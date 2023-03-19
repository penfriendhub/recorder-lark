package com.penfriendhub.recorderlark.lark;

import com.lark.oapi.service.im.v1.model.P2MessageReceiveV1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LarkMessageEvent implements Serializable {
    private String id;
    private String message;
    private String messageType;
    private String userId;
    private String chatId;
    private String chatType;
    private String parentMessageId;
    private long timestamp;

    public LarkMessageEvent (P2MessageReceiveV1 event) {
        this.id = event.getEvent().getMessage().getMessageId();
        this.userId = event.getEvent().getSender().getSenderId().getOpenId();
        this.chatId = event.getEvent().getMessage().getChatId();
        this.chatType = event.getEvent().getMessage().getChatType();
        this.parentMessageId = event.getEvent().getMessage().getParentId();
        this.timestamp = Long.parseLong(event.getEvent().getMessage().getCreateTime());
        this.messageType = event.getEvent().getMessage().getMessageType();
        this.message = event.getEvent().getMessage().getContent();
    }
}
