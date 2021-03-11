package com.sxsh.exception;

/**
 * @Description
 * @Author lhy
 * @Date 2020/12/3
 * @Version 1.0.0
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     * @return code
     */
    Integer getCode();

    /**
     * 获取异常信息
     * @return msg
     */
    String getMessage();
}
