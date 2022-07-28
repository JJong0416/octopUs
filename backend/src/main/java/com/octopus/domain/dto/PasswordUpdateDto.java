package com.octopus.domain.dto;

import lombok.Getter;

@Getter
public class PasswordUpdateDto {

    private final String currentPassword;

    private final String newPassword;

    public PasswordUpdateDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
