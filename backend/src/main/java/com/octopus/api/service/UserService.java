package com.octopus.api.service;

import com.octopus.domain.Mission;
import com.octopus.domain.User;
import com.octopus.dto.response.MissionInfoRes;
import com.octopus.dto.response.UserMyPageRes;
import com.octopus.exception.UserNotFoundException;
import com.octopus.api.repository.OctopusTableRepository;
import com.octopus.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final OctopusTableRepository octopusTableRepository;
    private final PasswordEncoder passwordEncoder;

    // 패스워드 중복 체크 후, 삭제
    @Transactional
    public boolean isPasswordEqualDbPassword(String password) {
        User user = getUserInfo(getCurrentUsername().get());
        // 입력받은 id, pw조합이 존재한다면 - 삭제
        if (isCurrentPasswordAndDbPasswordEquals(password, user.getUserPassword())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public UserMyPageRes getUserMyPageInfo(){

        User user = getUserInfo(getCurrentUsername().get());

        return UserMyPageRes.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userEmail(user.getUserEmail())
                .userPoint(user.getUserPoint())
                .userAvatar(user.getUserAvatar())
                .build();

    }

    @Transactional(readOnly = true)
    public User getUserInfo(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    public List<MissionInfoRes> getUserMissions(String userId){

        User user = getUserInfo(userId);
        List<Mission> missions = octopusTableRepository.findMissionByUser(user);
        List<MissionInfoRes> missionInfoRes = new ArrayList<>();

        for(Mission mission : missions){
            MissionInfoRes mid = MissionInfoRes.builder()
                    .missionNo(mission.getMissionNo())
                    .missionCode(mission.getMissionCode())
                    .missionLeaderId(mission.getMissionLeaderId())
                    .missionTitle(mission.getMissionTitle())
                    .missionType(mission.getMissionType())
                    .missionPoint(mission.getMissionPoint())
                    .missionStatus(mission.getMissionStatus())
                    .missionLimitPersonnel(mission.getMissionLimitPersonnel())
                    .missionUser(mission.getMissionUsers())
                    .missionContent(mission.getMissionContent())
                    .missionOpen(mission.getMissionOpen())
                    .build();

            missionInfoRes.add(mid);
        }
        return missionInfoRes;
    }

    private boolean isCurrentPasswordAndDbPasswordEquals(String currentPassword, String dbPassword) {
        return passwordEncoder.matches(currentPassword, dbPassword);
    }
}