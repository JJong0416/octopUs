package com.octopus.dto.response;

import com.octopus.domain.AuthenticationInfo;
import com.octopus.dto.request.AuthenticationReq;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MissionDetailRes {
    private final LocalDateTime missionTimeStartTime;

    private final LocalDateTime missionTimeEndTime;

    private final List<AuthenticationReq> authenticationInfoList;

    private final Integer missionTimeDPW;

    private final Integer missionTimeTPD;

    @Builder
    MissionDetailRes(
            LocalDateTime missionTimeStartTime, LocalDateTime missionTimeEndTime,
            List<AuthenticationReq> authenticationInfoList, Integer missionTimeDPW, Integer missionTimeTPD
    ) {
        this.missionTimeStartTime = missionTimeStartTime;
        this.missionTimeEndTime = missionTimeEndTime;
        this.authenticationInfoList = authenticationInfoList;
        this.missionTimeDPW = missionTimeDPW;
        this.missionTimeTPD = missionTimeTPD;

    }
}
