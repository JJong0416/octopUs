package com.octopus.api.controller;

import com.octopus.api.service.EmailTokenService;
import com.octopus.api.service.UserService;
import com.octopus.dto.request.UserFindPasswordReq;
import com.octopus.dto.request.UserUpdatePasswordReq;
import com.octopus.dto.response.MissionInfoRes;
import com.octopus.dto.response.UserMyPageRes;
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
    private final EmailTokenService emailTokenService;

    // 잃어버린 패스워드 찾기
    @PostMapping("/find-pw")
    public ResponseEntity<HttpStatus> findPasswordThroughEmail(@Valid @RequestBody UserFindPasswordReq userFindPasswordReq){
        userService.findPasswordThroughEmail(userFindPasswordReq);
        return ResponseEntity.ok().build();
    }

    // TODO: 2022-08-07 Controller 리팩토링
    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> deleteUser(@Valid @RequestBody UserUpdatePasswordReq userUpdatePasswordReq) {
        userService.isPasswordEqualDbPassword(userUpdatePasswordReq.getCurrentPassword());
        return ResponseEntity.ok().build();
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