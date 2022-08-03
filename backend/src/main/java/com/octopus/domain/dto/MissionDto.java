package com.octopus.domain.dto;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

import static com.octopus.utils.MissionUtils.CODE_SIZE;
import static com.octopus.utils.MissionUtils.createMissionCode;

@Getter
public class MissionDto {
    @Size(max = 8)
    private String missionCode;

    @NotNull
    private String missionLeaderId;

    @NotNull
    @Size(min = 4, max = 30)
    private String missionTitle;

    @NotNull
    private MissionType missionType;

    @Positive
    @Min(100) @Max(100000)
    private Integer missionPoint;

    @NotNull
    private MissionStatus missionStatus;

    @Positive
    @Min(2) @Max(8)
    private Integer missionLimitPersonnel;

    @NotNull
    @Size(min = 6, max = 300)
    private String missionContent;

    @NotNull
    private MissionOpenType missionOpen;

    private String missionUser;

    public void updateMission(MissionUpdateInfoDto missionUpdateInfoDto){
        this.missionTitle = missionUpdateInfoDto.getMissionTitle();
        this.missionContent = missionUpdateInfoDto.getMissionContent();
        this.missionType = missionUpdateInfoDto.getMissionType();
        if (missionUpdateInfoDto.getMissionLimitPersonnel() < this.missionLimitPersonnel){
            //throw LimitLessThanBeforeException;
        }
        else
        {this.missionLimitPersonnel = missionUpdateInfoDto.getMissionLimitPersonnel();}
    }

    @Builder
    public MissionDto(
            String missionCode,
            String missionLeaderId,
            String missionTitle,
            MissionType missionType,
            Integer missionPoint,
            Integer missionLimitPersonnel,
            String missionContent,
            MissionOpenType missionOpen,
            MissionStatus missionStatus
    ) {
        this.missionCode = missionCode;
        this.missionLeaderId = missionLeaderId;
        this.missionTitle = missionTitle;
        this.missionType = missionType;
        this.missionPoint = missionPoint;
        this.missionLimitPersonnel = missionLimitPersonnel;
        this.missionContent = missionContent;
        this.missionOpen = missionOpen;
        this.missionStatus = missionStatus;
    }
}
