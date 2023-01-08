package io.github.guimatech.domdaterra.application.exception;

public class AwsSnsClientException extends RuntimeException {
    public AwsSnsClientException(String message, RuntimeException exception) {
        super(message, exception);
    }
}
