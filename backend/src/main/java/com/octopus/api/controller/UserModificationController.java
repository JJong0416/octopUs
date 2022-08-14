package com.octopus.api.controller;

import com.octopus.api.service.UserModificationService;
import com.octopus.dto.request.AvatarReq;
import com.octopus.dto.request.PointReq;
import com.octopus.dto.request.UserUpdateInfoReq;
import com.octopus.dto.request.UserUpdatePasswordReq;
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

    private final UserModificationService userModificationService;

    // 아바타 수정
    @PatchMapping("/avatar")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyAvatar(@RequestBody AvatarReq avatarReq) {
        userModificationService.changeUserAvatar(avatarReq);
        return ResponseEntity.ok().build();
    }

    // 닉네임 변경
    @PatchMapping("/nickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyNickname(@RequestBody UserUpdateInfoReq userUpdateInfoReq) {
        userModificationService.changeUserNickname(userUpdateInfoReq.getUserNickname());
        return ResponseEntity.ok().build();
    }

    // 패스워드 변경
    @PatchMapping("/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> modifyPassword(@Valid @RequestBody UserUpdatePasswordReq userUpdatePasswordReq) {
        userModificationService.changeUserPassword(userUpdatePasswordReq);
        return ResponseEntity.ok().build();
    }

    // 포인트 충전
    @PatchMapping("/point/charge")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> pointCharge(@RequestBody PointReq pointReq) {
        userModificationService.plusUserPoint(pointReq.getPoint());
        return ResponseEntity.ok().build();
    }


    // 포인트 환급
    @PatchMapping("/point/refund")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> pointRefund(@RequestBody PointReq pointReq) {
        userModificationService.minusUserPoint(pointReq.getPoint());
        return ResponseEntity.ok().build();
    }
}
