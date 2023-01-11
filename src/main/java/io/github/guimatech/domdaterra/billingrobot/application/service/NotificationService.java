package io.github.guimatech.domdaterra.billingrobot.application.service;

import io.github.guimatech.domdaterra.billingrobot.domain.Billing;

public interface NotificationService {

    void sendMessage(String message, Billing billing);
}