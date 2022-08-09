package com.octopus.dto.layer;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailMessageDto {

    private final String to;

    private final String subject;

    private final String text;

    @Builder(builderMethodName = "createMessageForm")
    EmailMessageDto(String to, String subject, String text){
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
}
