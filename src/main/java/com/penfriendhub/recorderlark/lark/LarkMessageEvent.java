package com.penfriendhub.recorderlark.lark;

import com.google.gson.Gson;
import com.lark.oapi.service.im.v1.model.P2MessageReceiveV1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@Getter
public class LarkMessageEvent implements Serializable {
    private final String id;
    private final String message;
    private final String userId;
    private final String chatId;
    private final String chatType;
    private final String parentMessageId;
    private final long timestamp;

    public LarkMessageEvent (P2MessageReceiveV1 event) {
        this.id = event.getEvent().getMessage().getMessageId();
        this.message = this.getMessageText(event.getEvent().getMessage().getContent());
        this.userId = event.getEvent().getSender().getSenderId().getOpenId();
        this.chatId = event.getEvent().getMessage().getChatId();
        this.chatType = event.getEvent().getMessage().getChatType();
        this.parentMessageId = event.getEvent().getMessage().getParentId();
        this.timestamp = Long.parseLong(event.getEvent().getMessage().getCreateTime());
    }

    /**
     * Extract message text from JSON string
     * @param content Json String message
     */
    private String getMessageText (String content) {
        return new Gson().fromJson(content, LarkMessageContent.class)
                .getText()
                .replaceAll("@_user_[0-9]", "")
                .trim();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LarkMessageContent {
        private String text;
    }
}
