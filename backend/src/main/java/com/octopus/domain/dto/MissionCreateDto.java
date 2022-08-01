package com.octopus.domain.dto;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.Getter;

import javax.validation.constraints.*;

import static com.octopus.utils.MissionUtils.CODE_SIZE;
import static com.octopus.utils.MissionUtils.createMissionCode;

@Getter
public class MissionCreateDto {

    @Size(max = 8)
    private final String missionCode;

    @NotNull
    private String missionLeaderId;

    @NotNull
    @Size(min = 4, max = 30)
    private final String missionTitle;

    @NotNull
    private final MissionType missionType;

    @Positive
    @Min(100) @Max(100000)
    private final Integer missionPoint;

    @NotNull
    private final MissionStatus missionStatus;

    @Positive
    @Min(2) @Max(8)
    private final Integer missionLimitPersonnel;

    @NotNull
    @Size(min = 6, max = 300)
    private final String missionContent;

    @NotNull
    private final MissionOpenType missionOpen;

    private String missionUser;

    public void addMissionLeaderIdAndUser(String leaderId) {
        this.missionLeaderId = leaderId;
        this.missionUser = leaderId + ", ";
    }


    MissionCreateDto(
            String missionLeaderId,
            String missionTitle,
            MissionType missionType,
            Integer missionPoint,
            Integer missionLimitPersonnel,
            String missionContent,
            MissionOpenType missionOpen
    ) {
        /* 외부 주입*/
        this.missionCode = createMissionCode(CODE_SIZE);
        this.missionLeaderId = missionLeaderId;
        this.missionTitle = missionTitle;
        this.missionType = missionType;
        this.missionPoint = missionPoint;
        this.missionLimitPersonnel = missionLimitPersonnel;
        this.missionContent = missionContent;
        this.missionOpen = missionOpen;

        /* 내부 주입 */
        this.missionStatus = MissionStatus.UNACTIVATED;
    }
}
