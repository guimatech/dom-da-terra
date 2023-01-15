package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.billing.Billing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SMSService implements NotificationService {

    @Autowired
    private AwsSnsService snsService;

    @Override
    public void sendMessage(String message, Billing billing) {
        log.info("Sending sms...");
        snsService.sendSms(billing.getPhone(), message);
    }
}
