package com.springboot.chapter5redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
class Chapter5RedisApplicationTests {

    @Autowired
   private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    public void test() throws Exception {
        System.out.println("CacheManager type : " + cacheManager.getClass());

        // 创建1条记录
        userRepository.save(new User("aaaa", 10));

        User u1 = userRepository.findByName("aaaa");
        System.out.println("第一次查询：" + u1.getAge());

        User u2 = userRepository.findByName("aaaa");
        System.out.println("第二次查询：" + u2.getAge());
    }
}
