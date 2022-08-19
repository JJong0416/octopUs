package com.octopus.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AvatarReq {
    @NotNull
    private final Integer avatarColor;

    @NotNull
    private final Integer avatarFace;

    @NotNull
    private final Integer avatarHat;

    @NotNull
    private final Integer avatarPet;

    public AvatarReq(Integer avatarColor, Integer avatarFace, Integer avatarHat, Integer avatarPet) {
        this.avatarColor = avatarColor;
        this.avatarFace = avatarFace;
        this.avatarHat = avatarHat;
        this.avatarPet = avatarPet;
    }

}
