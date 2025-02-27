package com.example.jedisdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.jedis.maxIdle}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.maxTotal}")
    private Integer maxTotal;

    @Value("${spring.redis.jedis.minIdle}")
    private Integer minIdle;

    @Value("${spring.redis.jedis.maxWaitMillis}")
    private Long maxWaitMillis;
    @Value("${spring.redis.jedis.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.redis.jedis.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${spring.redis.jedis.testWhileIdle}")
    private Boolean testWhileIdle;

   
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大空闲连接数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 最小空闲连接数
        jedisPoolConfig.setMinIdle(minIdle);

        // 在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);

        // 在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnReturn(testOnReturn);

        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);

        if (password == "") password = null;
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }
}