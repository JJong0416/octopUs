package com.octopus.api.service;

import com.octopus.api.repository.EmailTokenRepository;
import com.octopus.api.repository.UserRepository;
import com.octopus.domain.entity.EmailToken;
import com.octopus.domain.type.PlatformType;
import com.octopus.dto.layer.EmailMessageDto;
import com.octopus.mail.EmailFactory;
import com.octopus.mail.SendStrategy;
import com.octopus.mail.StrategyFactory;
import com.octopus.mail.StrategyName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailTokenService {

    private final EmailTokenRepository emailTokenRepository;
    private final StrategyFactory strategyFactory;
    private final EmailFactory emailFactory;

    private final UserRepository userRepository;

    // 패스워드 찾기 토큰 생성
    public void createPasswordEmailToken(String userEmail, String userPassword){
        EmailMessageDto emailMessageDto = emailFactory.makePasswordMessageForm(userEmail, userPassword);
        sendEmail(emailMessageDto);
    }

    // 이메일 인증 토큰 생성
    public String createEmailToken(String userEmail) {

        // 이메일 중복 여부 체크
        if(!userRepository.existsByUserEmailAndPlatformType(userEmail, PlatformType.DOMAIN)) {

            // 이메일 토큰 저장
            EmailToken emailToken = EmailToken.createEmailToken(userEmail);
            emailTokenRepository.save(emailToken);

            // email 양식 만들기
            EmailMessageDto emailMessageDto = emailFactory.makeEmailMessageForm(userEmail, emailToken.getEmailCode());

            // email 보내기
            sendEmail(emailMessageDto);

            return "전송성공";
        }else{
            return "이메일중복";
        }
    }

    // 유효한 토큰 가져오기
    public Boolean findEmailToken(String emailTokenId) {
        Optional<EmailToken> emailToken = emailTokenRepository
                .findByEmailCodeAndExpirationDateAfterAndExpired(emailTokenId,
                        ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime(),
                        false);

        emailToken.ifPresent(EmailToken::hasTokenExpired);
        return emailToken.isPresent();
    }

    private void sendEmail(EmailMessageDto emailMessageDto) {
        SendStrategy sendStrategy = strategyFactory.findStrategy(StrategyName.GOOGLE_EMAIL);
        sendStrategy.sendEmail(emailMessageDto);
    }
}