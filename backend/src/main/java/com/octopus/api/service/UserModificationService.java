package com.octopus.api.service;

import com.octopus.api.repository.UserRepository;
import com.octopus.domain.User;
import com.octopus.dto.request.UserUpdatePasswordReq;
import com.octopus.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.octopus.utils.SecurityUtils.getCurrentUsername;

@Service
@RequiredArgsConstructor
public class UserModificationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changeUserAvatar(String userAvatar) {
        User user = getUserInfo(getCurrentUsername().get());
        user.updateAvatar(userAvatar);
    }

    @Transactional
    public boolean changeUserNickname(String newNickname) {
        if (!isUserByNicknameExist(newNickname)) {
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
