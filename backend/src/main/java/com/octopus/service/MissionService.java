package com.octopus.service;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional
    public List<MissionListDto> getHotRoom() {
        //모집중이면서 공개방 리스트가져오기
        List<Mission> missions = missionRepository.findByMissionStatusAndMissionOpen(MissionStatus.OPEN, MissionOpenType.OPEN_ROOM);

        List<MissionListDto> missionList = missions.stream()
                .filter(mission -> mission.getMissionLimitPersonnel() -
                        (mission.getMissionUsers().length() - (mission.getMissionUsers().replaceAll(",", "").length()) + 1) > 0)
                .sorted((m1, m2) -> ((m1.getMissionLimitPersonnel() - (m1.getMissionUsers().length()
                        - (m1.getMissionUsers().replaceAll(",", "").length()) + 1)) -
                        (m2.getMissionLimitPersonnel() - (m2.getMissionUsers().length()
                                - (m2.getMissionUsers().replaceAll(",", "").length()) + 1))))
                .map(mission -> MissionListDto.builder()
                        .missionCode(mission.getMissionCode())
                        .missionName(mission.getMissionName()).build())
                .collect(Collectors.toList());


        return missionList.size() < 5 ? missionList : missionList.subList(0, 5);

    }
}
