package com.octopus.dto.response;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class UserMyPageRes {

    @Pattern(regexp = "[a-z0-9]{6,15}")
    private final String userId;

    @NotBlank
    private final String userNickname;

    @Email
    private final String userEmail;

    private final Integer userPoint;

    @NotNull
    private final String userAvatar;

    @Builder
    UserMyPageRes(String userId, String userNickname, String userEmail, Integer userPoint, String userAvatar){
        this.userId = userId;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPoint = userPoint;
        this.userAvatar = userAvatar;
    }
}
