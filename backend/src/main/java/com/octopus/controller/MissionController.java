package com.octopus.controller;

import com.octopus.domain.Mission;
import com.octopus.domain.dto.MissionListDto;
import com.octopus.domain.dto.SignUpDto;
import com.octopus.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission")
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/hot")
    public List<MissionListDto> getHotRoom(){
        return missionService.getHotRoom();
    }


}
