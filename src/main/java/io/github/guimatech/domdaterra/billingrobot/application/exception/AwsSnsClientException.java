package io.github.guimatech.domdaterra.billingrobot.application.exception;

public class AwsSnsClientException extends RuntimeException {
    public AwsSnsClientException(String message, RuntimeException exception) {
        super(message, exception);
    }
}
