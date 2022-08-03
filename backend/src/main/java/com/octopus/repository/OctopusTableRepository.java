package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.Octopus;
import com.octopus.domain.PK.OctopusPK;
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

    Optional<List<Octopus>> findOctopusByMission(Mission mission);

}
