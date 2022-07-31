package com.octopus.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

   List<Mission> findByMissionStatusAndMissionOpen(MissionStatus missionStatus, MissionOpenType missionOpenType);
}
