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
    public String deleteUserFromMission(String userId, Long missionNo){
        //변경할 점 : 방은 진행 전 상태여야한다.?
        // 1. mission table의 MissionUser에서 해당하는 id의 이름을 제거한다.
        Optional<Mission> missionNullCheck = missionRepository.findByMissionNo(missionNo);
        Optional<User> userNullCheck = userRepository.findByUserId(userId);
        System.out.println("mission : "+missionNullCheck.get());
        System.out.println("user : "+userNullCheck.get());
        if(!missionNullCheck.isPresent() || !userNullCheck.isPresent()) return "해당 미션이 없습니다.";
        Mission mission = missionNullCheck.get();
        User user = userNullCheck.get();
        //방장이 자신을 추방하기는 불가능
        if(mission.getMissionLeaderId().toLowerCase().equals(userId)) return "방장은 강퇴할 수 없습니다.";

        int idLocation = mission.getMissionUsers().indexOf(userId.toLowerCase());
        System.out.println("idLocation :"+idLocation);
        // MissionUser에 userId가 없다면 잘못된 입력
        if(idLocation<0){
            return "미션에 등록되지 않은 user입니다.";
        }
        // Users에서 삭제하기 로직(확인필요)
        String newUsers = mission.getMissionUsers().substring(0,idLocation-1)+
                mission.getMissionUsers().substring(idLocation+userId.length()+2);
        System.out.println("newUsers : "+newUsers);
        mission.updateMissionUsers(newUsers);
        System.out.println("missionUsers입력확인 :"+mission.getMissionUsers());
        System.out.println("mission확인 :"+mission);
        missionRepository.save(mission);
        // 2. octopus_table 에서 해당하는 userNo, missionNo의 조합을 삭제한다
//        Octopus current = octopus_tableRepository.findByMissionAndUser(mission,user).get();
        Octopus current = octopus_tableRepository.findById(new OctopusPK(user,mission)).get();
        System.out.println(current.getMission() +"   ====   "+current.getUser());
//        octopus_tableRepository.delete(current);
//        octopus_tableRepository.deleteById(new OctopusPK(user,mission));
        Query query = em.createQuery("delete from Octopus o where o.user = :user_no and o.mission = :mission_no")
                .setParameter("user_no", current.getUser()).setParameter("mission_no",current.getMission());

        int rows = query.executeUpdate();
        // 3. 사진에서 삭제한다???
        return "성공";
    }
}
