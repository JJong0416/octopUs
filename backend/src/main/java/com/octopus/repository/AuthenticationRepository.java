package com.octopus.repository;

import com.octopus.domain.AuthenticationInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AuthenticationRepository extends CrudRepository<AuthenticationInfo, Long> {
    @Query(value = "SELECT COUNT(ai) > 0 " + "FROM AuthenticationInfo ai " + "WHERE ai.mission.missionNo=:missionNo")
    boolean haveAuthenticationInfoByMissionNo(@Param("missionNo") Long missionNo);

    @Query(value = "SELECT ai " + "FROM AuthenticationInfo ai " + "WHERE ai.mission.missionNo=:missionNo")
    List<AuthenticationInfo> findAuthenticationInfoByMissionNo(@Param("missionNo") Long missionNo);
}
