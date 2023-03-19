package com.penfriendhub.recorderlark.service;

import com.penfriendhub.recorderlark.lark.LarkMessageEvent;
import com.penfriendhub.recorderlark.redis.RedisHandlerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author unickcheng
 * @since 2023-03-20
 */

@Service
public class RecorderLarkService {
    @Resource
    private RedisHandlerTemplate<LarkMessageEvent> redisRecorder;
    @Value("${demo.token}")
    private String tempToken;

    /**
     * Return all data temporarily
     * @param token simulated authentication token
     */
    public Object page (String token) {
        if (tempToken.equals(token)) {
            return redisRecorder.data("recorder");
        }
        return "token is invalid.";
    }
}
