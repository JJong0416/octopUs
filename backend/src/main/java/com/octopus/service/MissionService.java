package com.octopus.service;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
// TODO: 2022-07-29  mapStructure 를 쓸 지, modelMapper를 쓸 지, 아예 안쓸지 고민
public class MissionService {

    private final MissionRepository missionRepository;

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
    public List<Mission> getNewMissions(){
        return missionRepository.findTop5By(Sort.by(Sort.Direction.DESC,"missionNo"));
    }
}
