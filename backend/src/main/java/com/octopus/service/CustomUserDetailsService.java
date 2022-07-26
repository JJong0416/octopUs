package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserDetailsService를 implements 받는다.
 */
@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final String COMMON_USER = "ROLE_USER";

    // TODO: 2022-07-26 CustomException 생성
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .map(user -> createUser(userId, user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
        return new org.springframework.security.core.userdetails.User(user.getUserId(),
                user.getUserPassword(),
                List.of(new SimpleGrantedAuthority(COMMON_USER))
        );
    }
}
