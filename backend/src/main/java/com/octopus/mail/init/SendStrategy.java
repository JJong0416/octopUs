package com.octopus.mail.init;

import com.octopus.mail.init.form.MessageForm;

public interface SendStrategy {

    void sendEmail(MessageForm messageForm);

    StrategyName getStrategyName();
}
