package com.springboot.chapter3mybatistwo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.chapter3mybatistwo.dao")
public class Chapter3MybatisTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter3MybatisTwoApplication.class, args);
    }

}
