package com.octopus.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailTokenService {

    private final EmailSenderService emailSenderService;
    private final EmailTokenRepository emailTokenRepository;

    // 이메일 인증 토큰 생성
    public Boolean createEmailToken(String userEmail) {

        // 이메일 토큰 저장
        EmailToken emailToken = EmailToken.createEmailToken(userEmail);
        emailTokenRepository.save(emailToken);

        // 이메일 전송
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText("http://[서버주소]/confirm-email?token=" + emailToken.getEmailCode());
        emailSenderService.sendEmail(mailMessage);

        return true;
    }

    // 유효한 토큰 가져오기
    public Boolean findByEmailCodeAndExpirationDateAfterAndExpired(String emailTokenId){
        Optional<EmailToken> emailToken = emailTokenRepository
                .findByEmailCodeAndExpirationDateAfterAndExpired(emailTokenId, LocalDateTime.now(), false);

        emailToken.ifPresent(EmailToken::hasTokenExpired);
        return emailToken.isPresent();
    }
}
