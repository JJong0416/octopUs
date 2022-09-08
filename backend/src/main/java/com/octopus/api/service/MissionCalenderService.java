package com.octopus.api.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.octopus.api.repository.*;
import com.octopus.domain.*;
import com.octopus.domain.type.MissionStatus;
import com.octopus.dto.layer.PictureDto;
import com.octopus.dto.request.UploadPictureReq;
import com.octopus.dto.response.CalenderRes;
import com.octopus.dto.response.CalenderUserInfoRes;
import com.octopus.dto.response.MissionPictureRes;
import com.octopus.exception.CustomException;
import com.octopus.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
public class MissionCalenderService {

    private final MissionRepository missionRepository;
    private final OctopusTableRepository octopusTableRepository;
    private final AuthenticationRepository authenticationRepository;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;
    private final Storage storage;

    @Value("${bucketname}")
    private String bucketName;

    public float getUserRate(Long missionNo, String userId) {
        Mission mission = getMissionByMissionNo(missionNo);
        MissionTime missionTime = mission.getMissionTime();
        User user = getUserByUserId(userId);
        List<PictureDto> userPicture = getPictureByUserAndMission(mission, user);
        int totalMissionSize = getTotalMissionAuthenticationCount(missionTime);
        Float successRate = (float) userPicture.size() / (float) totalMissionSize * 100;

        return successRate;
    }

    public float getTeamRate(Long missionNo){
        Mission mission = getMissionByMissionNo(missionNo);
        MissionTime missionTime = mission.getMissionTime();
        List<User> joinedMissionUsers = getOctopusByMission(mission).stream()
                .map(Octopus::getUser)
                .collect(Collectors.toList());
        List<CalenderUserInfoRes> calenderUserInfos =
                getCalenderUserInfos(mission, missionTime, joinedMissionUsers);

        Float successTeamRate = 0.0f;
        for (CalenderUserInfoRes calenderUserInfo : calenderUserInfos) {
            successTeamRate += calenderUserInfo.getSuccessUserRate();
        }
        successTeamRate = successTeamRate / joinedMissionUsers.size();

        return successTeamRate;
    }

    public CalenderRes getCalenderRes(Long missionNo) {
        List<AuthenticationInfo> authenticationInfos = authenticationRepository.findAuthenticationInfoByMissionNo(missionNo);
        // 1. mission과 해당 미션 Time 가지고 오기
        Mission mission = getMissionByMissionNo(missionNo);
        MissionTime missionTime = mission.getMissionTime();

        // 2. 해당 미션에 Join된 User 객체들 가져오기
        List<User> joinedMissionUsers = getOctopusByMission(mission).stream()
                .map(Octopus::getUser)
                .collect(Collectors.toList());


        // 3. 그 후, CalenderUserInfos를 가져오기
        List<CalenderUserInfoRes> calenderUserInfos =
                getCalenderUserInfos(mission, missionTime, joinedMissionUsers);

        // 4. calenderUserInfos를 통해서 팀 전체 진행률을 계산해준다.
        Float successTeamRate = 0.0f;
        for (CalenderUserInfoRes calenderUserInfo : calenderUserInfos) {
            successTeamRate += calenderUserInfo.getSuccessUserRate();
        }
        successTeamRate = successTeamRate / joinedMissionUsers.size();

        boolean checkPostPossible = getPostPossible(calenderUserInfos, missionTime, authenticationInfos);
        Integer weekInProgress = Math.toIntExact(getCurrentWeek(missionTime.getMissionTimeStartTime()));
        // 4. 마지막으로 추가해준다.
        return new CalenderRes(weekInProgress, successTeamRate, checkPostPossible, calenderUserInfos);
    }


    // mission에 userNickname - pictures 매핑시킨 CalenderUserInfoRes로 반환
    public List<CalenderUserInfoRes> getCalenderUserInfos(Mission mission,
                                                          MissionTime missionTime,
                                                          List<User> joinedMissionUsers
    ) {
        // 1. 해당 미션의 전체 인증 숫자를 파악
        int totalMissionSize = getTotalMissionAuthenticationCount(missionTime);

        // 2. 미리 UserInfoRes 생성
        List<CalenderUserInfoRes> calenderUserInfos = new ArrayList<>(8);

        // 3. foreach 돌리면서 calenderUserInfos에 넣기
        for (User user : joinedMissionUsers) {
            List<PictureDto> pictureByUser = getPictureByUserAndMission(mission, user);

            calenderUserInfos.add(new CalenderUserInfoRes(user.getUserNickname(),
                    user.getUserAvatar(),
                    (float) pictureByUser.size() / (float) totalMissionSize * 100,
                    pictureByUser
            ));
        }
        return calenderUserInfos;
    }

    // mission에 userNickname - pictures 매핑시킨 MissionPictureRes로 반환
    public List<MissionPictureRes> getMissionPictureMatchingUser(Long missionNo) {

        // mission에 가입한 UserId을 받기
        Mission mission = getMissionByMissionNo(missionNo);

        // 해당 미션에 join된 User객체들 가져오기
        List<User> joinedMissionUsers = getOctopusByMission(mission).stream()
                .map(Octopus::getUser)
                .collect(Collectors.toList());

        // User에 따라서 picture를 가져와 MissionPictureRes에 넣고 반환
        return joinedMissionUsers.stream()
                .map(user -> new MissionPictureRes(user.getUserNickname(), getPictureByUserAndMission(mission, user)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void uploadPicture(Long missionNo, UploadPictureReq uploadPictureReq) {

        User user = getUserByUserId(getCurrentUsername().get());
        Mission mission = getMissionByMissionNo(missionNo);
        if (!mission.getMissionStatus().equals(MissionStatus.ONGOING))
            throw new CustomException(ErrorCode.BAD_REQUEST);
        StringBuilder filename = makeFileName(user, mission);

        String encodedImg = uploadPictureReq.getEncodedImg().split(",")[1];
        byte[] decode = Base64.decodeBase64(encodedImg);

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, filename.toString())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                        .setContentType("image/png")
                        .build(), decode);

        if (blobInfo == null)
            throw new CustomException(ErrorCode.BAD_REQUEST);

        //TODO: 2022-08-4, 목, 1:35 StringBuilder 또 써도되나  -박지수
        StringBuilder makeUrl = new StringBuilder();
        Picture picture = Picture.createPicture()
                .missionNo(mission)
                .userNo(user)
                .pictureUrl(makeUrl.append("https://storage.googleapis.com/")
                        .append(bucketName).append("/")
                        .append(filename).toString())
                .build();
        pictureRepository.save(picture);
    }

    public boolean getPostPossible(List<CalenderUserInfoRes> calenderUserInfos, MissionTime missionTime, List<AuthenticationInfo> authenticationInfos) {
        User user = getUserByUserId(getCurrentUsername().get());

        int weekAuthentication = 0;
        int todayAuthentication = 0;
        boolean isParticipated = false;
        boolean isPossibleTime = false;
        boolean isPossibleCnt = false;

        LocalDate thisWeekStart = missionTime.getMissionTimeStartTime()
                .plusDays((int) (getCurrentWeek(missionTime.getMissionTimeStartTime()) - 1) * 7).toLocalDate();
        LocalDate today = LocalDate.now();
        for (CalenderUserInfoRes calenderUserInfoRes : calenderUserInfos) {
            if (calenderUserInfoRes.getUserNickname().equals(user.getUserNickname())) {
                isParticipated = true;
                for (PictureDto pictureDto : calenderUserInfoRes.getUserPictures()) {
                    LocalDate pictureDate = pictureDto.getDate().toLocalDate();
                    if ((pictureDate.isAfter(thisWeekStart) || pictureDate.equals(thisWeekStart))
                            && (pictureDate.isBefore(today) || pictureDate.equals(today))) {
                        weekAuthentication++;
                    }
                    if (pictureDto.getDate().toLocalDate().equals(today))
                        todayAuthentication++;
                }
                break;
            }
        }

        LocalTime nowTime = LocalTime.now();
        for (AuthenticationInfo authenticationInfo : authenticationInfos) {
            if (nowTime.isAfter(authenticationInfo.getAuthenticationStartTime()) && nowTime.isBefore(authenticationInfo.getAuthenticationEndTime())) {
                isPossibleTime = true;
                break;
            }
        }
        if (missionTime.getMissionTimeDPW() * missionTime.getMissionTimeTPD()> weekAuthentication && missionTime.getMissionTimeTPD() > todayAuthentication) {
            isPossibleCnt = true;
        }

        return isParticipated && isPossibleTime && isPossibleCnt;
    }

    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findMissionByMissionNo(missionNo).orElseThrow(() -> {
            throw new CustomException(ErrorCode.MISSION_NOT_FOUND);
        });
    }

    @Transactional(readOnly = true)
    public List<Octopus> getOctopusByMission(Mission mission) {
        return octopusTableRepository.findOctopusByMission(mission)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found Octopus");
                });
    }

    @Transactional(readOnly = true)
    public List<PictureDto> getPictureByUserAndMission(Mission mission, User user) {

        List<Picture> pictures = pictureRepository.findPicturesByMissionNoAndUserNo(mission, user)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found Picture");
                });

        return pictures.stream()
                .map(PictureDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });
    }

    private Integer getTotalMissionAuthenticationCount(MissionTime missionTime) {
        return missionTime.getMissionTimeWeek() *
                missionTime.getMissionTimeDPW() *
                missionTime.getMissionTimeTPD();
    }

    private Long getCurrentWeek(LocalDateTime startDate) {
        LocalDate today = LocalDate.now();
        LocalDate start = startDate.toLocalDate();
        return (ChronoUnit.DAYS.between(start, today)) / 7 + 1;
    }

    private StringBuilder makeFileName(User user, Mission mission) {
        StringBuilder filename = new StringBuilder();
        filename.append(mission.getMissionNo())
                .append("/")
                .append(user.getUserId())
                .append("/")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")))
                .append(".png");
        return filename;
    }
}
