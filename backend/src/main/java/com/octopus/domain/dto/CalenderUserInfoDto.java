package com.octopus.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CalenderUserInfoDto {

    private final String userNickname;

    private final Boolean doesTodayAuthentication;

    private final Boolean doseWeekAuthentication;

    private final Integer currentWeekAuthenticationSize;

    private final Integer totalWeekAuthenticationSize;
}
