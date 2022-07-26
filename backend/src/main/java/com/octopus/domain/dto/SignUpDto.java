package com.octopus.domain.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignUpDto {

    @NotBlank
    @Size(min = 6, max = 15)
    private final String userId;

    @NotBlank
    @Size(min = 6, max = 20)
    private final String userPassword;

    @Email
    private final String userEmail;

    @NotBlank
    @Size(min = 4, max = 10)
    private final String userNickname;

    private final Integer userAvatar;


    @Builder
    public SignUpDto(String userId, String userPassword, String userEmail, String userNickname, Integer userAvatar){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userAvatar = userAvatar;
    }
}
