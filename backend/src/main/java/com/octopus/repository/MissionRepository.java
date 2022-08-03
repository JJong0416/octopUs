package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.domain.dto.MissionDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findTop5By(Sort sort);

    Optional<Mission> findByMissionNo(Long missionNo);
}
