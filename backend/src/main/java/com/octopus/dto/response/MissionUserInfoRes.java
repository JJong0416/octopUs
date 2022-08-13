package com.octopus.dto.response;

import com.octopus.dto.layer.PictureDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Getter
public class MissionUserInfoRes {
    @NotBlank
    private final String userEmail;

    @NotBlank
    private final String userNickname;
    @NotNull
    private final String userAvatar;

    @Builder
    public MissionUserInfoRes(String userEmail,
                              String userNickname,
                              String userAvatar
    ) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userAvatar = userAvatar;

    }
}
