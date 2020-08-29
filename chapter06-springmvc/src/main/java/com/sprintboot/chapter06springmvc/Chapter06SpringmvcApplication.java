package com.sprintboot.chapter06springmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.chapter06springmvc.dao")
public class Chapter06SpringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter06SpringmvcApplication.class, args);
    }

}
