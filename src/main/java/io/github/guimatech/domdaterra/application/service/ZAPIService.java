package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.Button;
import io.github.guimatech.domdaterra.domain.ButtonList;
import io.github.guimatech.domdaterra.domain.WhatsAppMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ZAPIService {

    @Value("${zapi.instance}")
    private String instance;

    @Value("${zapi.token}")
    private String token;

    public void sendMessage(String phone, String textMessage) {
        RestTemplate client = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String uri = String.format("https://api.z-api.io/instances/%s/token/%s/send-button-list", instance, token);

        WhatsAppMessage message = WhatsAppMessage.builder()
                .phone(phone)
                .message(textMessage)
                .buttonList(
                        new ButtonList(List.of(
                                Button.builder().id("1").label("Foi útil").build(),
                                Button.builder().id("2").label("Não foi útil").build())))
                .build();
        HttpEntity<WhatsAppMessage> request = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = client.postForEntity(uri, request, String.class);
        log.info(response.getBody());
    }
}
