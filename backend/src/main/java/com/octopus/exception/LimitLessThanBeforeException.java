package com.octopus.exception;

import org.springframework.stereotype.Component;

@Component
public class LimitLessThanBeforeException extends RuntimeException{

    private static final String LIMIT_LESS_THAN_BEFORE_EXCEPTION_MESSAGE = "입력값이 이전 인원보다 적습니다.";

    @Override
    public final String getMessage(){
        return LIMIT_LESS_THAN_BEFORE_EXCEPTION_MESSAGE;
    }
}
