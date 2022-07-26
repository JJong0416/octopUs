package com.octopus.config;

import com.octopus.jwt.JwtAccessDeniedHandler;
import com.octopus.jwt.JwtAuthenticationEntryPoint;
import com.octopus.jwt.JwtSecurityConfig;
import com.octopus.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity // 기본적인 웹 보안을 활성화하겠다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해서 미리 적용
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ){
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // 패스워드 인코더 Bean으로 등록하기
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // h2에 대한 접근에는 인증없이 진행할 수 있도록
        return (web -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()

                // exceptionHandling할 때, authenticationEntryPoint와 accessDeniedHandler를 우리가 만든걸로 추가해주기
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // h2 콘솔을 사용하기 위해 설정
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()  // authorizeRequest는 HttpServletRequest를 사용하는 요청들에 대한 접근제한 설정
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/check1").permitAll()
                /* 토큰을 받기위한 로그인 api랑 회원 가입을 위한 api는 둘 다 토큰이 없는 상태에서 진행되기 때문에 열어준다.*/
                .anyRequest().authenticated()

                .and()
                // 그리고 마지막으로 JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적응을 해준다.
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
