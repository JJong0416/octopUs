package com.octopus.controller;

import com.octopus.domain.User;
import com.octopus.domain.dto.LoginDto;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 회원가입, 회원정보 수정에 대한 컨트롤러(Login X)
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Domain 회원가입
    @PostMapping("/register/domain")
    public ResponseEntity<HttpStatus> signup(@Valid @RequestBody SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return ResponseEntity.ok().build();
    }


    // 로그인 권한 테스트
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getUserInfo(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserWithUserId(userId));
    }

    @PostMapping("/users/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> userDelete(@PathVariable String userId, @RequestBody LoginDto loginDto){
        return userService.checkUserIdAndPw(new LoginDto(userId, loginDto.getUserPassword()))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }
}