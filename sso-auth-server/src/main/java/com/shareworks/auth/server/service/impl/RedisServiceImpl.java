package com.shareworks.auth.server.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author martin.peng
 * @Desc
 * @date 2022/5/10 14:28
 */
@Service
@AllArgsConstructor
public class RedisServiceImpl {

    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long l, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, l, timeUnit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public Boolean setIfAbsent(String key, String value, long l, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, l, timeUnit);
    }

    public Boolean setIfPresent(String key, String value) {
        return redisTemplate.opsForValue().setIfPresent(key, value);
    }

    public Boolean setIfPresent(String key, String value, long l, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfPresent(key, value, l, timeUnit);
    }

    public void multiSet(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public Boolean multiSetIfAbsent(Map<String, String> map) {
        return redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    public Object getAndSet(String key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public List<Object> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Long increment(String key, long number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    public Double increment(String key, double number) {
        return redisTemplate.opsForValue().increment(key, number);
    }

    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    public Long decrement(String key, long number) {
        return redisTemplate.opsForValue().decrement(key, number);
    }

    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    public Boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }
}
