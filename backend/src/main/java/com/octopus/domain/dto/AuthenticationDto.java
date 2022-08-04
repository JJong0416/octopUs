package com.octopus.domain.dto;

import com.octopus.domain.Mission;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Time;

@Getter
public class AuthenticationDto {

    private final Time authenticationStartTime;

    private final Time authenticationEndTime;

    AuthenticationDto(
            Time authenticationStartTime, Time authenticationEndTime
    ){
        this.authenticationStartTime = authenticationStartTime;
        this.authenticationEndTime = authenticationEndTime;
    }
}
