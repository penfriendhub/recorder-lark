package com.penfriendhub.recorderlark.lark;

import com.lark.oapi.service.im.v1.model.MentionEvent;
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
    /**
     * Message event ID, mainly messageId
     */
    private String id;
    /**
     * Message event content,
     * different messageType content is not the same
     */
    private String message;
    /**
     * Message event content type, e.g. text post image files etc.
     */
    private String messageType;
    /**
     * User ID
     */
    private String userId;
    /**
     * Chat Room ID
     */
    private String chatId;
    /**
     * Chat types, P2P and group
     */
    private String chatType;
    /**
     * Parent message ID, meaning the ID of the reply message
     */
    private String parentMessageId;
    /**
     * To mention the list of people,
     * the message mentions users using @_user_1 @_user_2 ...
     */
    private MentionEvent[] mention;
    /**
     * Timestamp of message event creation
     */
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
        this.mention = event.getEvent().getMessage().getMentions();
    }
}
