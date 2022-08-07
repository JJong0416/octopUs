package com.octopus.controller;

import com.octopus.domain.dto.SignUpDto;
import com.octopus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserService userService;

    // Domain 회원가입
    @PostMapping("/register/domain")
    public ResponseEntity<HttpStatus> domainSignUp(@Valid @RequestBody SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return ResponseEntity.ok().build();
    }

    // 카카오 회원가입
/*    @PostMapping("/register/kakao")
    public ResponseEntity<HttpStatus> kakaoSignUp(@Valid @RequestBody SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return ResponseEntity.ok().build();
    }
*/
}
