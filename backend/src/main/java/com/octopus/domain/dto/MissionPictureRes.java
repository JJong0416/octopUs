package com.octopus.domain.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString
public class MissionPictureRes {

    private final String userNickname;

    private final List<PictureRes> userPictures;

    public MissionPictureRes(String userNickname, List<PictureRes> userPictures) {
        this.userNickname = userNickname;
        this.userPictures = userPictures;
    }
}
