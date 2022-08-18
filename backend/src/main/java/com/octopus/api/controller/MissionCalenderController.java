package com.octopus.api.controller;

import com.octopus.api.service.MissionCalenderService;
import com.octopus.dto.request.UploadPictureReq;
import com.octopus.dto.response.CalenderRes;
import com.octopus.dto.response.MissionPictureRes;
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
public class MissionCalenderController {

    private final MissionCalenderService missionCalenderService;

    // 미션 캘린더 조회
    @GetMapping("/calender")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CalenderRes> missionTableInfo(@PathVariable Long missionNo) {
        return ResponseEntity.ok(missionCalenderService.getCalenderRes(missionNo));
    }

    //로그인 유저가 해당 미션에 등록한 사진들 불러오기
    @GetMapping("/picture")
    public ResponseEntity<List<MissionPictureRes>> getMissionPictures(@PathVariable Long missionNo) {
        return ResponseEntity.ok(missionCalenderService.getMissionPictureMatchingUser(missionNo));
    }

    //사진 업로드 하기
    @PostMapping("/picture")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> uploadPicture(
            @PathVariable Long missionNo,
            @Valid @RequestBody UploadPictureReq uploadPictureReq
    ) {
        missionCalenderService.uploadPicture(missionNo, uploadPictureReq);
        return ResponseEntity.ok().build();
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
