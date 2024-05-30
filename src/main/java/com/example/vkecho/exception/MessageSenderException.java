package com.example.vkecho.exception;

public class MessageSenderException extends RuntimeException {
    public MessageSenderException(String message) {
        super(message);
    }

    public MessageSenderException(Throwable cause) {
        super(cause);
    }
}
