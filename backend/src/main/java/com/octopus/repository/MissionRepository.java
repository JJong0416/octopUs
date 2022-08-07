package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.dto.layer.MissionListDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findTop5By(Sort sort);

    Optional<Mission> findByMissionNo(Long missionNo);

    List<MissionListDto> findTop5ByMissionStatusAndMissionOpen(
            Sort sort, MissionStatus missionStatus, MissionOpenType missionOpenType);

    Optional<Mission> findMissionByMissionNo(Long missionNo);

    List<Mission> findByMissionStatusAndMissionOpen(MissionStatus missionStatus, MissionOpenType missionOpenType);
}
