package com.sxsh.token.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author lhy
 * @Date 2020/12/9
 * @Version 1.0.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserLocal {

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 登录ID
     */
    private Integer shopUserId;

    /**
     * 平台ID
     */
    private Integer spreadId;

    /**
     * 代理ID
     */
    private Integer agentSid;

    /**
     * 平台用户id
     * */
    private Integer platformUserId;
}
