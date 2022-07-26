package com.octopus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

// 간단한 유틸리티 메소드를 만들기 위해 SecurityUtil 클래스를 생성
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {
    }

    // getCurrentUsername 메소드 하나를 가지고 있는 util이다.
    // 해당 메소드는 Security Context의 Authentication 객체를 username을 리턴해주는 간단한 유틸성 메소드다.
    // 그리고 Security Cotext에 Authentication 객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에 Request가 들어올 때
    // SecurityContext에 Authentication 객체를 저장해서 사용하게 된다. 이때 저장된 객체가 꺼내지게 되는 것이다.
    public static Optional<String> getCurrentUsername() {
        // 시큐리티 Context에서 Authentication 객체를 꺼내와서 authentication 객체를 통해서 usename을 꺼내오는 메소드
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
}