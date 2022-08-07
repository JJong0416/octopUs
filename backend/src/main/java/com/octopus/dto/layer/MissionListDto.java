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

    @Builder
    public MissionListDto(long missionNo, String missionCode, String missionTitle) {
        this.missionNo = missionNo;
        this.missionCode = missionCode;
        this.missionTitle = missionTitle;
    }
}
