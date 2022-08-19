package com.octopus.domain.type;

import lombok.Getter;

@Getter
public enum MissionStatus {
    UNACTIVATED("비활성화",0),
    OPEN("모집중",1),
    ONGOING("진행중",2),
    CLOSE("종료",3);

    private final String desc;
    private final Integer stateCode;

    MissionStatus(String desc, Integer stateCode){
        this.desc = desc;
        this.stateCode = stateCode;
    }
}
