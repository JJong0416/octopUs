package com.octopus.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
public class UserUpdatePasswordReq {

    private final String currentPassword;

    private final String newPassword;

    public UserUpdatePasswordReq(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
