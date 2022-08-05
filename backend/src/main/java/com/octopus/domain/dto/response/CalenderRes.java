package com.octopus.domain.dto.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class CalenderRes {

    private final Integer weekInProgress;

    private final Float successTeamRate;

    private final Boolean isCurrentUserPicturePost;

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
