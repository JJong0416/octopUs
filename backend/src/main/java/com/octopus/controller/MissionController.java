package com.octopus.controller;

import com.octopus.domain.dto.*;
import com.octopus.domain.dto.response.CalenderUserInfoRes;
import com.octopus.service.MissionService;
import com.octopus.service.UserService;
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

    //new 미션 5개 가져오기
    @GetMapping("/new")
    public ResponseEntity<List> newMissions() {
        return ResponseEntity.ok(missionService.getNewMissions());
    }

    //미션 정보 가져오기
    @GetMapping("/{missionNo}")
    public ResponseEntity<MissionDto> getMission(@PathVariable long missionNo) {
        MissionDto missionInfo = missionService.getMissionDtoByMissionNo(missionNo);
        return ResponseEntity.ok(missionInfo);
    }

    //미션 수정하기
    @PatchMapping("/{missionNo}")
    public ResponseEntity<HttpStatus> modifyMission(
            @Valid @RequestBody MissionUpdateInfoDto missionUpdateInfoDto, @PathVariable long missionNo) {
        missionService.modifyMission(missionUpdateInfoDto, missionNo);
        return ResponseEntity.ok().build();
    }

    //미션에 참여하기
    @PostMapping("/{missionNo}/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> addUserToMission(
            @PathVariable long missionNo
    ) {
        missionService.joinMission(missionNo);
        return ResponseEntity.ok().build();
    }

    //참여 유저 강퇴하기
    @DeleteMapping("/{missionNo}/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity kickOutUser(@PathVariable String userId, @PathVariable Long missionNo, @RequestBody String loginedUserId) {
        // 지금로그인된 사용자의 아이디를 받아오는 것이 아닌 토큰에서 id를 꺼내오는거로 변경 필요
        String message = missionService.deleteUserFromMission(userId, missionNo, loginedUserId);
        return message.equals("성공")
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    //캘린더에서 user 확인하기
    @GetMapping("/{missionNo}/calender/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> calenderUserInfoDetail(@PathVariable Long missionNo, @PathVariable String userId) {

        return ResponseEntity.ok().build();
    }

    //미션 time 생성
    @PostMapping("/{missionNo}/mission-time")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMissionTime(
            @PathVariable Long missionNo, @Valid @RequestBody MissionTimeDto missionTimeDto
    ) {
        return missionService.createMissionTime(missionNo, missionTimeDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();

    }

    //hot 미션 5개 가져오기
    @GetMapping("/hot")
    public ResponseEntity<List> hotMissions() {
        return new ResponseEntity<>(
                missionService.getHotMissions(), HttpStatus.OK);
    }

    //로그인 유저가 해당 미션에 등록한 사진들 불러오기
    @GetMapping("/{missionNo}/picture")
    public ResponseEntity<List<MissionPictureRes>> getMissionPictures(@PathVariable Long missionNo) {
        return ResponseEntity.ok(missionService.getMissionPictureMatchingUser(missionNo));
    }

    //인증 시작 시간 & 끝나는 시간 생성하기
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

    //사진 업로드 하기
    @PostMapping("/{missionNo}/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> uploadPicture(@PathVariable Long missionNo, @Valid @RequestBody UploadPictureDto uploadPictureDto) {
        return missionService.uploadPicture(missionNo, uploadPictureDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.internalServerError().build();
    }

    //현재 미션에 참여 중인 유저 목록
    @GetMapping("/{missionNo}/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List> missionUsers(@PathVariable Long missionNo){
        return new ResponseEntity<>(
                missionService.getMissionUsers(missionNo), HttpStatus.OK);
    }


    @GetMapping("/{missionNo}/calender")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CalenderUserInfoRes>> missionTableInfo(@PathVariable Long missionNo){
        return ResponseEntity.ok(missionService.getCalenderInfo(missionNo));
    }
}
