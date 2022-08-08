package com.octopus.mail;

import com.octopus.mail.form.MessageForm;

public interface SendStrategy {

    void sendNotice(MessageForm messageForm);

    StrategyName getStrategyName();
}
