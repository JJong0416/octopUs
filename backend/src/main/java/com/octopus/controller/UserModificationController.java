package com.octopus.controller;

import com.octopus.domain.dto.UserUpdateInfoDto;
import com.octopus.domain.dto.UserUpdatePasswordDto;
import com.octopus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/modification")
@RequiredArgsConstructor
public class UserModificationController {

    private final UserService userService;

    // 아바타 수정
    @PatchMapping("/avatar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyAvatar(@RequestBody UserUpdateInfoDto userUpdateInfoDto) {
        userService.changeUserAvatar(userUpdateInfoDto.getUserAvatar());
        return ResponseEntity.ok().build();
    }

    // 닉네임 변경
    @PatchMapping("/nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyNickname(@RequestBody UserUpdateInfoDto userUpdateInfoDto) {
        return (userService.updateUserNickname(userUpdateInfoDto.getUserNickname()))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 패스워드 변경
    @PatchMapping("/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyPassword(@Valid @RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        return (userService.changeUserPassword(userUpdatePasswordDto))
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }
}
