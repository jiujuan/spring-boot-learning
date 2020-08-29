package com.springboot.chapter21;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class Chapter21Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter21Application.class, args);
    }

}
