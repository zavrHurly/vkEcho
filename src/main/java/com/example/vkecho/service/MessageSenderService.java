package com.example.vkecho.service;

public interface MessageSenderService<T> {
    void send(T message);
}