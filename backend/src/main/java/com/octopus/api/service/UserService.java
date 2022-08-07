package com.octopus.api.service;

import com.octopus.domain.Mission;
import com.octopus.domain.User;
import com.octopus.dto.response.MissionInfoReq;
import com.octopus.dto.request.UserSignUpReq;
import com.octopus.dto.response.UserMyPageRes;
import com.octopus.dto.request.UserUpdatePasswordReq;
import com.octopus.exception.SignUpException;
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

    /* Exception Bean*/
    private final SignUpException signUpException;
    private final UserNotFoundException userNotFoundException;

    /* 도메인 회원가입 */
    @Transactional
    public void signup(UserSignUpReq userSignUpReq) {
        if (userRepository.findByUserId(userSignUpReq.getUserId()).isPresent()) {
            throw signUpException;
        }
        userSignUpReq.dtoEncodePassword(passwordEncoder.encode(userSignUpReq.getUserPassword()));
        User user = createUser(userSignUpReq);
        userRepository.save(user);
    }

    @Transactional
    public void changeUserAvatar(String userAvatar) {
        User user = getUserInfo(getCurrentUsername().get());
        user.updateAvatar(userAvatar);
    }

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

    private User createUser(UserSignUpReq userSignUpReq) {
        return User.signUpBuilder()
                .signUpDto(userSignUpReq)
                .build();
    }

    @Transactional
    public boolean updateUserNickname(String newNickname) {
        if (!isUserByNicknameExist(newNickname)) {
            System.out.println("1234");
            User user = getUserInfo(getCurrentUsername().get());
            user.changeNickname(newNickname);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeUserPassword(UserUpdatePasswordReq userUpdatePasswordReq) {

        User user = getUserInfo(getCurrentUsername().get());

        if (isCurrentPasswordAndDbPasswordEquals(userUpdatePasswordReq.getCurrentPassword(), user.getUserPassword())) {
            user.updatePassword(passwordEncoder.encode(userUpdatePasswordReq.getNewPassword()));
            return true;
        } else {
            return false;
        }
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
    protected boolean isUserByNicknameExist(String nickname) {
        return userRepository.findByUserNickname(nickname).isPresent();
    }

    private boolean isCurrentPasswordAndDbPasswordEquals(String currentPassword, String dbPassword) {
        return passwordEncoder.matches(currentPassword, dbPassword);
    }

    @Transactional(readOnly = true)
    public List<MissionInfoReq> getUserMissions(String userId){

        User user = getUserInfo(userId);
        List<Mission> missions = octopusTableRepository.findMissionByUser(user);
        List<MissionInfoReq> missionInfoReq = new ArrayList<>();

        for(Mission mission : missions){
            MissionInfoReq mid = MissionInfoReq.builder()
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

            missionInfoReq.add(mid);
        }

        return missionInfoReq;

    }



}