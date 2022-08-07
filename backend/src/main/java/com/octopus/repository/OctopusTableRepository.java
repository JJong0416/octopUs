package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.Octopus;
import com.octopus.domain.pk.OctopusPK;
import com.octopus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OctopusTableRepository extends JpaRepository<Octopus, OctopusPK> {

    @Modifying
    @Transactional
    @Query("delete from Octopus o where o.user = :user and o.mission = :mission")
    void deleteByUserAndMissionInQuery(@Param("user") User user, @Param("mission") Mission mission);

    @Modifying
    @Transactional
    @Query(value = "Insert into octopus_table Values(:userNo, :missionNo)", nativeQuery = true)
    void insertToOctopusTable(@Param("userNo") Long userNo, @Param("missionNo") Long missionNo);

    @Transactional(readOnly = true)
    @Query("select o.mission from Octopus o where o.user = :user")
    List<Mission> findMissionByUser(@Param("user") User user);

    @Transactional(readOnly = true)
    Optional<List<Octopus>> findOctopusByMission(Mission mission);
    
}
