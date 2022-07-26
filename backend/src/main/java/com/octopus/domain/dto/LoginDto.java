package com.octopus.domain.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class LoginDto {

    @NotBlank
    @Size(min = 6, max = 15)
    private final String userId;

    @NotBlank
    @Size(min = 6, max = 100)
    private final String userPassword;

    @Builder
    public LoginDto(
            String userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
