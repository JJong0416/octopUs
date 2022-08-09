package com.octopus.api.controller;

import com.octopus.api.service.UserRegisterService;
import com.octopus.dto.request.UserSignUpReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    // Domain 회원가입
    @PostMapping("/domain")
    public ResponseEntity<HttpStatus> domainSignUp(@Valid @RequestBody UserSignUpReq userSignUpReq) {
        userRegisterService.signup(userSignUpReq);
        return ResponseEntity.ok().build();
    }

/*
    // 카카오 회원가입
    @PostMapping("/register/kakao")
    public ResponseEntity<HttpStatus> kakaoSignUp(@Valid @RequestBody SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return ResponseEntity.ok().build();
    }
*/

    @GetMapping("/check/id/{id}")
    public ResponseEntity<Boolean> checkDuplicatedId(@PathVariable String id) {
        return new ResponseEntity<>(userRegisterService.isDuplicatedUserId(id), HttpStatus.OK);
    }

    @GetMapping("/check/nickname/{nickname}")
    public ResponseEntity<Boolean> checkDuplicatedNickname(@PathVariable String nickname) {
        return new ResponseEntity<>(userRegisterService.isDuplicatedUserNickname(nickname), HttpStatus.OK);
    }
}
