package com.octopus.dto.response;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class CalenderRes {

    @Positive
    private final Integer weekInProgress;

    @NotNull
    private final Float successTeamRate;

    @NotNull
    private final Boolean isCurrentUserPicturePost;

    @NotNull
    private final List<CalenderUserInfoRes> calenderUserInfos;

    public CalenderRes(Integer weekInProgress,
                       Float successTeamRate,
                       Boolean isCurrentUserPicturePost,
                       List<CalenderUserInfoRes> calenderUserInfos
    ) {
        this.weekInProgress = weekInProgress;
        this.successTeamRate = successTeamRate;
        this.isCurrentUserPicturePost = isCurrentUserPicturePost;
        this.calenderUserInfos = calenderUserInfos;
    }
}
