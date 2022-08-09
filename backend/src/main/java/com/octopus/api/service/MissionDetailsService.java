package com.octopus.api.service;

import com.octopus.api.repository.*;
import com.octopus.domain.*;
import com.octopus.domain.type.MissionStatus;
import com.octopus.dto.request.AuthenticationReq;
import com.octopus.dto.request.MissionTimeReq;
import com.octopus.dto.request.MissionUpdateInfoReq;
import com.octopus.dto.response.MissionRes;
import com.octopus.exception.MissionNotFoundException;
import com.octopus.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
public class MissionDetailsService {

    private final MissionRepository missionRepository;
    private final MissionTimeRepository missionTimeRepository;
    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;
    private final OctopusTableRepository octopusTableRepository;
    private final AuthenticationRepository authenticationRepository;


    @Transactional
    public boolean createMissionTime(Long missionNo, MissionTimeReq missionTimeReq) {

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
                .missionTimeWeek(missionTimeReq.getMissionTimeWeek())
                .missionTimeDPW(missionTimeReq.getMissionTimeDPW())
                .missionTimeTPD(missionTimeReq.getMissionTimeTPD())
                .build();

        missionTimeRepository.save(missionTime);

        if (haveAuthentication(missionNo)) mission.updateMissionStatus(MissionStatus.OPEN);

        return true;
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
    public boolean createAuthentication(Long missionNo, AuthenticationReq authenticationReq) {
        if (isValidAuthenticationRequest(authenticationReq))
            return false;
        
        Mission mission = getMissionByMissionNo(missionNo);
        
        if (!isAuthorizedMissionUser(mission)) {
            return false;
        }
        
        AuthenticationInfo authenticationInfo = AuthenticationInfo.createAuthenticationInfo()
                .mission(mission)
                .authenticationStartTime(authenticationReq.getAuthenticationStartTime())
                .authenticationEndTime(authenticationReq.getAuthenticationEndTime())
                .build();

        authenticationRepository.save(authenticationInfo);
        
        if (haveMissionTime(missionNo)) mission.updateMissionStatus(MissionStatus.OPEN);
        return true;
    }

    @Transactional(readOnly = true)
    public boolean haveAuthentication(Long missionNo) {
        return authenticationRepository.haveAuthenticationInfoByMissionNo(missionNo);
    }

    @Transactional(readOnly = true)
    public List<Octopus> getOctopusByMission(Mission mission) {
        return octopusTableRepository.findOctopusByMission(mission)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found Octopus");
                });
    }

    @Transactional
    public void modifyMission(MissionUpdateInfoReq missionUpdateInfoReq, long missionNo) {
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        mission.updateMission(missionUpdateInfoReq);
    }
    
    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findMissionByMissionNo(missionNo).orElseThrow(() -> {
            throw new RuntimeException("Not found Mission");
        });
    }

    @Transactional(readOnly = true)
    public List<String> getMissionUsers(Long missionNo) {
        Mission mission = missionRepository.findByMissionNo(missionNo)
                .orElseThrow(MissionNotFoundException::new);

        return Arrays.stream(mission.getMissionUsers().split(", "))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean haveMissionTime(Long missionNo) {
        return missionTimeRepository.findMissionTimeByMissionNo(missionNo);
    }

    @Transactional(readOnly = true)
    public MissionRes getMissionDtoByMissionNo(Long missionNo) {
        Mission mission = getMissionByMissionNo(missionNo);

        return MissionRes.builder()
                .missionNo(missionNo)
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

    private boolean isAuthorizedMissionUser(Mission mission) {
        String currentUser = getCurrentUsername().get();
        return mission.getMissionLeaderId().equals(currentUser);
    }

    private boolean checkUserIdEqualLeaderId(Mission mission, String loginedUserId) {
        return loginedUserId.contains(mission.getMissionLeaderId());
    }

    private boolean checkMissionStatusIsOPEN(Mission mission) {
        return mission.getMissionStatus().equals(MissionStatus.OPEN);
    }

    private Integer checkMissionContainsUserId(Mission mission, String userId) {
        return mission.getMissionUsers().indexOf(userId.toLowerCase());
    }

    private Boolean isValidAuthenticationRequest(AuthenticationReq authenticationReq){
        return authenticationReq.getAuthenticationEndTime()
                .isBefore(authenticationReq.getAuthenticationStartTime()) || authenticationReq.getAuthenticationEndTime()
                .equals(authenticationReq.getAuthenticationStartTime());
    }
}
