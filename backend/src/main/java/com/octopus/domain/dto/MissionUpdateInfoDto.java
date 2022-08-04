package com.octopus.domain.dto;

import com.octopus.domain.type.MissionType;
import lombok.Getter;
import lombok.ToString;
import javax.validation.constraints.*;



@Getter
@ToString
public class MissionUpdateInfoDto {

    @NotNull
    @Size(min = 4, max = 30)
    private String missionTitle;

    @NotNull
    private final MissionType missionType;

    @Positive
    @Min(2) @Max(8)
    private Integer missionLimitPersonnel;
    //이거 min 어떻게 수정하징...?

    @NotNull
    @Size(min = 6, max = 300)
    private String missionContent;



    MissionUpdateInfoDto(
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
