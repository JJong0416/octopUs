package com.octopus.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class MissionTimeDto {

    private final Date missionTimeStartTime;

    private final Integer missionTimeWeek;

    private final Integer missionTimeDPW;

    private final Integer missionTimeTPD;

    MissionTimeDto(
            Integer missionTimeWeek, Integer missionTimeDPW, Integer missionTimeTPD
    ) {
        this.missionTimeStartTime = new Date();
        this.missionTimeWeek = missionTimeWeek;
        this.missionTimeDPW = missionTimeDPW;
        this.missionTimeTPD = missionTimeTPD;
    }
}
