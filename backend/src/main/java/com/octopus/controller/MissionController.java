package com.octopus.controller;

import com.octopus.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {
    private final MissionService missionService;


    @GetMapping("/hot")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List> hotMissions() {
        return ResponseEntity.ok(missionService.getHotMissions());
    }
}
