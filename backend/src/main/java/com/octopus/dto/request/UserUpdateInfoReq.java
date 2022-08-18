package com.octopus.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateInfoReq {

    private final String userAvatar;

    private final String userNickname;

    public UserUpdateInfoReq(String userNickname, String userAvatar) {
        this.userAvatar = userAvatar;
        this.userNickname = userNickname;
    }
}
