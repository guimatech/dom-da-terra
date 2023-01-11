package io.github.guimatech.domdaterra.billingrobot.infra.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSnsConfig {

    @Value("${aws.accessKeyId}")
    private String keyId;

    @Value("${aws.secretAccessKey}")
    private String secretKey;

    @Bean
    public AmazonSNS amazonSNS() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(keyId, secretKey);

        return AmazonSNSClient.builder()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
