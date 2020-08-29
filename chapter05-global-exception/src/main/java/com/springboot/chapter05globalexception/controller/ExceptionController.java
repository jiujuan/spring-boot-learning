package com.springboot.chapter05globalexception.controller;

import com.springboot.chapter05globalexception.exception.CustomerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExceptionController {

    @GetMapping("/test1")
    public String test1() {
        int i = 10 / 0;
        return "test1";
    }

    @GetMapping("/test2")
    public Map<String, String> test2() {
        Map<String, String> result = new HashMap<>(16);

        try {
            int i = 10 / 0 ;
            result.put("code", "200");
            result.put("data", "具体返回结果集");
        } catch (Exception e) {
            result.put("code", "500");
            result.put("message", "请求错误");
        }
        return result;
    }

    @GetMapping("/test3")
    public String test3(Integer num) {
        if (num == null) {
            throw new CustomerException(400, "num不能为空");
        }

        int i = 10 / num;
        return "result:" + i;
    }
}