package com.sprintboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
class DemoApplicationTests {

    @RequestMapping("/hello")
    public String index() {
        return "hello world, Spring boot is started!";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplicationTests.class, args);
    }

}
