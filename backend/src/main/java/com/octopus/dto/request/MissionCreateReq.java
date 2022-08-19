package com.octopus.dto.request;

import com.octopus.domain.type.MissionOpenType;
import com.octopus.domain.type.MissionStatus;
import com.octopus.domain.type.MissionType;
import lombok.Getter;

import javax.validation.constraints.*;

import static com.octopus.utils.MissionUtils.CODE_SIZE;
import static com.octopus.utils.MissionUtils.createMissionCode;

@Getter
public class MissionCreateReq {

    @Size(max = 8)
    private final String missionCode;

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

    public void addMissionLeaderIdAndUser(String leaderId, String leaderNickname) {
        this.missionLeaderId = leaderId;

        this.missionUser = leaderNickname;
    }


    MissionCreateReq(
            String missionTitle,
            MissionType missionType, // 타입 여러개
            Integer missionPoint,
            Integer missionLimitPersonnel,
            String missionContent,
            MissionOpenType missionOpen
    ) {
        /* 외부 주입*/
        this.missionCode = createMissionCode(CODE_SIZE);
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
