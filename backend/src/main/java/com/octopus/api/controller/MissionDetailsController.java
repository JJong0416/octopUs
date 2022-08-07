package com.octopus.api.controller;

import com.octopus.dto.request.AuthenticationReq;
import com.octopus.dto.request.MissionTimeReq;
import com.octopus.dto.request.MissionUpdateInfoReq;
import com.octopus.dto.request.UploadPictureReq;
import com.octopus.dto.response.CalenderRes;
import com.octopus.dto.response.MissionPictureRes;
import com.octopus.dto.response.MissionRes;
import com.octopus.api.service.MissionService;
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

    private final MissionService missionService;

    //미션 정보 가져오기
    @GetMapping
    public ResponseEntity<MissionRes> getMission(@PathVariable long missionNo) {
        MissionRes missionInfo = missionService.getMissionDtoByMissionNo(missionNo);
        return ResponseEntity.ok(missionInfo);
    }

    //미션 수정하기
    @PatchMapping
    public ResponseEntity<HttpStatus> modifyMission(
            @Valid @RequestBody MissionUpdateInfoReq missionUpdateInfoReq,
            @PathVariable long missionNo
    ) {
        missionService.modifyMission(missionUpdateInfoReq, missionNo);
        return ResponseEntity.ok().build();
    }

    //미션 time 생성
    @PostMapping("/mission-time")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMissionTime(
            @PathVariable Long missionNo,
            @Valid @RequestBody MissionTimeReq missionTimeReq
    ) {
        return missionService.createMissionTime(missionNo, missionTimeReq)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    //인증 시작 시간 & 끝나는 시간 생성하기
    @PostMapping("/authentication")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createAuthentication(
            @PathVariable Long missionNo,
            @Valid @RequestBody AuthenticationReq authenticationReq
    ) {
        return missionService.createAuthentication(missionNo, authenticationReq)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    //미션에 참여하기
    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> addUserToMission(@PathVariable long missionNo) {
        missionService.joinMission(missionNo);
        return ResponseEntity.ok().build();
    }

    //사진 업로드 하기
    @PostMapping("/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> uploadPicture(
            @PathVariable Long missionNo,
            @Valid @RequestBody UploadPictureReq uploadPictureReq
    ) {
        return missionService.uploadPicture(missionNo, uploadPictureReq)
                ? ResponseEntity.ok().build()
                : ResponseEntity.internalServerError().build();
    }

    //현재 미션에 참여 중인 유저 목록
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List> missionUsers(@PathVariable Long missionNo) {
        return new ResponseEntity<>(
                missionService.getMissionUsers(missionNo), HttpStatus.OK);
    }

    // 미션 캘린더 조회
    @GetMapping("/calender")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CalenderRes> missionTableInfo(@PathVariable Long missionNo) {
        return ResponseEntity.ok(missionService.getCalenderRes(missionNo));
    }


    //로그인 유저가 해당 미션에 등록한 사진들 불러오기
    @GetMapping("/picture")
    public ResponseEntity<List<MissionPictureRes>> getMissionPictures(@PathVariable Long missionNo) {
        return ResponseEntity.ok(missionService.getMissionPictureMatchingUser(missionNo));
    }

    //참여 유저 강퇴하기
    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity kickOutUser(
            @PathVariable String userId,
            @PathVariable Long missionNo,
            @RequestBody String loginedUserId
    ) {
        // 지금로그인된 사용자의 아이디를 받아오는 것이 아닌 토큰에서 id를 꺼내오는거로 변경 필요
        String message = missionService.deleteUserFromMission(userId, missionNo, loginedUserId);
        return message.equals("성공")
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    //캘린더에서 user 확인하기
    @GetMapping("/calender/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> calenderUserInfoDetail(
            @PathVariable Long missionNo,
            @PathVariable String userId
    ) {
        return ResponseEntity.ok().build();
    }
}
