package com.octopus.exception;

import lombok.Getter;

import java.time.LocalDateTime;
<<<<<<< HEAD
=======
import java.time.ZoneId;
import java.time.ZonedDateTime;
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95

@Getter
public class ErrorResponse {

<<<<<<< HEAD
    private final LocalDateTime timestamp = LocalDateTime.now();
=======
    private final LocalDateTime timestamp = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.error = errorCode.getStatus().name();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
