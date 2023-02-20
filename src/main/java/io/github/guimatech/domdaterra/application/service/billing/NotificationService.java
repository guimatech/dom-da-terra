package io.github.guimatech.domdaterra.application.service.billing;

import io.github.guimatech.domdaterra.domain.billing.Billing;

public interface NotificationService {

    void sendMessage(String message, Billing billing);
}