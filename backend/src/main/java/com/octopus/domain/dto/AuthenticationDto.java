package com.octopus.domain.dto;

import com.octopus.domain.Mission;
import lombok.Getter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Getter
public class AuthenticationDto {

    private final LocalTime authenticationStartTime;

    private final LocalTime authenticationEndTime;

    AuthenticationDto(
            LocalTime authenticationStartTime, LocalTime authenticationEndTime
    ){
        this.authenticationStartTime = authenticationStartTime;
        this.authenticationEndTime = authenticationEndTime;
    }
}
