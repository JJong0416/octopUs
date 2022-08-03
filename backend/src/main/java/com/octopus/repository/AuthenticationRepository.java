package com.octopus.repository;

import com.octopus.domain.AuthenticationInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<AuthenticationInfo, Long> {
    @Query(value = "SELECT COUNT(ai) > 0 " + "FROM AuthenticationInfo ai " + "WHERE ai.mission.missionNo=:missionNo")
    boolean findAuthenticationInfoByMissionNo(@Param("missionNo") Long missionNo);
}
