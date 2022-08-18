package com.octopus.dto.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class PointReq {

    private final String userId;
    private final Integer point;

    public PointReq(String userId, Integer point) {
        this.userId = userId;
        this.point = point;
    }
}
