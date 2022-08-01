package com.octopus.controller;

import com.octopus.domain.dto.MissionListDto;
import com.octopus.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/hot")
    public List<MissionListDto> getHotRoom() {
        return missionService.getHotRoom();
    }


}
