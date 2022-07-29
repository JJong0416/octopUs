package com.octopus.controller;

import com.octopus.domain.User;
import com.octopus.domain.dto.UserMyPageDto;
import com.octopus.domain.dto.UserUpdateInfoDto;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.domain.dto.UserUpdatePasswordDto;
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
    public ResponseEntity<HttpStatus> modifyAvatar(@RequestBody UserUpdateInfoDto userUpdateInfoDto) {
        userService.changeUserAvatar(userUpdateInfoDto.getUserAvatar());
        return ResponseEntity.ok().build();
    }
    // 닉네임 변경
    @PatchMapping("/user/modify/nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> changeNickname(@RequestBody UserUpdateInfoDto userUpdateInfoDto) {
        System.out.println(userUpdateInfoDto.toString());
        return (userService.updateUserNickname(userUpdateInfoDto.getUserNickname()))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 패스워드 변경
    @PatchMapping("/user/modify/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> checkPassword(@Valid @RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        System.out.println("hellloooooo");
        return (userService.changeUserPassword(userUpdatePasswordDto))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 유저 삭제
    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> userDelete(@RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        return userService.isPasswordEqualDbPassword(userUpdatePasswordDto.getCurrentPassword())
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 유저 myPage
    @GetMapping("/user/info")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserMyPageDto> userMyPage() {
        return ResponseEntity.ok(userService.getUserMyPageInfo());
    }

/*  테스트용 API
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> searchUser(@PathVariable String userId){
        User userInfo = userService.getUserInfo(userId);
        return ResponseEntity.ok(userInfo);
    }
    */
}