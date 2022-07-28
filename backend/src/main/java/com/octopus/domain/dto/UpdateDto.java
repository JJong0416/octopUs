package com.octopus.domain.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UpdateDto {
    @NotBlank
    @Size(min = 6, max = 15)
    private final String userId;
    @NotBlank
    @Size(min = 4, max = 10)
    private final String userNickname;

    @NotBlank
    private final String userAvatar;


    @Builder
    public UpdateDto(
            String userId, String userNickname, String userAvatar
    ) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userAvatar = userAvatar;
    }
}
