package com.octopus.domain.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
public class AvatarUpdateDto {

    @NotNull
    private final String avatar;


}
