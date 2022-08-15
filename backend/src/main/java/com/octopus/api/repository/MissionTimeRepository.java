package com.octopus.api.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.MissionTime;
import com.octopus.domain.pk.MissionPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MissionTimeRepository extends JpaRepository<MissionTime, MissionPk> {

    // TODO: 2022-08-02 성능 이슈
//    @Query(value = "SELECT mt FROM  MissionTime mt WHERE mt.mission.missionNo=?1")
    @Query(value = "SELECT COUNT(mt) > 0 " + "FROM MissionTime mt " + "WHERE mt.mission.missionNo=:missionNo")
    boolean haveMissionTimeByMissionNo(@Param("missionNo") Long missionNo);

    @Query(value = "SELECT mt " + "FROM MissionTime mt " + "WHERE mt.mission.missionNo=:missionNo")
    Optional<MissionTime> findMissionTimeByMissionNo(@Param("missionNo") Long missionNo);

    @Transactional
    @Modifying
    void deleteByMission(Mission mission);
}
