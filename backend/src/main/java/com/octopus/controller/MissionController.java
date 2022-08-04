package com.octopus.controller;

import com.octopus.domain.dto.*;
import com.octopus.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 비활성화 미션 생성
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMission(
            @Valid @RequestBody MissionCreateDto missionCreateDto
    ) {
        missionService.createMission(missionCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/new")
    public ResponseEntity<List> newMissions() {
        return ResponseEntity.ok(missionService.getNewMissions());
    }

    @GetMapping("/{missionNo}")
    public ResponseEntity<MissionDto> getMission(@PathVariable long missionNo) {
        MissionDto missionInfo = missionService.getMissionDtoByMissionNo(missionNo);
        return ResponseEntity.ok(missionInfo);
    }

    @PatchMapping("/{missionNo}")
    public ResponseEntity<HttpStatus> modifyMission(
            @Valid @RequestBody MissionUpdateInfoDto missionUpdateInfoDto, @PathVariable long missionNo) {
        missionService.modifyMission(missionUpdateInfoDto, missionNo);
        return ResponseEntity.ok().build();
    }

    //join
    // octopus 테이블에 missionNo랑 userNo 추가
    // mission에다가 add user할 거
    @PostMapping("/{missionNo}/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> addUserToMission(
            @PathVariable long missionNo
    ) {
        missionService.joinMission(missionNo);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{missionNo}/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity kickOutUser(@PathVariable String userId, @PathVariable Long missionNo, @RequestBody String loginedUserId) {
        // 지금로그인된 사용자의 아이디를 받아오는 것이 아닌 토큰에서 id를 꺼내오는거로 변경 필요
        String message = missionService.deleteUserFromMission(userId, missionNo, loginedUserId);
        return message.equals("성공")
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{missionNo}/calender/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> calenderUserInfoDetail(@PathVariable Long missionNo, @PathVariable String userId) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{missionNo}/mission-time")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMissionTime(
            @PathVariable Long missionNo, @Valid @RequestBody MissionTimeDto missionTimeDto
    ) {
        return missionService.createMissionTime(missionNo, missionTimeDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();

    }

    @GetMapping("/hot")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List> hotMissions() {
        return new ResponseEntity<>(
                missionService.getHotMissions(), HttpStatus.OK);
    }

    @GetMapping("/{missionNo}/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<MissionPictureRes>> getMissionPictures(@PathVariable Long missionNo) {
        System.out.println("hello");
        return ResponseEntity.ok(missionService.getMissionPictureMatchingUser(missionNo));
    }

    @PostMapping("/{missionNo}/authentication")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createAuthentication(
            @PathVariable Long missionNo,
            @Valid @RequestBody AuthenticationDto authenticationDto
    ) {
        return missionService.createAuthentication(missionNo, authenticationDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

}
