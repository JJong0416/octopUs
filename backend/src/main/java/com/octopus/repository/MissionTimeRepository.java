package com.octopus.repository;

import com.octopus.composite.MissionId;
import com.octopus.domain.MissionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MissionTimeRepository extends JpaRepository<MissionTime, MissionId> {

    // TODO: 2022-08-02 성능 이슈
//    @Query(value = "SELECT mt FROM  MissionTime mt WHERE mt.mission.missionNo=?1")
    @Query(value = "SELECT COUNT(mt) > 0 " + "FROM MissionTime mt " + "WHERE mt.mission.missionNo=:missionNo")
    boolean findMissionTimeByMissionNo(@Param("missionNo") Long missionNo);
}
