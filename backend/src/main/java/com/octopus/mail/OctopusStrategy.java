package com.octopus.mail;

import com.octopus.dto.layer.EmailMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class OctopusStrategy implements SendStrategy {

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendEmail(EmailMessageDto emailMessageDto) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessageDto.getTo());
            mimeMessageHelper.setSubject(emailMessageDto.getSubject());
            mimeMessageHelper.setText(emailMessageDto.getText(), true);
            javaMailSender.send(mimeMessage);

            log.info("success send to email : {}", emailMessageDto.getTo());
        } catch (MessagingException e) {
            log.info("fail send to email : {}", emailMessageDto.getTo());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.GOOGLE_EMAIL;
    }
}
