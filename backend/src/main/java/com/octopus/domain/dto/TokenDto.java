package com.octopus.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
public class TokenDto {

    @NotNull
    private final String token;

    public TokenDto(String token){
        this.token = token;
    }
}