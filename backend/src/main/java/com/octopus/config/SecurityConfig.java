package com.octopus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() { // h2에 대한 접근에는 인증없이 진행할 수 있도록
//        return (web -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico"));
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에 대한 접근 제한을 설정
                .antMatchers("/**").permitAll() // api/hello는 인증없이 접근을 허용
                .anyRequest().authenticated(); // 나머지 요청에 대해서는 모두 인증이 필요하다.

        return http.build();
    }
}