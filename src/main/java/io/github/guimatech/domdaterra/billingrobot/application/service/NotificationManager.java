package io.github.guimatech.domdaterra.billingrobot.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationManager {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private WhatsAppService whatsAppService;

    public List<NotificationService> getNotificationServices() {
        return List.of(
                emailService,
                smsService,
                whatsAppService
        );
    }
}