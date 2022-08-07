package com.octopus.dto.response;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class MissionInfoReq {

    @NotBlank
    private String missionCode;

    @NotNull
    private String missionLeaderId;

    @NotNull
    private String missionTitle;

    @NotBlank
    private MissionType missionType;

    @Positive
    private Integer missionPoint;

    @NotNull
    private MissionStatus missionStatus;

    @NotNull
    private Integer missionLimitPersonnel;

    @NotNull
    private String missionContent;

    @NotNull
    private MissionOpenType missionOpen;

    @NotNull
    private String missionUser;


    @Builder
    public MissionInfoReq(
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
    ) {
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
