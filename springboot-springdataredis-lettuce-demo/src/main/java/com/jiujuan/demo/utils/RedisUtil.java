package com.jiujuan.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 设置值
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 设置值并设置过期时间
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 获取值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 修改键值对
    public void update(String key, Object newValue) {
        redisTemplate.opsForValue().set(key, newValue);
    }

    // 删除值
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // 批量删除
    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    // 是否存在key
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // List操作 - 从左边添加
    public Long leftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    // List操作 - 从右边弹出
    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    // List操作 - 从左边弹出第一个元素
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    // List操作 - 获取指定范围的元素
    public List<Object> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // Set操作 - 添加元素
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    // Set操作 - 获取所有元素
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    // ZSet操作 - 添加元素
    public Boolean zAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    // ZSet操作 - 获取指定分数范围的元素
    public Set<Object> zRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    // Hash操作 - 设置hash field
    public void hSet(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    // Hash操作 - 获取hash field
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    // 搜索匹配的键
    public Set<String> searchKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}