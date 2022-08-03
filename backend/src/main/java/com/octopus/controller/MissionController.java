package com.octopus.controller;

import com.octopus.domain.AuthenticationInfo;
import com.octopus.domain.Mission;
import com.octopus.domain.dto.AuthenticationDto;
import com.octopus.domain.dto.MissionCreateDto;
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
    ){
        missionService.createMission(missionCreateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/new")
    public ResponseEntity<List> newMissions(){
        return ResponseEntity.ok(missionService.getNewMissions());
    }

    @PostMapping("/{missionNo}/authentication")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createAuthentication(
            @PathVariable Long missionNo,
            @Valid @RequestBody AuthenticationDto authenticationDto
    ){
        return missionService.createAuthentication(missionNo, authenticationDto)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

}
