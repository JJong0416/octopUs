package com.octopus.service;

import com.octopus.domain.User;
import com.octopus.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Spring Security에서 중요한 부분 중 하나인 UserDetailsService를 구현한
 *  CustomUserDetailsService 클래스 생성
 *  UserDetailsService를 implements 하고 UserRepository를 주입받는다.
 */
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * loadUserByUsername는 로그인 시, DB에서 유저 정보를 권한정보와 함께 가져오는 역할을 하게 된다.
     * DB에서 가져온 정보를 기준으로 해당 유저가 활성화 상태라면 그 유저들의 정보를 가지고 User 객체를 리턴한다.
     */
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