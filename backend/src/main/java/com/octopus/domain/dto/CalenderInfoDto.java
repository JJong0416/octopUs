package com.octopus.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CalenderInfoDto {

    // 몇주차인지
    private final String week;

    private final List<CalenderUserInfoDto> calenderUserInfos;

    CalenderInfoDto(String week, List<CalenderUserInfoDto> calenderUserInfos){
        this.week = week;
        this.calenderUserInfos = calenderUserInfos;
    }
}
