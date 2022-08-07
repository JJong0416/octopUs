package com.octopus.api.service;

import com.octopus.domain.User;
import com.octopus.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserDetailsService를 구현한 CustomUserDetailsService
 */
// TODO: 2022-07-26 1.CustomException 만들기 / 2. List.of 나만 저러는지 확인하기
@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) {
        return userRepository.findByUserId(userId)
                .map(this::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    
    private org.springframework.security.core.userdetails.User createUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUserId(),
                user.getUserPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}