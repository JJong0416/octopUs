package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<MissionListDto> findTop5ByMissionStatusAndMissionOpen(Sort sort, MissionStatus missionStatus, MissionOpenType missionOpenType);

    Optional<Mission> findByMissionNo(Long missionNo);

}
