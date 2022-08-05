package com.octopus.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserMyPageDto {

    private final String userId;

    private final String userNickname;

    private final String userEmail;

    private final Integer userPoint;

    private final String userAvatar;

    @Builder
    UserMyPageDto(String userId, String userNickname, String userEmail, Integer userPoint, String userAvatar){
        this.userId = userId;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPoint = userPoint;
        this.userAvatar = userAvatar;
    }
}
