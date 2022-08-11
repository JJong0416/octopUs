package com.octopus.dto.layer;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class MissionListDto {

    @NotBlank
    private final Long missionNo;

    @NotNull
    private final String missionCode;

    @NotNull
    private final String missionTitle;

    private final String missionLeaderAvatar;

    private final String missionLeaderId;

    private final String missionContent;


    @Builder
    public MissionListDto(long missionNo, String missionCode, String missionTitle, String missionLeaderAvatar, String missionLeaderId, String missionContent) {
        this.missionNo = missionNo;
        this.missionCode = missionCode;
        this.missionTitle = missionTitle;
        this.missionLeaderAvatar = missionLeaderAvatar;
        this.missionLeaderId = missionLeaderId;
        this.missionContent = missionContent;
    }
}
