package com.octopus.exception;

import org.springframework.stereotype.Component;

@Component
public class MissionNotFoundException extends RuntimeException{

    private static final String MISSION_NOT_FOUND_EXCEPTION_MESSAGE = "해당 미션은 존재하지 않습니다.";

    @Override
    public final String getMessage(){
        return MISSION_NOT_FOUND_EXCEPTION_MESSAGE;
    }
}
