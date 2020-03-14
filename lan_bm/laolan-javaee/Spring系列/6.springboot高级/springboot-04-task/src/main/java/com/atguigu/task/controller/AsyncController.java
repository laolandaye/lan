package com.atguigu.task.controller;

import com.atguigu.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        return "hello";
    }

    @GetMapping("/helloExectur")
    public String helloExectur(){
        for (int i = 0; i < 100; i++) {
            asyncService.helloExectur(i);
        }
        return "helloExectur";
    }
}
