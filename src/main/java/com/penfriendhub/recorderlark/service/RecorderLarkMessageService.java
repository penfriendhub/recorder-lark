package com.penfriendhub.recorderlark.service;

import com.lark.oapi.core.utils.Jsons;
import com.lark.oapi.event.EventDispatcher;
import com.lark.oapi.service.im.v1.ImService;
import com.lark.oapi.service.im.v1.model.P2MessageReceiveV1;
import com.penfriendhub.recorderlark.lark.LarkInfo;
import com.penfriendhub.recorderlark.lark.LarkMessageEvent;
import com.penfriendhub.recorderlark.redis.RedisHandlerTemplate;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@Getter
@Service
public class RecorderLarkMessageService {

    @Resource
    private LarkInfo larkInfo;
    @Resource
    private RedisHandlerTemplate<LarkMessageEvent> redisRecorder;
    @Resource
    private RedisHandlerTemplate<String> redisVisit;

    private EventDispatcher eventDispatcher;

    @PostConstruct
    private void initEventDispatcher () {
        this.eventDispatcher = EventDispatcher.newBuilder(larkInfo.getVerificationToken(), larkInfo.getEncryptKey())
                .onP2MessageReceiveV1(new ImService.P2MessageReceiveV1Handler() {
                    @Override
                    public void handle(P2MessageReceiveV1 event)  {
                        System.out.println(Jsons.DEFAULT.toJson(event));
                        LarkMessageEvent larkMessageEvent =  new LarkMessageEvent(event);
                        if (isEventValid(larkMessageEvent)) {
                            messageRecorder(larkMessageEvent);
                            redisVisit.setValue(larkMessageEvent.getId(), "1", Duration.ofHours(2));
                        }
                    }
                }).build();
    }

    /**
     * Avoid re-requesting historical requests <br/>
     * No more requests condition: history of requests over 60s; already requested message events
     */
    private boolean isEventValid (LarkMessageEvent event) {
        return Math.abs((System.currentTimeMillis() - event.getTimestamp()) / 1000) <= 60
                && !redisVisit.isExists(event.getId());
    }

    /**
     * Core handlers for message events
     */
    private void messageRecorder (LarkMessageEvent event) {
        redisRecorder.push("recorder", event);
    }
}
