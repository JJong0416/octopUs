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

        String message = makeTemplateEngineVerifiedMessage(emailCode);

        return EmailMessageDto.createMessageForm()
                .to(email)
                .subject("회원가입 이메일 인증")
                .text(message)
                .build();
    }

    public EmailMessageDto makePasswordMessageForm(String userEmail, String userPassword) {

        String message = makeTemplateEnginePasswordMessage(userPassword);

        return EmailMessageDto.createMessageForm()
                .to(userEmail)
                .subject("옥토퍼스 패스워드 찾기")
                .text(message)
                .build();
    }

    private String makeTemplateEngineVerifiedMessage(String emailCode) {
        Context context = new Context();
        context.setVariable("point", "서비스를 이용하려면, 아래 코드를 옥토퍼스에 입력해주세요");
        context.setVariable("code", emailCode);

        return templateEngine.process(emailProcessLink, context);
    }

    private String makeTemplateEnginePasswordMessage(String userPassword) {
        Context context = new Context();
        context.setVariable("point", "비밀번호가 변경되었습니다. 아래 비밀번호로 로그인 해주세요");
        context.setVariable("code", userPassword);

        return templateEngine.process(emailProcessLink, context);
    }
}
