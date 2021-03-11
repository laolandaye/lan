package com.sxsh.token.request;

import lombok.Data;

@Data
public class LoginRequest {

    /**
     * 账号
     * */
    private String account;

    /**
     * 登录方式
     * */
    private Integer loginType;

    /**
     * 登录密码
     * */
    private String password;

}
