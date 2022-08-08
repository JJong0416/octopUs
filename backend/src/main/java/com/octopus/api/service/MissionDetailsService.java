package com.octopus.api.service;

import com.octopus.api.repository.*;
import com.octopus.domain.*;
import com.octopus.domain.type.MissionStatus;
import com.octopus.dto.request.AuthenticationReq;
import com.octopus.dto.request.MissionTimeReq;
import com.octopus.dto.request.MissionUpdateInfoReq;
import com.octopus.dto.response.MissionRes;
import com.octopus.exception.CustomException;
import com.octopus.exception.ErrorCode;
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

    private final OctopusTableRepository octopusTableRepository;

    private final AuthenticationRepository authenticationRepository;

    @Transactional
    public void createMissionTime(Long missionNo, MissionTimeReq missionTimeReq) {

        // 미션을 가지고 와서
        Mission mission = getMissionByMissionNo(missionNo);

        // 권한이 있으면서 동시에 기존에 MissionTime을 안가지고 있다면
        // 권한이 없거나 이미 미션이 존재한다면
        if (!isAuthorizedMissionUser(mission))
            throw new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);
        if (haveMissionTime(missionNo))
            throw new CustomException(ErrorCode.BAD_REQUEST);

        // 미션 타임 생성
        MissionTime missionTime = MissionTime.createMTBuilder()
                .mission(mission)
                .missionTimeWeek(missionTimeReq.getMissionTimeWeek())
                .missionTimeDPW(missionTimeReq.getMissionTimeDPW())
                .missionTimeTPD(missionTimeReq.getMissionTimeTPD())
                .build();

        missionTimeRepository.save(missionTime);

        if (haveAuthentication(missionNo)) mission.updateMissionStatus(MissionStatus.OPEN);

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
    public void deleteUserFromMission(String userId, Long missionNo) {
        // 1. mission table의 MissionUser에서 해당하는 id의 이름을 제거한다.

        String loginedUserId = getCurrentUsername().get();
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new CustomException(ErrorCode.MISSION_NOT_FOUND);
        });
        User user = userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        if (!checkUserIdEqualLeaderId(mission, loginedUserId))
            throw new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);

        if (!checkMissionStatusIsOPEN(mission))
            throw new CustomException(ErrorCode.MISSION_NOT_OPENED);

        // MissionUser에 userId가 없다면 잘못된 입력
        int nicknameLocation = checkMissionContainsUserNickname(mission, user.getUserNickname());
        if (nicknameLocation < 0)
            throw new CustomException(ErrorCode.USER_NOT_FOUND);

        // Users에서 삭제하기 로직
        String newUsers = mission.getMissionUsers().substring(0, nicknameLocation - 2) +
                mission.getMissionUsers().substring(nicknameLocation + user.getUserNickname().length());

        mission.updateMissionUsers(newUsers);

        missionRepository.save(mission);
        // 2. octopus_table 에서 해당하는 user, mission의 조합을 삭제한다
        octopusTableRepository.deleteByUserAndMissionInQuery(user, mission);
    }

    @Transactional
    public void createAuthentication(Long missionNo, AuthenticationReq authenticationReq) {
        if (isValidAuthenticationRequest(authenticationReq))
            throw new CustomException(ErrorCode.BAD_REQUEST);

        Mission mission = getMissionByMissionNo(missionNo);

        if (!isAuthorizedMissionUser(mission)) {
            throw new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);
        }

        AuthenticationInfo authenticationInfo = AuthenticationInfo.createAuthenticationInfo()
                .mission(mission)
                .authenticationStartTime(authenticationReq.getAuthenticationStartTime())
                .authenticationEndTime(authenticationReq.getAuthenticationEndTime())
                .build();

        authenticationRepository.save(authenticationInfo);

        if (haveMissionTime(missionNo)) mission.updateMissionStatus(MissionStatus.OPEN);
    }

    @Transactional(readOnly = true)
    public boolean haveAuthentication(Long missionNo) {
        return authenticationRepository.haveAuthenticationInfoByMissionNo(missionNo);
    }

    @Transactional(readOnly = true)
    public List<Octopus> getOctopusByMission(Mission mission) {
        return octopusTableRepository.findOctopusByMission(mission)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.OCTOPUS_NOT_FOUND);
                });
    }

    @Transactional
    public void modifyMission(MissionUpdateInfoReq missionUpdateInfoReq, long missionNo) {
        Mission mission = missionRepository.findByMissionNo(missionNo).orElseThrow(() -> {
            throw new CustomException(ErrorCode.MISSION_NOT_FOUND);
        });
        mission.updateMission(missionUpdateInfoReq);
    }

    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findMissionByMissionNo(missionNo).orElseThrow(() -> {
            throw new CustomException(ErrorCode.MISSION_NOT_FOUND);
        });
    }

    @Transactional(readOnly = true)
    public List<String> getMissionUsers(Long missionNo) {
        Mission mission = missionRepository.findByMissionNo(missionNo)
                .orElseThrow(() -> new CustomException(ErrorCode.MISSION_NOT_FOUND));

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

    private Integer checkMissionContainsUserNickname(Mission mission, String userNickname) {
        return mission.getMissionUsers().indexOf(userNickname.toLowerCase());
    }

    private Boolean isValidAuthenticationRequest(AuthenticationReq authenticationReq) {
        return authenticationReq.getAuthenticationEndTime()
                .isBefore(authenticationReq.getAuthenticationStartTime()) || authenticationReq.getAuthenticationEndTime()
                .equals(authenticationReq.getAuthenticationStartTime());
    }
}
