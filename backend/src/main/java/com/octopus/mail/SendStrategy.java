package com.octopus.mail;

import com.octopus.dto.layer.EmailMessageDto;

public interface SendStrategy {

    void sendEmail(EmailMessageDto emailMessageDto);

    StrategyName getStrategyName();
}
