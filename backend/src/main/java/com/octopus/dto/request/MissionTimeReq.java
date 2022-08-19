package com.octopus.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionTimeReq {

    private final LocalDateTime missionTimeStartTime;

    private final Integer missionTimeWeek;

    private final Integer missionTimeDPW;

    private final Integer missionTimeTPD;

    MissionTimeReq(
            LocalDateTime missionTimeStartTime, Integer missionTimeWeek, Integer missionTimeDPW, Integer missionTimeTPD
    ) {
        this.missionTimeStartTime = missionTimeStartTime;
        this.missionTimeWeek = missionTimeWeek;
        this.missionTimeDPW = missionTimeDPW;
        this.missionTimeTPD = missionTimeTPD;
    }
}
