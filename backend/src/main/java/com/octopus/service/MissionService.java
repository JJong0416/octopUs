package com.octopus.service;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.octopus.domain.Mission;
import com.octopus.domain.Picture;
import com.octopus.domain.User;
import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.dto.UploadPictureDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.exception.MissionNotFoundException;
import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.MissionRepository;
import com.octopus.repository.Octopus_tableRepository;
import com.octopus.repository.PictureRepository;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
// TODO: 2022-07-29  mapStructure 를 쓸 지, modelMapper를 쓸 지, 아예 안쓸지 고민
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;

    private final EntityManager em;
    private final Octopus_tableRepository octopus_tableRepository;
    private final Storage storage;
    @Value("${bucketname}")
    private String bucketName;

    /* 미션 코드 중복은 안했음. */
    @Transactional
    public void createMission(MissionCreateDto missionCreateDto) {

        String currentUserId = getCurrentUsername().get();

        /* 미션에 방장 정보와 String 으로 유저 넣기 */
        missionCreateDto.addMissionLeaderIdAndUser(currentUserId);

        /* DTO 를 통한 미션 생성 */
        Mission mission = Mission.createMission()
                .missionCreateDto(missionCreateDto)
                .build();

        missionRepository.save(mission);
    }

    @Transactional(readOnly = true)
    public List<MissionListDto> getNewMissions() {
        return missionRepository.findTop5ByMissionStatusAndMissionOpen(
                Sort.by(Sort.Direction.DESC, "missionNo"),
                MissionStatus.OPEN,
                MissionOpenType.OPEN_ROOM
        );
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
        octopus_tableRepository.deleteByUserAndMissionInQuery(user, mission);
        return "성공";
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


        byte decode[] = Base64.decodeBase64(uploadPictureDto.getEncodedImg());
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, filename.toString())
                        .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                        .setContentType("image/png")
                        .build(), decode);

        if(blobInfo == null)
            return false;

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
        return true;
    }


    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new RuntimeException("Not found Mission");
        });
    }

    @Transactional(readOnly = true)
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }


}
