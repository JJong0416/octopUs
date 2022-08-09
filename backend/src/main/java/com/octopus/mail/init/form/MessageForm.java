package com.octopus.mail.init.form;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageForm {

    private final String to;

    private final String subject;

    private final String text;

    @Builder(builderMethodName = "createMessageForm")
    MessageForm(String to, String subject, String text){
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
}
