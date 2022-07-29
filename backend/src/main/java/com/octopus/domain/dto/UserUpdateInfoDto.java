package com.octopus.domain.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateInfoDto {

    private final String userAvatar;

    private final String userNickname;

    public UserUpdateInfoDto(String userNickname, String userAvatar) {
        this.userAvatar = userAvatar;
        this.userNickname = userNickname;
    }
}
