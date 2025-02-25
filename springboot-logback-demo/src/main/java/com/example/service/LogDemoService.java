package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {
    private static final Logger logger = LoggerFactory.getLogger(LogDemoService.class);
    
    public void performLogging() {
        logger.trace("s:这是一条 TRACE 日志");
        logger.debug("s:这是一条 DEBUG 日志");
        logger.info("s:这是一条 INFO 日志");
        logger.warn("s:这是一条 WARN 日志");
        logger.error("s:这是一条 ERROR 日志");
    }
}