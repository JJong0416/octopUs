package com.octopus.dto.response;

import com.octopus.dto.layer.PictureDto;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CalenderUserInfoRes {

    @NotBlank
    private final String userNickname;

    @NotNull
    private final String userAvatar;

    @NotNull
    private final Float successUserRate;

    private final List<PictureDto> userPictures;

    public CalenderUserInfoRes(String userNickname,
                               String userAvatar,
                               Float successUserRate,
                               List<PictureDto> userPictures
    ) {
        this.userNickname = userNickname;
        this.userAvatar = userAvatar;
        this.successUserRate = successUserRate;
        this.userPictures = userPictures;
    }
}
