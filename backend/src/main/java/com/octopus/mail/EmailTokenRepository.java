package com.octopus.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {

    @Transactional(readOnly = true)
    Optional<EmailToken> findByEmailCodeAndExpirationDateAfterAndExpired(
            String emailCode, LocalDateTime now, boolean expired);
}
