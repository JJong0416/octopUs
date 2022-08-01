package com.octopus.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMyPageDto {

    private final String userId;

    private final String userNickname;

    private final String userEmail;

    private final Integer userPoint;

    @Builder
    UserMyPageDto(String userId, String userNickname, String userEmail, Integer userPoint){
        this.userId = userId;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPoint = userPoint;
    }
}
