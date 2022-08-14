package com.octopus.dto.request;

import com.octopus.domain.AuthenticationInfo;
import lombok.Builder;
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
    @Builder
    AuthenticationReq(AuthenticationInfo authenticationInfo){
        this.authenticationStartTime = authenticationInfo.getAuthenticationStartTime();
        this.authenticationEndTime = authenticationInfo.getAuthenticationEndTime();
    }
}
