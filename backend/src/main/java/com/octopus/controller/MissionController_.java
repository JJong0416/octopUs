package com.octopus.controller;

import com.octopus.domain.dto.MissionCreateDto;
import com.octopus.service.MissionService_;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController_ {

    private final MissionService_ missionService;

    // 비활성화 미션 생성
    @PostMapping("/mission")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> createMission(
            @Valid @RequestBody MissionCreateDto missionCreateDto
    ){
        missionService.createMission(missionCreateDto);
        return ResponseEntity.ok().build();
    }
}
