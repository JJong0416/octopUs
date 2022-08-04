package com.octopus.domain.dto;

import com.octopus.domain.Picture;
import lombok.Getter;

import java.util.Date;

@Getter
public class PictureRes {

    private final Long pictureNo;

    private final Long missionNo;

    private final Long userNo;

    private final String pictureUrl;

    private final Date date;

    public PictureRes(Picture picture){
        this.pictureNo = picture.getPictureNo();
        this.missionNo = picture.getMissionNo().getMissionNo();
        this.userNo = picture.getUserNo().getUserNo();
        this.pictureUrl = picture.getPictureUrl();
        this.date = picture.getDate();
    }
}
