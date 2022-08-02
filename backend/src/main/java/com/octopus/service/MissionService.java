package com.octopus.service;

import com.octopus.domain.Mission;
import com.octopus.domain.Octopus;
import com.octopus.domain.PK.OctopusPK;
import com.octopus.domain.User;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.repository.MissionRepository;
import com.octopus.repository.Octopus_tableRepository;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
// TODO: 2022-07-29  mapStructure 를 쓸 지, modelMapper를 쓸 지, 아예 안쓸지 고민
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    private final EntityManager em;
    private final Octopus_tableRepository octopus_tableRepository;

    /* 미션 코드 중복은 안했음. */
    @Transactional
    public void createMission(MissionCreateDto missionCreateDto){

        String currentUserId = getCurrentUsername().get();

        /* 미션에 방장 정보와 String 으로 유저 넣기 */
        missionCreateDto.addMissionLeaderIdAndUser(currentUserId);

        /* DTO 를 통한 미션 생성 */
        Mission mission = Mission.createMission()
                .missionCreateDto(missionCreateDto)
                .build();

        missionRepository.save(mission);
    }

    @Transactional(readOnly = true)
    public List<MissionListDto> getNewMissions(){
        return missionRepository.findTop5ByMissionStatusAndMissionOpen(
                Sort.by(Sort.Direction.DESC,"missionNo"),
                MissionStatus.OPEN,
                MissionOpenType.OPEN_ROOM
        );
    }

    @Transactional
    public String deleteUserFromMission(String userId, Long missionNo, String loginedUserId){
        // 1. mission table의 MissionUser에서 해당하는 id의 이름을 제거한다.
        Optional<Mission> missionNullCheck = missionRepository.findByMissionNo(missionNo);
        Optional<User> userNullCheck = userRepository.findByUserId(userId);

        if(!missionNullCheck.isPresent() || !userNullCheck.isPresent()) return "해당 미션, 혹은 사용자가 없습니다.";
        Mission mission = missionNullCheck.get();
        User user = userNullCheck.get();


        if(!loginedUserId.contains(mission.getMissionLeaderId())) return "해당 미션의 방장만 강퇴할수 있습니다.";

        //변경할 점 : 방은 진행 전 상태여야한다.?
        if(!mission.getMissionStatus().equals(MissionStatus.OPEN)) return "모집중인 방에서만 강퇴할 수 있습니다.";
        //방장이 자신을 추방하기는 불가능
        if(mission.getMissionLeaderId().toLowerCase().equals(userId)) return "방장은 강퇴할 수 없습니다.";

        int idLocation = mission.getMissionUsers().indexOf(userId.toLowerCase());

        // MissionUser에 userId가 없다면 잘못된 입력
        if(idLocation<0) return "미션에 등록되지 않은 user입니다.";

        // Users에서 삭제하기 로직
        String newUsers = mission.getMissionUsers().substring(0,idLocation-1)+
                mission.getMissionUsers().substring(idLocation+userId.length()+2);

        mission.updateMissionUsers(newUsers);

        missionRepository.save(mission);
        // 2. octopus_table 에서 해당하는 user, mission의 조합을 삭제한다
        Query query = em.createQuery("delete from Octopus o where o.user = :user and o.mission = :mission")
                .setParameter("user", user).setParameter("mission",mission);

        int rows = query.executeUpdate();
        // 3. 사진에서 삭제한다???
        return "성공";
    }
}
