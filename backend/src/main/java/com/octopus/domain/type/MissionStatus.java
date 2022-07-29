package com.octopus.domain.type;

import lombok.Getter;

@Getter
public enum MissionStatus {
    OPEN("모집중",0),
    ONGOING("진행중",1),
    CLOSE("종료",2);

    private final String desc;
    private final Integer stateCode;

    MissionStatus(String desc, Integer stateCode){
        this.desc = desc;
        this.stateCode = stateCode;
    }
}
