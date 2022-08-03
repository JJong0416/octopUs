package com.octopus.service;

import com.octopus.domain.AuthenticationInfo;
import com.octopus.domain.Mission;
import com.octopus.domain.dto.AuthenticationDto;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.exception.MissionNotFoundException;
import com.octopus.repository.AuthenticationRepository;
import com.octopus.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import java.util.List;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
// TODO: 2022-07-29  mapStructure 를 쓸 지, modelMapper를 쓸 지, 아예 안쓸지 고민
public class MissionService {

    private final MissionRepository missionRepository;
    private final AuthenticationRepository authenticationRepository;

    /* 미션 코드 중복은 안했음. */
    @Transactional
    public void createMission(MissionCreateDto missionCreateDto){

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
    public List<MissionListDto> getNewMissions(){
        return missionRepository.findTop5ByMissionStatusAndMissionOpen(
                Sort.by(Sort.Direction.DESC,"missionNo"),
                MissionStatus.OPEN,
                MissionOpenType.OPEN_ROOM
        );

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
        return true;
    }

    @Transactional(readOnly = true)
    public Mission getMissionByMissionNo(Long missionNo) {
        return missionRepository.findMissionByMissionNo(missionNo).orElseThrow(() -> {
            //MissionNotFoundException 쓰는건 안좋을지?
            throw new RuntimeException("Not found Mission");
        });
    }
    @Transactional(readOnly = true)
    public boolean haveAuthentication(Long missionNo) {
        return authenticationRepository.findAuthenticationInfoByMissionNo(missionNo);
    }

    public boolean isAuthorizedMissionUser(Mission mission) {
        String currentUser = getCurrentUsername().get();
        return mission.getMissionLeaderId().equals(currentUser);
    }





}
