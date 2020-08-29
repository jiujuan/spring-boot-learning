package com.springboot.chapter5cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Chapter5CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter5CacheApplication.class, args);
    }

}
