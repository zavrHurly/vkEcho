package com.example.vkecho.entity;

import com.example.vkecho.dto.CallBackDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageNewCallBack {
    private Long id;
    private Long date;
    private Long peerId;
    private Long fromId;
    private String text;
    private Long groupId;

    public MessageNewCallBack(CallBackDto callbackDto){
        Map<String, Object> message = (Map<String, Object>) callbackDto.getObject().get("message");
        this.id = Long.parseLong(String.valueOf(message.get("id")));
        this.date = Long.parseLong(String.valueOf(message.get("date")));
        this.peerId = Long.parseLong(String.valueOf(message.get("peer_id")));
        this.fromId = Long.parseLong(String.valueOf(message.get("from_id")));
        this.text = String.valueOf(message.get("text"));
        this.groupId = callbackDto.getGroupId();
    }
}
