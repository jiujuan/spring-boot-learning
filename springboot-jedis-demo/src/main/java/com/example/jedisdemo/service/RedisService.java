package com.example.jedisdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RedisService {
    
    @Autowired
    private JedisPool jedisPool;
    
    // 设置键值对
    public void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }
    
    // 获取值
    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }
    
    // 删除键
    public void delete(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        }
    }
    
    // 更新键值对（如果存在）
    public boolean update(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(key)) {
                jedis.set(key, value);
                return true;
            }
            return false;
        }
    }
    
    // 搜索键
    public List<String> searchKeys(String pattern) {
        try (Jedis jedis = jedisPool.getResource()) {
            Set<String> keys = jedis.keys(pattern);
            return keys.stream().collect(Collectors.toList());
        }
    }
    
    // 使用scan命令进行分页搜索
    public ScanResult<String> scan(String cursor, String pattern, int count) {
        try (Jedis jedis = jedisPool.getResource()) {
            ScanParams scanParams = new ScanParams();
            scanParams.match(pattern);
            scanParams.count(count);
            return jedis.scan(cursor, scanParams);
        }
    }
}