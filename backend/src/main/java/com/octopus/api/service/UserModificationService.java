package com.octopus.api.service;

import com.octopus.api.repository.UserRepository;
import com.octopus.domain.User;
import com.octopus.dto.request.AvatarReq;
import com.octopus.dto.request.UserUpdatePasswordReq;
import com.octopus.exception.CustomException;
import com.octopus.exception.ErrorCode;
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
    private final Integer changeAvatarPoint = 500;

    @Transactional
    public void changeUserAvatar(AvatarReq avatarReq) {
        User user = getUserInfo(getCurrentUsername().get());

        String[] userAvatar = user.getUserAvatar().split(", ");

        boolean isChange = false;
        if (Integer.parseInt(userAvatar[0]) != avatarReq.getAvatarColor()
                || Integer.parseInt(userAvatar[1]) != avatarReq.getAvatarFace()
                || Integer.parseInt(userAvatar[2]) != avatarReq.getAvatarHat()
                || Integer.parseInt(userAvatar[3]) != avatarReq.getAvatarPet()) {
            isChange = true;
        }
        if(isChange && user.getUserPoint() < 500){
            throw new CustomException(ErrorCode.POINT_LACK_ERROR);
        }
        if (isChange) {
            user.updatePoint(user.getUserPoint() - changeAvatarPoint);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(avatarReq.getAvatarColor()).append(", ")
                .append(avatarReq.getAvatarFace()).append(", ")
                .append(avatarReq.getAvatarHat()).append(", ")
                .append(avatarReq.getAvatarPet());
        user.updateAvatar(sb.toString());


    }

    @Transactional
    public void changeUserNickname(String newNickname) {
        if (!isUserByNicknameExist(newNickname)) {
            User user = getUserInfo(getCurrentUsername().get());
            user.changeNickname(newNickname);
        } else {
            throw new CustomException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }
    }

    @Transactional
    public void changeUserPassword(UserUpdatePasswordReq userUpdatePasswordReq) {

        User user = getUserInfo(getCurrentUsername().get());

        if (isCurrentPasswordAndDbPasswordEquals(userUpdatePasswordReq.getCurrentPassword(), user.getUserPassword())) {
            user.updatePassword(passwordEncoder.encode(userUpdatePasswordReq.getNewPassword()));
        } else {
            throw new CustomException(ErrorCode.PASSWORD_NOT_VALID);
        }
    }

    @Transactional(readOnly = true)
    public User getUserInfo(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
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
