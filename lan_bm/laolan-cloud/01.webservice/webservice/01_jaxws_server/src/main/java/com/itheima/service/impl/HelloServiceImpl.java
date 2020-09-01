package com.itheima.service.impl;

import com.itheima.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return name + ",Welcome to Itheima!";
    }
}
