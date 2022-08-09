package com.octopus.mail;

import com.octopus.dto.layer.EmailMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class EmailFactory {

    private final TemplateEngine templateEngine;

    private final static String emailProcessLink = "design-email";

    public EmailMessageDto makeEmailMessageForm(String email, String emailCode) {

        String message = makeTemplateEngineMessage(emailCode);

        return EmailMessageDto.createMessageForm()
                .to(email)
                .subject("회원가입 이메일 인증")
                .text(message)
                .build();
    }

    private String makeTemplateEngineMessage(String emailCode) {
        Context context = new Context();
        context.setVariable("code", emailCode);

        return templateEngine.process(emailProcessLink, context);
    }
}
