package com.octopus.api.repository;

import com.octopus.domain.Mission;
import com.octopus.domain.type.MissionType;
import com.octopus.dto.layer.MissionListDto;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.dto.response.MissionRes;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findTop5By(Sort sort);

    Optional<Mission> findByMissionNo(Long missionNo);

    List<Mission> findTop5ByMissionStatusAndMissionOpen(
            Sort sort, MissionStatus missionStatus, MissionOpenType missionOpenType);

    Optional<Mission> findMissionByMissionNo(Long missionNo);

    List<Mission> findByMissionStatusAndMissionOpen(MissionStatus missionStatus, MissionOpenType missionOpenType);

    List<MissionRes> findMissionsByMissionCodeAndMissionStatus(String missionCode, MissionStatus missionStatus);

    List<MissionRes> findMissionsByMissionTitleContainingAndMissionOpenAndMissionStatus(
            String missionTitle, MissionOpenType openRoom,  MissionStatus missionStatus
    );

    List<MissionRes> findMissionsByMissionTypeAndMissionStatus(MissionType missionType, MissionStatus missionStatus);

    @Modifying
    @Transactional
    void deleteByMissionNo(Long missionNo);
}

/**
 * 	// 단순 조건
 * 	public School findByName(String name); // where name = ?
 * 	public List<School> findByRegion(String region); // where region = ?
 *
 * 	// not
 * 	public List<School> findByRegionNot(String region); // where region <> ?
 *
 * 	// and
 * 	public List<School> findByNameAndRanking(String name, int ranking); // where name = ? and ranking = ?
 *
 * 	// or
 * 	public List<School> findByNameOrRanking(String name, int ranking); // where name = ? or ranking = ?
 *
 * 	// between
 * 	public List<School> findByRankingBetween(int startRanking, int endRanking); // where ranking between ? and ?
 *
 * 	// 부등호
 * 	public List<School> findByRankingLessThan(int ranking); // where ranking < ?
 * 	public List<School> findByRankingLessThanEqual(int ranking); // where ranking <= ?
 *
 */