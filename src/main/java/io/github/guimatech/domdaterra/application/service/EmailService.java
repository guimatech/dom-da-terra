package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.Billing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService implements NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMessage(String message, Billing billing) {
        log.info("Sending email...");
        sendMessage(billing.getEmail(), "Notificação da SBL Dom da Terra", message);
    }

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
