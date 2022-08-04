package com.octopus.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CalenderTotalDto {

    private final Float totalMissionSuccessRate;

    private final List<CalenderInfoDto> calenderInfoDto;

    public CalenderTotalDto(Float totalMissionSuccessRate, List<CalenderInfoDto> calenderInfoDto) {
        this.totalMissionSuccessRate = totalMissionSuccessRate;
        this.calenderInfoDto = calenderInfoDto;
    }
}
