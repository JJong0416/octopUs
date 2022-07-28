package com.octopus.domain.dto;

import lombok.Getter;

@Getter
public class UserUpdatePasswordDto {

    private final String currentPassword;

    private final String newPassword;

    public UserUpdatePasswordDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
