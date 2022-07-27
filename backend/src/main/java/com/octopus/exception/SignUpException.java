package com.octopus.exception;

import org.springframework.stereotype.Component;

@Component
public class SignUpException extends RuntimeException{

    private static final String SIGNUP_EXCEPTION_MESSAGE = "이미 가입되어 있는 유저입니다.";

    @Override
    public final String getMessage(){
        return SIGNUP_EXCEPTION_MESSAGE;
    }
}
