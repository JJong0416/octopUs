package com.octopus.controller;

import com.octopus.domain.Mission;
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
    @PostMapping("/mission")
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

    @DeleteMapping("/{missionNo}/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity kickOutUser(@PathVariable String userId, @PathVariable Long missionNo, @RequestBody String loginedUserId){
        // 지금로그인된 사용자의 아이디를 받아오는 것이 아닌 토큰에서 id를 꺼내오는거로 변경 필요
        String message = missionService.deleteUserFromMission(userId,missionNo,loginedUserId);
        return message.equals("성공")
                ?new ResponseEntity<>(message,HttpStatus.OK)
                :new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{missionNo}/calender/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> calenderUserInfoDetail(@PathVariable Long missionNo, @PathVariable String userId){

        return ResponseEntity.ok().build();
    }
}
