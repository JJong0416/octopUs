package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.domain.dto.UserMyPageDto;
import com.octopus.domain.dto.UserUpdatePasswordDto;
import com.octopus.exception.SignUpException;
import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

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

    private User createUser(SignUpDto signUpDto) {
        return User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();
    }

    @Transactional
    public boolean updateUserNickname(String newNickname) {
        if (!isUserByNicknameExist(newNickname)) {
            return true;
        }
        changeUserNickname(newNickname);
        return false;
    }

    @Transactional
    public void changeUserNickname(String newNickname) {
        User user = getUserInfo(String.valueOf(getCurrentUsername()));
        user.changeNickname(newNickname);
    }

    @Transactional
    public boolean changeUserPassword(UserUpdatePasswordDto userUpdatePasswordDto) {

        User user = getUserInfo(getCurrentUsername().get());

        if (isCurrentPasswordAndDbPasswordEquals(userUpdatePasswordDto.getCurrentPassword(), user.getUserPassword())) {
            user.updatePassword(passwordEncoder.encode(userUpdatePasswordDto.getNewPassword()));
            return true;
        } else {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public UserMyPageDto getUserMyPageInfo(){

        User user = getUserInfo(getCurrentUsername().get());

        return UserMyPageDto.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userEmail(user.getUserEmail())
                .userPoint(user.getUserPoint())
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

}