package com.octopus.domain.dto;

import com.octopus.domain.Picture;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter @ToString
public class MissionPictureRes {

    private final String userNickname;

    private final List<Picture> userPictures;

    public MissionPictureRes(String userNickname, List<Picture> userPictures) {
        this.userNickname = userNickname;
        this.userPictures = userPictures;
    }
}
