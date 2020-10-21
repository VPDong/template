package com.temp.server.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExcepConfig {
    private static Logger LOGGER = LoggerFactory.getLogger(WebExcepConfig.class);

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        LOGGER.error("系统异常【全局异常】：" + e.getMessage(), e);
        return "error";
    }
}
