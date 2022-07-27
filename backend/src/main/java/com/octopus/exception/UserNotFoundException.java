package com.octopus.exception;

import org.springframework.stereotype.Component;

@Component
public class UserNotFoundException extends RuntimeException{

    private static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "해당 회원은 존재하지 않습니다.";

    @Override
    public final String getMessage(){
        return USER_NOT_FOUND_EXCEPTION_MESSAGE;
    }
}
