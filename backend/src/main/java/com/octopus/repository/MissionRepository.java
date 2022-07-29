package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findTop5ByMissionStatusAndMissionOpen(Sort sort, MissionStatus missionStatus, MissionOpenType missionOpenType);

    List<MissionListDto> findTop5By(Sort sort);
}
