package com.octopus.domain.dto.response;

import com.octopus.domain.dto.PictureRes;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class CalenderUserInfoRes {

    private final String userNickname;

    private final String userAvatar;

    private final Float successUserRate;

    private final List<PictureRes> userPictures;

    public CalenderUserInfoRes(String userNickname,
                               String userAvatar,
                               Float successUserRate,
                               List<PictureRes> userPictures
    ) {
        this.userNickname = userNickname;
        this.userAvatar = userAvatar;
        this.successUserRate = successUserRate;
        this.userPictures = userPictures;
    }
}
