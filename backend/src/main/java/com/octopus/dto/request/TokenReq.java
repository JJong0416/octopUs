package com.octopus.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TokenReq {

    @NotNull
    private final String token;

    public TokenReq(String token) {
        this.token = token;
    }
}