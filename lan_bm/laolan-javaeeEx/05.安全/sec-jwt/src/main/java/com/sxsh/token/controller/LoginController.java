package com.sxsh.token.controller;


import com.sxsh.annotation.Anoymous;
import com.sxsh.token.entity.R;
import com.sxsh.token.request.LoginRequest;
import com.sxsh.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 总公司管理员账号表 前端控制器
 * </p>
 *
 * @author wxf
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/t-spread-sys-user")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping("/login")
    @Anoymous
    R login(@RequestBody LoginRequest loginRequest){
        R result=R.fail();
        if("super".equals(loginRequest.getAccount()) && "123456".equals(loginRequest.getPassword())) {
            String token = jwtUtil.genToken(1);
            result=R.success("登录成功！",token);
        }
        return result;
    }









}

