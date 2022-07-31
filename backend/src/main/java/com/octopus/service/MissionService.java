package com.octopus.service;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional
    public List<MissionListDto> getHotRoom() {
        //모집중이면서 공개방 리스트가져오기
        List<Mission> missions = missionRepository.findByMissionStatusAndMissionOpen(MissionStatus.OPEN, MissionOpenType.OPEN_ROOM);
        Collections.sort(missions);
        List<MissionListDto> missionList = new LinkedList<>();
        for (Mission mission : missions) {
            if (mission.getMissionLimitPersonnel() - (mission.getMissionUsers().length() - (mission.getMissionUsers().replaceAll(",", "").length()) + 1) > 0) {
                missionList.add(MissionListDto.builder()
                        .missionCode(mission.getMissionCode())
                        .missionName(mission.getMissionName()).build());
            }
            if (missionList.size() == 5)
                break;
        }
        return missionList;
    }
}
