package com.yang.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 */
@ControllerAdvice
public class LogControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogControllerAdvice.class);

    @ModelAttribute("test")
    public String test666() {
        return "this is test";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        return "Exception!";
    }

    /**
     * 处理所有业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handleBusinessException(RuntimeException e) {
        LOGGER.error(e.getMessage(), e);

        return e.getMessage();
    }
}
