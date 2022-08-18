package com.octopus.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class EmailVerificationReq {

    private String userEmail;
    
    private String emailCode;
    
}
