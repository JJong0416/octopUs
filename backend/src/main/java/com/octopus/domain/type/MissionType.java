package com.octopus.domain.type;

import lombok.Getter;

@Getter
public enum MissionType {

    LIFE("life", 0),
    EXERCISE("exercise", 1),
    STUDY("study", 2),
    MEETING("meeting", 3),
    ETC("etc", 4);
    private final String desc;
    private final Integer stateCode;

    MissionType(String desc, Integer stateCode) {
        this.desc = desc;
        this.stateCode = stateCode;
    }
}
