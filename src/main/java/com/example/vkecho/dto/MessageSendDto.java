package com.example.vkecho.dto;

import com.example.vkecho.entity.MessageNewCallBack;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@JsonPropertyOrder(alphabetic = true)
@EqualsAndHashCode(exclude = "randomId")
@AllArgsConstructor
@NoArgsConstructor
public class MessageSendDto implements Serializable {
    @JsonProperty(value = "user_id")
    Long userId;
    @JsonProperty(value = "random_id")
    Long randomId;
    @JsonProperty(value = "peer_id")
    Long peerId;
    String domain;
    @JsonProperty(value = "chat_id")
    Long chatId;
    @JsonProperty(value = "user_ids")
    List<Long> userIds;
    String message;
    Double lat;
    @JsonProperty(value = "long")
    Double longField;
    String attachment;
    @JsonProperty(value = "reply_to")
    Long replyTo;
    @JsonProperty(value = "forward_messages")
    String forwardMessages;
    @JsonProperty(value = "sticker_id")
    Long stickerId;
    @JsonProperty(value = "group_id")
    Long groupId;
    Map<String, String> keyboard;
    Long payload;
    @JsonProperty(value = "dont_parse_links")
    Integer dontParseLinks;

    public MessageSendDto(MessageNewCallBack message){
        this.peerId = message.getPeerId();
        this.message = "Вы сказали: " + message.getText();
        this.groupId = message.getGroupId();
    }

    public void setRandomId() {
        this.randomId = (long) (Math.random() * Math.random());
    }
}
