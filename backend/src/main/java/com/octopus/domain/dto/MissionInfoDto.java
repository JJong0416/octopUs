package com.octopus.domain.dto;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionInfoDto {

    private String missionCode;

    private String missionLeaderId;

    private String missionTitle;

    private MissionType missionType;

    private Integer missionPoint;

    private MissionStatus missionStatus;

    private Integer missionLimitPersonnel;

    private String missionContent;

    private MissionOpenType missionOpen;

    private String missionUser;


    @Builder
    public MissionInfoDto(
            String missionCode,
            String missionLeaderId,
            String missionTitle,
            MissionType missionType,
            Integer missionPoint,
            MissionStatus missionStatus,
            Integer missionLimitPersonnel,
            String missionContent,
            MissionOpenType missionOpen,
            String missionUser
    ){
        this.missionCode = missionCode;
        this.missionLeaderId = missionLeaderId;
        this.missionTitle = missionTitle;
        this.missionType = missionType;
        this.missionPoint = missionPoint;
        this.missionStatus = missionStatus;
        this.missionLimitPersonnel = missionLimitPersonnel;
        this.missionContent = missionContent;
        this.missionOpen = missionOpen;
        this.missionUser = missionUser;
    }
}
