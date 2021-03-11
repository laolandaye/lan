package com.sxsh.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//业务异常
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends Exception{

    private Integer code;

    private String message;

    public BusinessException(String message) {
        this.message = message;
    }
}
