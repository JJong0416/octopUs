package com.octopus.domain.type;

import lombok.Getter;

@Getter
public enum MissionOpenType {
    OPEN_ROOM("공개방",0),
    CLOSE_ROOM("비공개방",1);

    private final String desc;
    private final Integer stateCode;

    MissionOpenType(String desc, Integer stateCode){
        this.desc = desc;
        this.stateCode = stateCode;
    }
}
