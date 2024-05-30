package com.example.vkecho.service;

import com.example.vkecho.config.VkApiConfig;
import com.example.vkecho.dto.CallBackDto;
import com.example.vkecho.dto.MessageSendDto;
import com.example.vkecho.entity.MessageNewCallBack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MessageNewCallBackService implements CallBackService {
    private final VkApiConfig vkApiConfigurationProperties;
    private final MessageSenderService<MessageSendDto> messageSenderService;

    @Override
    public String handleCallback(CallBackDto callBackDto) {
        validateSecret(callBackDto);
        switch (Objects.requireNonNull(callBackDto.getType())) {
            case confirmation: {
                return vkApiConfigurationProperties.getConfirmation();
            }
            case message_new: {
                MessageNewCallBack messageNewCallback = new MessageNewCallBack(callBackDto);
                callBackDto.getType();
                handleMessageNew(messageNewCallback);
                return "ok";
            }
            default: {
                throw new UnsupportedOperationException("Service support only 'message_new' callback type");
            }
        }
    }

    private void validateSecret(CallBackDto callbackDto) {
        if (!vkApiConfigurationProperties.getSecret().equals(callbackDto.getSecret())) {
            throw new InvalidParameterException("Invalid secret");
        }
    }

    private void handleMessageNew(MessageNewCallBack messageNewCallback) {
        messageSenderService.send(new MessageSendDto(messageNewCallback));
    }
}
