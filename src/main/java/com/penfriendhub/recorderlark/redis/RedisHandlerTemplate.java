package com.penfriendhub.recorderlark.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@Component
public class RedisHandlerTemplate<T> {
    @Resource
    RedisTemplate<String, T> redisTemplate;

    public List<T> data (String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public void push (String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public T pop (String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public void setValue (String key, T value, Duration timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    public T getValue (String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteKey (String key) {
        redisTemplate.delete(key);
    }

    public boolean isExists (String key) {
        try {
            T value = this.getValue(key);
            return null != value;
        } catch (Exception e) {
            return false;
        }
    }

}
