package com.octopus.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionListDto {

    private final String missionCode;

    private final String missionName;

    @Builder
    public MissionListDto(String missionCode,String missionName){
        this.missionCode = missionCode;
        this.missionName = missionName;
    }
}
