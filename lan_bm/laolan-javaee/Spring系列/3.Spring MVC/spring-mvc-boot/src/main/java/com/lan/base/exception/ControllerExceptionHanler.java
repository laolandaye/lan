package com.lan.base.exception;

import com.lan.base.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHanler {

    /**
     * 捕获所有系统异常  包括系统的404,403等
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        Map<String, String> errorMsg = new HashMap();
        errorMsg.put("errorMsg", e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            errorMsg.put("errorMsg", e.getMessage());
        }
        e.printStackTrace();
        return errorMsg;
    }

    /**
     * 1.参数校验异常捕获  @Validated
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return R.fail(objectError.getDefaultMessage());
    }

    /**
     * 2.参数校验异常捕获  @Valid
     */
    @ExceptionHandler({BindException.class })
    public R BindExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return R.fail(objectError.getDefaultMessage());
    }

    @ExceptionHandler(ApiQuickException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> apiQuickExceptionHandler(ApiQuickException e) {
        log.error(e.getMsg());
        Map<String, Object> errorMsg = new HashMap();
        errorMsg.put("errorMsg", e.getMsg());
        errorMsg.put("errorCode",e.getCode());
        return errorMsg;
    }
}
