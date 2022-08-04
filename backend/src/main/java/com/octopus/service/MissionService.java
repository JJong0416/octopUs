package com.octopus.service;

import com.octopus.domain.*;
import com.octopus.domain.User;
import com.octopus.domain.dto.*;
import com.octopus.exception.UserNotFoundException;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.exception.MissionNotFoundException;
import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.MissionRepository;
import com.octopus.repository.MissionTimeRepository;
import com.octopus.repository.OctopusTableRepository;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Target;
import java.util.List;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
// TODO: 2022-07-29  mapStructure 를 쓸 지, modelMapper를 쓸 지, 아예 안쓸지 고민
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionTimeRepository missionTimeRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    private final OctopusTableRepository octopus_tableRepository;

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

    @Transactional
    public String deleteUserFromMission(String userId, Long missionNo, String loginedUserId){
        // 1. mission table의 MissionUser에서 해당하는 id의 이름을 제거한다.
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(()->{
            throw new MissionNotFoundException();
        });
        User user = userRepository.findByUserId(userId).orElseThrow(()->{
            throw new UserNotFoundException();
        });

        if(!checkUserIdEqualLeaderId(mission,loginedUserId)) return "해당 미션의 방장만 강퇴할수 있습니다.";
        if(!checkMissionStatusIsOPEN(mission)) return "모집중인 방에서만 강퇴할 수 있습니다.";
        if(checkUserIdEqualLeaderId(mission, userId.toLowerCase())) return "방장은 강퇴할 수 없습니다.";

        // MissionUser에 userId가 없다면 잘못된 입력
        int idLocation = checkMissionContainsUserId(mission,userId);
        if(idLocation<0) return "미션에 등록되지 않은 user입니다.";

        // Users에서 삭제하기 로직
        String newUsers = mission.getMissionUsers().substring(0,idLocation-1)+
                mission.getMissionUsers().substring(idLocation+userId.length()+2);

        mission.updateMissionUsers(newUsers);

        missionRepository.save(mission);
        // 2. octopus_table 에서 해당하는 user, mission의 조합을 삭제한다
        octopus_tableRepository.deleteByUserAndMissionInQuery(user,mission);
        return "성공";
    }

    // TODO: 2022-08-02 contains말고 다른거
    public boolean checkUserIdEqualLeaderId(Mission mission, String loginedUserId){
        return loginedUserId.contains(mission.getMissionLeaderId());
    }

    public boolean checkMissionStatusIsOPEN(Mission mission){
        return mission.getMissionStatus().equals(MissionStatus.OPEN);
    }

    public Integer checkMissionContainsUserId(Mission mission, String userId){
        return mission.getMissionUsers().indexOf(userId.toLowerCase());
    }

    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findMissionByMissionNo(missionNo).orElseThrow(() -> {
            throw new RuntimeException("Not found Mission");
        });
    }

    @Transactional(readOnly = true)
    public boolean haveMissionTime(Long missionNo) {
        return missionTimeRepository.findMissionTimeByMissionNo(missionNo);
    }

    public boolean isAuthorizedMissionUser(Mission mission) {
        String currentUser = getCurrentUsername().get();
        return mission.getMissionLeaderId().equals(currentUser);
    }

    @Transactional(readOnly = true)
    public MissionDto getMissionDtoByMissionNo(Long missionNo){
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
                .build();
    }

    @Transactional
    public void modifyMission(MissionUpdateInfoDto missionUpdateInfoDto, long missionNo){
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        mission.updateMission(missionUpdateInfoDto);
    }

    @Transactional
    public void joinMission(long missionNo){
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(()->{
            throw new MissionNotFoundException();
        });
        User user = userRepository.findByUserId(getCurrentUsername().get()).orElseThrow(()->{
            throw new UserNotFoundException();
        });


        Octopus octopus = Octopus.addToOctopus()
                .user(user)
                .mission(mission)
                .build();
        System.out.println(octopus.toString());
        octopus_tableRepository.save(octopus);

    }

    private User createUser(SignUpDto signUpDto) {
        return User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();
    }


}
