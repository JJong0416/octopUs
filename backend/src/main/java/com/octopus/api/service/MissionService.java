package com.octopus.api.service;

import com.octopus.api.repository.MissionRepository;
import com.octopus.api.repository.OctopusTableRepository;
import com.octopus.api.repository.UserRepository;
import com.octopus.domain.Mission;
import com.octopus.domain.User;
import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import com.octopus.dto.layer.MissionListDto;
import com.octopus.dto.request.MissionCreateReq;
import com.octopus.dto.response.MissionRes;
import com.octopus.exception.CustomException;
import com.octopus.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final OctopusTableRepository octopusTableRepository;

    /* 미션 코드 중복은 안했음. */
    @Transactional
    public Long createMission(MissionCreateReq missionCreateReq) {
        User user = userRepository.findByUserId(getCurrentUsername().get()).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        /* 미션에 방장 정보와 String 으로 유저 넣기 */
        missionCreateReq.addMissionLeaderIdAndUser(user.getUserId(), user.getUserNickname());

        /* DTO 를 통한 미션 생성 */
        Mission mission = Mission.createMission()
                .missionCreateReq(missionCreateReq)
                .build();
        Mission savedMission = missionRepository.save(mission);

        octopusTableRepository.insertToOctopusTable(user.getUserNo(), mission.getMissionNo());
        return savedMission.getMissionNo();
    }
    @Transactional(readOnly = true)
    public List<MissionListDto> getAllMissions() {
        List<Mission> missions  = missionRepository.findByMissionStatusAndMissionOpen(MissionStatus.OPEN, MissionOpenType.OPEN_ROOM);

        return changeMissionListToMissionListDtoList(missions);

    }
    @Transactional(readOnly = true)
    public List<MissionListDto> getNewMissions() {
        List<Mission> missions  = missionRepository.findTop5ByMissionStatusAndMissionOpen(
                Sort.by(Sort.Direction.DESC, "missionNo"),
                MissionStatus.OPEN,
                MissionOpenType.OPEN_ROOM
        );
        return changeMissionListToMissionListDtoList(missions);
    }

    @Transactional
    public List<MissionListDto> getHotMissions() {
        //모집중이면서 공개방 리스트가져오기
        List<Mission> missions = missionRepository.findByMissionStatusAndMissionOpen(MissionStatus.OPEN, MissionOpenType.OPEN_ROOM);
        List<MissionListDto> missionList = missions.stream()
                .filter(mission -> mission.getMissionLimitPersonnel() -
                        (mission.getMissionUsers().length() - (mission.getMissionUsers().replaceAll(",", "").length()) + 1) > 0)
                .sorted(missionComparator)
                .map(mission -> MissionListDto.builder()
                        .missionNo(mission.getMissionNo())
                        .missionCode(mission.getMissionCode())
                        .missionTitle(mission.getMissionTitle())
                        .missionLeaderId(mission.getMissionLeaderId())
                        .missionContent(mission.getMissionContent())
                        .missionLeaderAvatar(userRepository.findByUserId(mission.getMissionLeaderId()).orElseThrow(() -> {
                            throw new CustomException(ErrorCode.USER_NOT_FOUND);
                        }).getUserAvatar())
                        .build())
                .collect(Collectors.toList());

        return missionList.size() < 5 ? missionList : missionList.subList(0, 5);
    }
    @Transactional(readOnly = true)
    public List<MissionListDto> changeMissionListToMissionListDtoList(List<Mission> missions){
        return missions.stream().map(mission -> MissionListDto.builder()
                .missionNo(mission.getMissionNo())
                .missionCode(mission.getMissionCode())
                .missionTitle(mission.getMissionTitle())
                .missionLeaderId(mission.getMissionLeaderId())
                .missionContent(mission.getMissionContent())
                .missionLeaderAvatar(userRepository.findByUserId(mission.getMissionLeaderId()).orElseThrow(() -> {
                    throw new CustomException(ErrorCode.USER_NOT_FOUND);
                }).getUserAvatar())
                .build()).collect(Collectors.toList());
    }

    Comparator<Mission> missionComparator =
            Comparator.comparingInt(m -> (m.getMissionLimitPersonnel() - (m.getMissionUsers().length()
                    - (m.getMissionUsers().replaceAll(",", "").length()) + 1)));

    @Transactional(readOnly = true)
    public List<MissionRes> getMissionByMissionCode(String missionCode) {
        return missionRepository.findMissionsByMissionCodeAndMissionOpen(missionCode, MissionOpenType.OPEN_ROOM);
    }

    @Transactional(readOnly = true)
    public List<MissionRes> getMissionByMissionTitle(String missionTitle) {
        return missionRepository.findMissionsByMissionTitleAndMissionOpen(missionTitle, MissionOpenType.OPEN_ROOM);
    }

    @Transactional(readOnly = true)
    public List<MissionRes> getMissionsByMissionType(MissionType missionType) {
        return missionRepository.findMissionsByMissionType(missionType);
    }
}
