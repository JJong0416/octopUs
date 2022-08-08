package com.octopus.api.service;

import com.octopus.api.repository.UserRepository;
import com.octopus.domain.User;
import com.octopus.dto.request.UserSignUpReq;
import com.octopus.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* 도메인 회원가입 */
    @Transactional
    public void signup(UserSignUpReq userSignUpReq) {
        if (userRepository.findByUserId(userSignUpReq.getUserId()).isPresent()) {
            throw new UserNotFoundException();
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
}
