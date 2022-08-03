package com.octopus.domain.dto;

import com.octopus.domain.Mission;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionListDto {
    private final Long missionNo;
    private final String missionCode;

    private final String missionName;

    @Builder
    public MissionListDto(long missionNo, String missionCode,String missionName){
        this.missionNo = missionNo;
        this.missionCode = missionCode;
        this.missionName = missionName;
    }

}
