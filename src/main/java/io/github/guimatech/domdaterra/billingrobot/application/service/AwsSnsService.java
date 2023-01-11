package io.github.guimatech.domdaterra.billingrobot.application.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import io.github.guimatech.domdaterra.billingrobot.application.exception.AwsSnsClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AwsSnsService {

    private static final String AWS_SNS_SMS_TYPE = "AWS.SNS.SMS.SMSType";
    private static final String AWS_SNS_SMS_TYPE_VALUE = "Transactional";
    private static final String AWS_SNS_DATA_TYPE = "String";

    @Autowired
    private AmazonSNS snsClient;

    public void sendSms(String phone, String message) {
        try {
            int requestTimeout = 3000;
            Map<String, MessageAttributeValue> smsAttributes =
                    new HashMap<>();
            smsAttributes.put(AWS_SNS_SMS_TYPE, new MessageAttributeValue()
                    .withStringValue(AWS_SNS_SMS_TYPE_VALUE)
                    .withDataType(AWS_SNS_DATA_TYPE));

            PublishResult request = snsClient.publish(new PublishRequest()
                    .withMessage(message)
                    .withPhoneNumber(phone)
                    .withMessageAttributes(smsAttributes)
                    .withSdkRequestTimeout(requestTimeout));
            log.debug(String.valueOf(request));
        } catch (RuntimeException e) {
            log.error("Ocorreu um erro ao enviar o sms para {} ", phone, e);
            throw new AwsSnsClientException("Ocorreu um erro enquanto enviava o sms, por favor, tente novamente mais tarde ", e);
        }
    }
}
