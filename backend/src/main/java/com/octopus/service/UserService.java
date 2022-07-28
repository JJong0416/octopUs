package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.domain.dto.AvatarUpdateDto;
import com.octopus.domain.dto.PasswordUpdateDto;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.exception.SignUpException;
import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.octopus.utils.SecurityUtil.getCurrentUsername;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* Exception Bean*/
    private final SignUpException signUpException;
    private final UserNotFoundException userNotFoundException;

    /* 도메인 회원가입 */
    @Transactional
    public void signup(SignUpDto signUpDto) {
        if (userRepository.findByUserId(signUpDto.getUserId()).isPresent()) {
            throw signUpException;
        }
        signUpDto.dtoEncodePassword(passwordEncoder.encode(signUpDto.getUserPassword()));
        User user = createUser(signUpDto);
        userRepository.save(user);
    }

    @Transactional
    public void changeUserAvatar(String avatar) {
        User user = getUserInfo(getCurrentUsername().get());
        user.updateAvatar(avatar);
        userRepository.save(user);
    }

    // 패스워드 중복 체크 후, 삭제
    @Transactional
    public boolean isPasswordEqualDbPassword(String password) {
        User user = getUserInfo(String.valueOf(getCurrentUsername()));
        // 입력받은 id, pw조합이 존재한다면 - 삭제
        if (isCurrentPasswordAndDbPasswordEquals(password, user.getUserPassword())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    private User createUser(SignUpDto signUpDto) {
        return User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();
    }

    @Transactional
    public boolean checkUserNickname(String newNickname) {
        if (isUserInfoByNickname(newNickname))
            return false;
        else {
            changeUserNickname(newNickname);
            return true;
        }
    }

    @Transactional
    public void changeUserNickname(String newNickname) {
        User user = getUserInfo(String.valueOf(getCurrentUsername()));
        user.changeNickname(newNickname);
    }

    @Transactional
    public boolean changeUserPassword(PasswordUpdateDto passwordUpdateDto) {

        User user = getUserInfo(String.valueOf(getCurrentUsername()));

        if (isCurrentPasswordAndDbPasswordEquals(passwordUpdateDto.getCurrentPassword(), user.getUserPassword())) {
            user.updatePassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
            return true;
        } else {
            return false;
        }
    }

    private boolean isCurrentPasswordAndDbPasswordEquals(String currentPassword, String dbPassword) {
        return passwordEncoder.matches(currentPassword, dbPassword);
    }

    protected User getUserInfo(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });
    }

    @Transactional(readOnly = true)
    protected boolean isUserInfoByNickname(String nickname) {
        return userRepository.findByUserNickname(nickname).isPresent();
    }


}