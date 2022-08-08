package com.octopus.api.controller;

import com.octopus.api.service.MissionService;
import com.octopus.dto.request.MissionCreateReq;
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
    public ResponseEntity<HttpStatus> createMission(@Valid @RequestBody MissionCreateReq missionCreateReq) {
        missionService.createMission(missionCreateReq);
        return ResponseEntity.ok().build();
    }

    //new 미션 5개 가져오기
    @GetMapping("/new")
    public ResponseEntity<List> newMissions() {
        return ResponseEntity.ok(missionService.getNewMissions());
    }

    //hot 미션 5개 가져오기
    @GetMapping("/hot")
    public ResponseEntity<List> hotMissions() {
        return new ResponseEntity<>(missionService.getHotMissions(), HttpStatus.OK);
    }
}
