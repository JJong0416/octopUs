package com.octopus.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserUpdatePasswordDto {

    private final String currentPassword;

    private final String newPassword;

    public UserUpdatePasswordDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
