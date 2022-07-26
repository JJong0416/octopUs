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

    @Transactional
    public User signup(SignUpDto signUpDto) {
        // 회원가입이기 때문에, Repository를 통해 userDto를 통해 DB에 존재하면 RuntimeException 터트리고,
        if (userRepository.findByUserId(signUpDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 계정 생성
        User user = User.signUpBuilder()
                .signUpDto(signUpDto)
                .build();

        return userRepository.save(user);
    }

    // username을 파라미터로 받아서 해당 객체를 db에 찔러서 권한정보까지 가져오는 것이고,
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String userId) {
        return userRepository.findByUserId(userId);
    }

    // 현재 SecurityContext에 저장된 username의 정보만 가져오게 된다. 둘 나눈 이유는 권한 검증에 대한 부분을 테스트하기 위해서
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findByUserId);
    }
}