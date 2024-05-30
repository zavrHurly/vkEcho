package com.example.vkecho.service;

import com.example.vkecho.dto.MessageSendResultDto;
import com.example.vkecho.dto.MessageSendDto;
import com.example.vkecho.exception.MessageSenderException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VkMessageSenderService implements MessageSenderService<MessageSendDto> {
    private static final int TEXT_MAX_LENGTH = 2000;
    private final RestTemplate restTemplate;
    private final VkUriCreator vkUriCreator;

    @Override
    public void send(MessageSendDto message) {
        List<MessageSendDto> messages = parseIfRequired(message);
        messages.forEach(this::sendInternal);
    }

    private List<MessageSendDto> parseIfRequired(MessageSendDto dto) {
        String originalMessage = dto.getMessage();
        int capacity = originalMessage.length() / TEXT_MAX_LENGTH + 1;
        ArrayList<MessageSendDto> result = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            int beginIndex = i * TEXT_MAX_LENGTH;
            int endIndex = Math.min((i + 1) * TEXT_MAX_LENGTH, originalMessage.length());
            dto.setMessage(originalMessage.substring(beginIndex, endIndex));
            result.add(dto);
        }
        return result;
    }

    private void sendInternal(MessageSendDto message) {
        message.setRandomId();
        URI uri = vkUriCreator.createUri(message);
        ResponseEntity<MessageSendResultDto> responseEntity = restTemplate.postForEntity(uri, null, MessageSendResultDto.class);
        validateResponse(responseEntity);
    }

    private void validateResponse(ResponseEntity<MessageSendResultDto> responseEntity) {
        MessageSendResultDto.MessagesSendErrorResultDto error = Objects.requireNonNull(responseEntity.getBody()).getError();
        if (error != null && error.getErrorCode() != null) {
            throw new MessageSenderException(error.getErrorMsg());
        }
    }
}
