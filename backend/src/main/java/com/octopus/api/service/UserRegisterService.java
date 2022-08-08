package com.octopus.api.service;

import com.octopus.api.repository.UserRepository;
import com.octopus.domain.User;
import com.octopus.dto.layer.UserListDto;
import com.octopus.dto.request.UserSignUpReq;
import com.octopus.exception.CustomException;
import com.octopus.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* 도메인 회원가입 */
    @Transactional
    public void signup(UserSignUpReq userSignUpReq) {
        List<UserListDto> allUsers =
                userRepository.findUserByUserIdOrUserEmailOrUserNickname(
                        userSignUpReq.getUserId(),
                        userSignUpReq.getUserEmail(),
                        userSignUpReq.getUserNickname());

        for (UserListDto user : allUsers) {
            if (user.getUserId().equals(userSignUpReq.getUserId())) {
                throw new CustomException(ErrorCode.ID_ALREADY_EXISTS);
            } else if (user.getUserNickname().equals(userSignUpReq.getUserNickname())) {
                throw new CustomException(ErrorCode.NICKNAME_ALREADY_EXISTS);
            } else if (user.getUserEmail().equals(userSignUpReq.getUserEmail())) {
                throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
            }
        }
        userSignUpReq.dtoEncodePassword(passwordEncoder.encode(userSignUpReq.getUserPassword()));
        User user = createUser(userSignUpReq);
        userRepository.save(user);
    }

    private User createUser(UserSignUpReq userSignUpReq) {
        return User.signUpBuilder()
                .userSignUpReq(userSignUpReq)
                .build();
    }

    /* 카카오 회원가입 */
}
