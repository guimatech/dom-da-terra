package io.github.guimatech.domdaterra.billingrobot.infra.schedule;

import io.github.guimatech.domdaterra.billingrobot.application.service.BillingService;
import io.github.guimatech.domdaterra.billingrobot.application.service.NotificationManager;
import io.github.guimatech.domdaterra.billingrobot.domain.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.github.guimatech.domdaterra.shared.util.DateUtil.formatDateToBrazilianPattern;
import static io.github.guimatech.domdaterra.shared.util.StringUtil.addBrazilCode;


@Component
@EnableScheduling
public class BillingNotifier {

    private static final String TIME_ZONE = "America/Sao_Paulo";
    private static final String NOTIFIER_CRON = "0 0 9 * * *";
    private static final String MESSAGE_PATTERN = "A SBL Dom da Terra informa,%n" +
            "que seu contrato de nº %s está vencendo na data %s,%n" +
            "conforme relatório de endividamento já disponibilizado no seu whatsapp.";

    @Autowired
    private NotificationManager notificationManager;

    @Autowired
    private BillingService billingService;

    @Scheduled(cron = NOTIFIER_CRON, zone = TIME_ZONE)
    public void notifyDebtors() {
        List<Billing> billings = billingService.findByCurrentDate();
        billings.stream().map(this::handleInformation)
                .filter(Billing::isActive)
                .forEach(billing -> notificationManager
                        .getNotificationServices()
                        .forEach(notificationService -> notificationService.sendMessage(mountBillingMessage(billing), billing))
                );
    }

    private String mountBillingMessage(Billing billing) {
        String expiration = formatDateToBrazilianPattern(billing.getExpiration());
        return String.format(MESSAGE_PATTERN, billing.getOperation(), expiration);
    }

    private Billing handleInformation(Billing billing) {
        billing.setPhone(addBrazilCode(billing.getPhone()));
        return billing;
    }
}
