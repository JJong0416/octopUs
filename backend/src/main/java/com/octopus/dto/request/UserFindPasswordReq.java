package com.octopus.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
public class UserFindPasswordReq {

    @Email @Setter
    private String userEmail;
}
