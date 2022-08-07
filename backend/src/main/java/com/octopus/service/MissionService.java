package com.octopus.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.octopus.domain.*;
import com.octopus.domain.dto.*;
import com.octopus.domain.dto.response.CalenderRes;
import com.octopus.domain.dto.response.CalenderUserInfoRes;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.exception.MissionNotFoundException;
import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
// TODO: 2022-07-29  mapStructure 를 쓸 지, modelMapper를 쓸 지, 아예 안쓸지 고민
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionTimeRepository missionTimeRepository;
    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;
    private final OctopusTableRepository octopusTableRepository;
    private final AuthenticationRepository authenticationRepository;

    private final Storage storage;

    @Value("${bucketname}")
    private String bucketName;

    public CalenderRes getCalenderRes(Long missionNo){

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

        // 4. 마지막으로 추가해준다.
        return new CalenderRes(1,successTeamRate,true,calenderUserInfos);
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
        for (User user: joinedMissionUsers) {
            List<PictureRes> pictureByUser = getPictureByUserAndMission(mission, user);

            calenderUserInfos.add(new CalenderUserInfoRes(user.getUserNickname(),
                    user.getUserAvatar(),
                    (float) pictureByUser.size()/ (float) totalMissionSize * 100 ,
                    pictureByUser
            ));
        }
        return calenderUserInfos;
    }

    /* 미션 코드 중복은 안했음. */
    @Transactional
    public void createMission(MissionCreateDto missionCreateDto) {
        User user = userRepository.findByUserId(getCurrentUsername().get()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        /* 미션에 방장 정보와 String 으로 유저 넣기 */
        missionCreateDto.addMissionLeaderIdAndUser(user.getUserId(), user.getUserNickname());

        /* DTO 를 통한 미션 생성 */
        Mission mission = Mission.createMission()
                .missionCreateDto(missionCreateDto)
                .build();
        missionRepository.save(mission);

        octopusTableRepository.insertToOctopusTable(user.getUserNo(), mission.getMissionNo());

    }

    @Transactional
    public boolean createMissionTime(Long missionNo, MissionTimeDto missionTimeDto) {

        // 미션을 가지고 와서
        Mission mission = getMissionByMissionNo(missionNo);

        // 권한이 있으면서 동시에 기존에 MissionTime을 안가지고 있다면
        // 권한이 없거나 이미 미션이 존재한다면
        if (!isAuthorizedMissionUser(mission) || haveMissionTime(missionNo)) {
            return false;
        }

        // 미션 타임 생성
        MissionTime missionTime = MissionTime.createMTBuilder()
                .mission(mission)
                .missionTimeWeek(missionTimeDto.getMissionTimeWeek())
                .missionTimeDPW(missionTimeDto.getMissionTimeDPW())
                .missionTimeTPD(missionTimeDto.getMissionTimeTPD())
                .build();

        missionTimeRepository.save(missionTime);

        if(haveAuthentication(missionNo)) mission.updateMissionStatus(MissionStatus.OPEN);

        return true;
    }

    @Transactional(readOnly = true)
    public List<MissionListDto> getNewMissions() {
        return missionRepository.findTop5ByMissionStatusAndMissionOpen(
                Sort.by(Sort.Direction.DESC, "missionNo"),
                MissionStatus.OPEN,
                MissionOpenType.OPEN_ROOM
        );
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
    public String deleteUserFromMission(String userId, Long missionNo, String loginedUserId) {
        // 1. mission table의 MissionUser에서 해당하는 id의 이름을 제거한다.
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new MissionNotFoundException();
        });
        User user = userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        if (!checkUserIdEqualLeaderId(mission, loginedUserId)) return "해당 미션의 방장만 강퇴할수 있습니다.";
        if (!checkMissionStatusIsOPEN(mission)) return "모집중인 방에서만 강퇴할 수 있습니다.";
        if (checkUserIdEqualLeaderId(mission, userId.toLowerCase())) return "방장은 강퇴할 수 없습니다.";

        // MissionUser에 userId가 없다면 잘못된 입력
        int idLocation = checkMissionContainsUserId(mission, userId);
        if (idLocation < 0) return "미션에 등록되지 않은 user입니다.";

        // Users에서 삭제하기 로직
        String newUsers = mission.getMissionUsers().substring(0, idLocation - 1) +
                mission.getMissionUsers().substring(idLocation + userId.length() + 2);

        mission.updateMissionUsers(newUsers);

        missionRepository.save(mission);
        // 2. octopus_table 에서 해당하는 user, mission의 조합을 삭제한다
        octopusTableRepository.deleteByUserAndMissionInQuery(user, mission);
        return "성공";
    }

    @Transactional
    public boolean createAuthentication(Long missionNo, AuthenticationDto authenticationDto) {
        Mission mission = getMissionByMissionNo(missionNo);
        if (!isAuthorizedMissionUser(mission) || haveAuthentication(missionNo)) {
            return false;
        }
        AuthenticationInfo authenticationInfo = AuthenticationInfo.createAuthenticationInfo()
                .mission(mission)
                .authenticationStartTime(authenticationDto.getAuthenticationStartTime())
                .authenticationEndTime(authenticationDto.getAuthenticationEndTime())
                .build();

        authenticationRepository.save(authenticationInfo);
        if(haveMissionTime(missionNo)) mission.updateMissionStatus(MissionStatus.OPEN);
        return true;
    }

    @Transactional(readOnly = true)
    public boolean haveAuthentication(Long missionNo) {
        return authenticationRepository.findAuthenticationInfoByMissionNo(missionNo);
    }

    // TODO: 2022-08-02 contains말고 다른거
    public boolean checkUserIdEqualLeaderId(Mission mission, String loginedUserId) {
        return loginedUserId.contains(mission.getMissionLeaderId());
    }

    public boolean checkMissionStatusIsOPEN(Mission mission) {
        return mission.getMissionStatus().equals(MissionStatus.OPEN);
    }

    public Integer checkMissionContainsUserId(Mission mission, String userId) {
        return mission.getMissionUsers().indexOf(userId.toLowerCase());
    }


    public List<Octopus> getOctopusByMission(Mission mission) {
        return octopusTableRepository.findOctopusByMission(mission)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found Octopus");
                });
    }

    @Transactional(readOnly = true)
    public boolean haveMissionTime(Long missionNo) {
        return missionTimeRepository.findMissionTimeByMissionNo(missionNo);
    }

    @Transactional(readOnly = true)
    public List<PictureRes> getPictureByUserAndMission(Mission mission, User user) {

        List<Picture> pictures = pictureRepository.findPicturesByMissionNoAndUserNo(mission, user)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found Picture");
                });

        return pictures.stream()
                .map(PictureRes::new)
                .collect(Collectors.toList());
    }

    public boolean isAuthorizedMissionUser(Mission mission) {
        String currentUser = getCurrentUsername().get();
        return mission.getMissionLeaderId().equals(currentUser);
    }

    @Transactional
    public List<MissionListDto> getHotMissions() {
        //모집중이면서 공개방 리스트가져오기
        List<Mission> missions = missionRepository.findByMissionStatusAndMissionOpen(MissionStatus.OPEN, MissionOpenType.OPEN_ROOM);
        if (missions == null || missions.isEmpty())
            throw new MissionNotFoundException();
        List<MissionListDto> missionList = missions.stream()
                .filter(mission -> mission.getMissionLimitPersonnel() -
                        (mission.getMissionUsers().length() - (mission.getMissionUsers().replaceAll(",", "").length()) + 1) > 0)
                .sorted(missionComparator)
                .map(mission -> MissionListDto.builder()
                        .missionNo(mission.getMissionNo())
                        .missionCode(mission.getMissionCode())
                        .missionTitle(mission.getMissionTitle()).build())
                .collect(Collectors.toList());

        return missionList.size() < 5 ? missionList : missionList.subList(0, 5);
    }

    Comparator<Mission> missionComparator = Comparator.comparingInt(m -> (m.getMissionLimitPersonnel() - (m.getMissionUsers().length()
            - (m.getMissionUsers().replaceAll(",", "").length()) + 1)));


    @Transactional(readOnly = true)
    public MissionDto getMissionDtoByMissionNo(Long missionNo) {
        Mission mission = getMissionByMissionNo(missionNo);

        return MissionDto.builder()
                .missionCode(mission.getMissionCode())
                .missionContent(mission.getMissionContent())
                .missionTitle(mission.getMissionTitle())
                .missionLeaderId(mission.getMissionLeaderId())
                .missionOpen(mission.getMissionOpen())
                .missionPoint(mission.getMissionPoint())
                .missionType(mission.getMissionType())
                .missionStatus(mission.getMissionStatus())
                .missionLimitPersonnel(mission.getMissionLimitPersonnel())
                .missionUsers(mission.getMissionUsers())
                .build();
    }

    @Transactional
    public void modifyMission(MissionUpdateInfoDto missionUpdateInfoDto, long missionNo) {
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        mission.updateMission(missionUpdateInfoDto);
    }

    @Transactional
    public void joinMission(Long missionNo) {
        User user = userRepository.findByUserId(getCurrentUsername().get()).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        StringBuilder sb = new StringBuilder();
        sb.append(mission.getMissionUsers()).append(", ").append(user.getUserNickname());
        String newList = sb.toString();
        mission.updateMissionUsers(newList);

        octopusTableRepository.insertToOctopusTable(user.getUserNo(), missionNo);
    }


    @Transactional
    public boolean uploadPicture(Long missionNo, UploadPictureDto uploadPictureDto) {

        StringBuilder filename = new StringBuilder();
        User user = getUserByUserId(getCurrentUsername().get());
        Mission mission = getMissionByMissionNo(missionNo);
        filename.append(mission.getMissionNo())
                .append("/")
                .append(user.getUserId())
                .append("/")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")))
                .append(".png");


        byte[] decode = Base64.decodeBase64(uploadPictureDto.getEncodedImg());
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, filename.toString())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                        .setContentType("image/png")
                        .build(), decode);

        if (blobInfo == null)
            return false;

        //TODO: 2022-08-4, 목, 1:35 StringBuilder 또 써도되나  -박지수
        StringBuilder makeUrl = new StringBuilder();
        Picture picture = Picture.createPicture()
                .missionNo(mission)
                .userNo(user)
                .pictureUrl(makeUrl.append("https://storage.googleapㅐis.com/")
                        .append(bucketName).append("/")
                        .append(filename).toString())
                .build();
        pictureRepository.save(picture);
        return true;
    }

    @Transactional(readOnly = true)
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findMissionByMissionNo(missionNo).orElseThrow(() -> {
            throw new RuntimeException("Not found Mission");
        });
    }

    @Transactional(readOnly = true)
    public List<String> getMissionUsers(Long missionNo){
        Mission mission = missionRepository.findByMissionNo(missionNo)
                .orElseThrow(MissionNotFoundException::new);

        return Arrays.stream(mission.getMissionUsers().split(", "))
                .collect(Collectors.toList());
    }

    private Integer getTotalMissionAuthenticationCount(MissionTime missionTime){
        return missionTime.getMissionTimeWeek() *
                missionTime.getMissionTimeDPW() *
                missionTime.getMissionTimeTPD();
    }
}
