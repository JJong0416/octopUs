package com.octopus.api.controller;

import com.octopus.dto.request.LoginReq;
import com.octopus.dto.response.TokenRes;
import com.octopus.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 권한에 대한 Controller (Login 포함)
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/domain")
    public ResponseEntity<TokenRes> loginFromDomain(@Valid @RequestBody LoginReq loginReq) {
        String jwt = authService.createJwtString(loginReq);

        return new ResponseEntity<>(
                new TokenRes(jwt), authService.createJwtHttpHeader(jwt), HttpStatus.OK);
    }

/*    @PostMapping("/login/kakao")
    public ResponseEntity<TokenDto> loginFromKakao(@Valid @RequestBody LoginDto loginDto) {
        String jwt = authService.createJwtString(loginDto);

        return new ResponseEntity<>(
                new TokenDto(jwt), authService.createJwtHttpHeader(jwt), HttpStatus.OK);
    }
*/
}