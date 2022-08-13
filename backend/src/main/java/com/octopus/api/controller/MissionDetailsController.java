package com.octopus.api.controller;

import com.octopus.api.service.MissionDetailsService;
import com.octopus.dto.request.AuthenticationReq;
import com.octopus.dto.request.MissionTimeReq;
import com.octopus.dto.request.MissionUpdateInfoReq;
import com.octopus.dto.response.MissionRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mission/{missionNo}")
@RequiredArgsConstructor
public class MissionDetailsController {

    private final MissionDetailsService missionDetailsService;

    //미션 정보 가져오기
    @GetMapping
    public ResponseEntity<MissionRes> getMission(@PathVariable long missionNo) {
        MissionRes missionInfo = missionDetailsService.getMissionDtoByMissionNo(missionNo);
        return ResponseEntity.ok(missionInfo);
    }

    //미션 수정하기
    @PatchMapping
    public ResponseEntity<HttpStatus> modifyMission(
            @Valid @RequestBody MissionUpdateInfoReq missionUpdateInfoReq,
            @PathVariable long missionNo
    ) {
        missionDetailsService.modifyMission(missionUpdateInfoReq, missionNo);
        return ResponseEntity.ok().build();
    }

    //미션 time 생성
    @PostMapping("/mission-time")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMissionTime(
            @PathVariable Long missionNo,
            @Valid @RequestBody MissionTimeReq missionTimeReq
    ) {
        missionDetailsService.createMissionTime(missionNo, missionTimeReq);
        return ResponseEntity.ok().build();
    }

    //인증 시작 시간 & 끝나는 시간 생성하기
    @PostMapping("/authentication")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createAuthentication(
            @PathVariable Long missionNo,
            @Valid @RequestBody AuthenticationReq authenticationReq
    ) {
        missionDetailsService.createAuthentication(missionNo, authenticationReq);
        return ResponseEntity.ok().build();
    }

    //미션에 참여하기
    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> addUserToMission(@PathVariable long missionNo) {
        missionDetailsService.joinMission(missionNo);
        return ResponseEntity.ok().build();
    }

    //현재 미션에 참여 중인 유저 목록
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List> missionUsers(@PathVariable Long missionNo) {
        return new ResponseEntity<>(
                missionDetailsService.getMissionUsers(missionNo), HttpStatus.OK);
    }

    //참여 유저 강퇴하기
    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity kickOutUser(
            @PathVariable String userNickname,
            @PathVariable Long missionNo
    ) {
        missionDetailsService.deleteUserFromMission(userNickname, missionNo);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reward")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity distributeReward(@PathVariable Long missionNo){
        missionDetailsService.rewardDistribution(missionNo);
        return ResponseEntity.ok().build();
    }
}
