package com.octopus.api.controller;

import com.octopus.api.service.MissionService;
import com.octopus.domain.type.MissionType;
import com.octopus.dto.request.MissionCreateReq;
import com.octopus.dto.response.MissionRes;
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
    public ResponseEntity<Long> createMission(@Valid @RequestBody MissionCreateReq missionCreateReq) {
        return new ResponseEntity<>(missionService.createMission(missionCreateReq), HttpStatus.OK);
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

    @GetMapping("/search/code/{missionCode}")
    public ResponseEntity<List<MissionRes>> getMissionByMissionCode(@PathVariable String missionCode){
        return new ResponseEntity<>(missionService.getMissionByMissionCode(missionCode), HttpStatus.OK);
    }

    @GetMapping("/search/title/{missionTitle}")
    public ResponseEntity<List<MissionRes>> getMissionsByMissionTitle(@PathVariable String missionTitle){
        return new ResponseEntity<>(missionService.getMissionByMissionTitle(missionTitle), HttpStatus.OK);
    }

    @GetMapping("/search/type/{missionType}")
    public ResponseEntity<List<MissionRes>> getMissionsByMissionType(@PathVariable MissionType missionType){
        return new ResponseEntity<>(missionService.getMissionsByMissionType(missionType), HttpStatus.OK);
    }
}
