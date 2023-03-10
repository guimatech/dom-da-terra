package io.github.guimatech.domdaterra.application.service.billing;

import io.github.guimatech.domdaterra.domain.billing.Billing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WhatsAppService implements NotificationService {

    @Autowired
    private ZAPIService service;

    @Override
    public void sendMessage(String message, Billing billing) {
        log.info("Sending WhatsApp message...");

        service.sendMessage(billing.getPhone(), message);
    }
}