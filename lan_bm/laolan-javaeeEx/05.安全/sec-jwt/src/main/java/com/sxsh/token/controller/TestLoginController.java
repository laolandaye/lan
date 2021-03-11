package com.sxsh.token.controller;

import com.sxsh.annotation.Anoymous;
import com.sxsh.token.entity.UserLocal;
import com.sxsh.token.entity.R;
import com.sxsh.util.AuthLocalTokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testLogin")
public class TestLoginController {

    @GetMapping("/needLogin")
    R needLogin(){
        UserLocal userLocal = AuthLocalTokenUtil.getUser();
        System.out.println(userLocal);
        return R.success("needLogin");
    }

    @GetMapping("/noNeedLogin")
    @Anoymous
    R noNeedLogin(){
        return R.success("noNeedLogin");
    }

}

