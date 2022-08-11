package com.octopus.api.controller;

import com.octopus.api.service.AuthService;
import com.octopus.api.service.OAuthService;
import com.octopus.dto.layer.KakaoProfileDto;
import com.octopus.dto.response.TokenRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final AuthService authService;
    private final OAuthService oAuthService;

//     카카오 회원가입 및 로그인
    @GetMapping("/oauth2/code/kakao")
    public ResponseEntity<TokenRes> loginOrSignupFromKakao(@RequestParam String code) {
        String jwt = authService.createJwtString(oAuthService.kakaoLoginOrRegister(code));

        return new ResponseEntity<>(
                new TokenRes(jwt), authService.createJwtHttpHeader(jwt), HttpStatus.OK);
    }
}
