package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogDemoController {
    private static final Logger logger = LoggerFactory.getLogger(LogDemoController.class);

    @GetMapping("/test-log")
    public String testLog() {
        logger.trace("这是一条 TRACE 日志");
        logger.debug("这是一条 DEBUG 日志");
        logger.info("这是一条 INFO 日志");
        logger.warn("这是一条 WARN 日志");
        logger.error("这是一条 ERROR 日志");
        return "日志测试完成，请查看控制台和日志文件";
    }
}