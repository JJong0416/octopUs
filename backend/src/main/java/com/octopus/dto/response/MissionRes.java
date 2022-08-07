package com.octopus.dto.response;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import com.octopus.dto.request.MissionUpdateInfoReq;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class MissionRes {
    @Size(max = 8)
    final private String missionCode;

    @NotNull
    final private String missionLeaderId;

    @NotNull
    @Size(min = 4, max = 30)
    private String missionTitle;

    @NotNull
    private MissionType missionType;

    @Positive
    @Min(100) @Max(100000)
    final private Integer missionPoint;

    @NotNull
    final private MissionStatus missionStatus;

    @Positive
    @Min(2) @Max(8)
    private Integer missionLimitPersonnel;

    @NotNull
    @Size(min = 6, max = 300)
    private String missionContent;

    @NotNull
    final private MissionOpenType missionOpen;

    final private String missionUsers;

    public void updateMission(MissionUpdateInfoReq missionUpdateInfoReq){
        this.missionTitle = missionUpdateInfoReq.getMissionTitle();
        this.missionContent = missionUpdateInfoReq.getMissionContent();
        this.missionType = missionUpdateInfoReq.getMissionType();
        if (missionUpdateInfoReq.getMissionLimitPersonnel() < this.missionLimitPersonnel){
            //throw LimitLessThanBeforeException;
        }
        else
        {this.missionLimitPersonnel = missionUpdateInfoReq.getMissionLimitPersonnel();}
    }

    @Builder
    public MissionRes(
            String missionCode,
            String missionLeaderId,
            String missionTitle,
            MissionType missionType,
            Integer missionPoint,
            Integer missionLimitPersonnel,
            String missionContent,
            MissionOpenType missionOpen,
            MissionStatus missionStatus,
            String missionUsers) {
        this.missionCode = missionCode;
        this.missionLeaderId = missionLeaderId;
        this.missionTitle = missionTitle;
        this.missionType = missionType;
        this.missionPoint = missionPoint;
        this.missionLimitPersonnel = missionLimitPersonnel;
        this.missionContent = missionContent;
        this.missionOpen = missionOpen;
        this.missionStatus = missionStatus;
        this.missionUsers = missionUsers;
    }
}
