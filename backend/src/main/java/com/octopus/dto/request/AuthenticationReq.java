package com.octopus.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
public class AuthenticationReq {

    @NotNull
    private final LocalTime authenticationStartTime;

    @NotNull
    private final LocalTime authenticationEndTime;

    AuthenticationReq(
            LocalTime authenticationStartTime, LocalTime authenticationEndTime
    ){
        this.authenticationStartTime = authenticationStartTime;
        this.authenticationEndTime = authenticationEndTime;
    }
}
