package com.octopus.dto.layer;

import com.octopus.domain.Picture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PictureDto {

    private final Long pictureNo;

    private final Long missionNo;

    private final Long userNo;

    private final String pictureUrl;

    private final LocalDateTime date;

    public PictureDto(Picture picture){
        this.pictureNo = picture.getPictureNo();
        this.missionNo = picture.getMissionNo().getMissionNo();
        this.userNo = picture.getUserNo().getUserNo();
        this.pictureUrl = picture.getPictureUrl();
        this.date = picture.getDate();
    }
}
