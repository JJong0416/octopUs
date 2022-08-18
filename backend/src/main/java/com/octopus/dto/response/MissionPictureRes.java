package com.octopus.dto.response;

import com.octopus.dto.layer.PictureDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionPictureRes {

    private final String userNickname;

    private final List<PictureDto> userPictures;

    public MissionPictureRes(String userNickname, List<PictureDto> userPictures) {
        this.userNickname = userNickname;
        this.userPictures = userPictures;
    }
}
