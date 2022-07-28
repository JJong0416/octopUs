package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.domain.dto.LoginDto;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.exception.SignUpException;
import com.octopus.exception.UserNotFoundException;
import com.octopus.repository.UserRepository;
import com.octopus.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional(readOnly = true)
    public User getUserWithUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() ->{
            throw userNotFoundException;
        });
    }

    @Transactional
    public boolean checkUserIdAndPw(LoginDto logindto){
        Optional<User> tmp = userRepository.findByUserId(logindto.getUserId());
        // 입력받은 id, pw조합이 존재한다면 - 삭제
        if(tmp.isPresent() && passwordEncoder.matches(logindto.getUserPassword(), tmp.get().getUserPassword())) {
            userRepository.delete(tmp.get());
            return true;
        }
        return false;
    }

    private User createUser(SignUpDto signUpDto) {
        return User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();
    }
}