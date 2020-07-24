package com.lan.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理
 */
@Component("pocHandle")
@Configuration
@ControllerAdvice
public class ControllerExceptionHanler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHanler.class);

    /**
     * 捕获所有系统异常  包括系统的404,403等
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> exceptionHandler(Exception e) {
        logger.error(e.getMessage());
        Map<String, String> errorMsg = new HashMap();
        errorMsg.put("errorMsg", e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            errorMsg.put("errorMsg", e.getMessage());
        }
        e.printStackTrace();
        return errorMsg;
    }

    @ExceptionHandler(ApiQuickException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> apiQuickExceptionHandler(ApiQuickException e) {
        logger.error(e.getMsg());
        Map<String, Object> errorMsg = new HashMap();
        errorMsg.put("errorMsg", e.getMsg());
        errorMsg.put("errorCode",e.getCode());
        return errorMsg;
    }
}
