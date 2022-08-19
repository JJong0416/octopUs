package com.octopus.dto.request;

import com.octopus.domain.type.MissionType;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class MissionUpdateInfoReq {

    @NotNull
    @Size(min = 4, max = 30)
    private final String missionTitle;

    @NotNull
    private final MissionType missionType;

    @Positive
    @Min(2)
    @Max(8)
    private final Integer missionLimitPersonnel;
    //이거 min 어떻게 수정하징...?

    @NotNull
    @Size(min = 6, max = 300)
    private final String missionContent;


    MissionUpdateInfoReq(
            String missionTitle,
            MissionType missionType,
            Integer missionLimitPersonnel,
            String missionContent
    ) {
        this.missionTitle = missionTitle;
        this.missionType = missionType;
        this.missionLimitPersonnel = missionLimitPersonnel;
        this.missionContent = missionContent;
    }
}
