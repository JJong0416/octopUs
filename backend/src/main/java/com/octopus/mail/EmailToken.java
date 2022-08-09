package com.octopus.mail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailToken {

    private static final Long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;

    @Id
    @Column(length = 6)
    private String emailCode;

    private LocalDateTime expirationDate;

    private boolean expired;

    private String userEmail;

    // 이메일 인증 토큰 생성
    public static EmailToken createEmailToken(String userEmail) {
        EmailToken emailToken = new EmailToken();
        emailToken.emailCode = EmailUtils.createEmailAuthentication();
        emailToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        emailToken.expired = false;
        emailToken.userEmail = userEmail;

        return emailToken;
    }

    // 토큰 만료
    public void hasTokenExpired() {
        this.expired = true;
    }
}
