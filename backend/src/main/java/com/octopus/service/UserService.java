package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.domain.dto.SignUpDto;
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

    // TODO: 2022-07-26 CustomException 만들기
    @Transactional
    public User signup(SignUpDto signUpDto) {
        if (userRepository.findByUserId(signUpDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        signUpDto.dtoEncodePassword(passwordEncoder.encode(signUpDto.getUserPassword()));
        User user = User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    // 현재 SecurityContext에 저장된 username의 정보만 가져오게 한다.
    // 둘 나눈 이유는 권한 검증에 대한 부분을 테스트하기 위해서
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithUserId() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUserId);
    }
}