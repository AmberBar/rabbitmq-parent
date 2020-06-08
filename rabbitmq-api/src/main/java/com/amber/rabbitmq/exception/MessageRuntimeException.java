package com.amber.rabbitmq.exception;

/**
 * @Author: Amber
 */
public class MessageRuntimeException extends RuntimeException {

    public MessageRuntimeException() {
    }

    public MessageRuntimeException(String message) {
        super(message);
    }

    public MessageRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageRuntimeException(Throwable cause) {
        super(cause);
    }

    public MessageRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
