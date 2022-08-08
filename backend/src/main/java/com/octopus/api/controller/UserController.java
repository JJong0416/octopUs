package com.octopus.api.controller;

import com.octopus.dto.response.MissionInfoRes;
import com.octopus.dto.response.UserMyPageRes;
import com.octopus.dto.request.UserUpdatePasswordReq;
import com.octopus.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // TODO: 2022-08-07 Controller 리팩토링
    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> deleteUser(@Valid @RequestBody UserUpdatePasswordReq userUpdatePasswordReq) {
        return userService.isPasswordEqualDbPassword(userUpdatePasswordReq.getCurrentPassword())
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    // 유저 myPage
    @GetMapping("/user/info")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserMyPageRes> userMyPage() {
        return ResponseEntity.ok(userService.getUserMyPageInfo());
    }

    //참여 미션 목록
    @GetMapping("/user/mission/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<MissionInfoRes>> userJoinedMissions(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserMissions(userId));
    }
}