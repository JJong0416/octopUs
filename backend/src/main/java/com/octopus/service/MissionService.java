package com.octopus.service;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public List<MissionListDto> getNewMissions(){
        return missionRepository.findTop5ByMissionStatusAndMissionOpen(
                Sort.by(Sort.Direction.DESC,"missionNo"),
                MissionStatus.OPEN,
                MissionOpenType.OPEN_ROOM
        );

    }
}
