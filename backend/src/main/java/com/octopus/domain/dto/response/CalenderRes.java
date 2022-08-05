package com.octopus.domain.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CalenderRes {

    private final Integer weekInProgress;

    private final Float successTeamRate;

    private final Boolean isCurrentUserPicturePost;

    private final List<CalenderUserInfoRes> calenderUserInfos;

    CalenderRes(Integer weekInProgress,
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
