package com.octopus.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UploadPictureReq {

    @NotBlank
    private String encodedImg;

}
