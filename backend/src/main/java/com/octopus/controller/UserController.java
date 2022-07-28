package com.octopus.controller;

import com.octopus.domain.dto.AvatarUpdateDto;
import com.octopus.domain.dto.PasswordUpdateDto;
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

    // 아바타 수정
    @PatchMapping("/user/modify/avatar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyAvatar(String avatar) {
        System.out.println("hello!!!!!");
        userService.changeUserAvatar(avatar);

        return ResponseEntity.ok().build();
    }

    // 유저 삭제
    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> userDelete(@RequestParam String password) {
        return userService.isPasswordEqualDbPassword(password)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }


    @PutMapping("/user/modify/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> checkPassword(@Valid @RequestBody PasswordUpdateDto passwordUpdateDto) {
        return (userService.changeUserPassword(passwordUpdateDto))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    @PutMapping("/user/modify/nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> changeNickname(@RequestParam String newNickname) {
        return (userService.checkUserNickname(newNickname))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }
}