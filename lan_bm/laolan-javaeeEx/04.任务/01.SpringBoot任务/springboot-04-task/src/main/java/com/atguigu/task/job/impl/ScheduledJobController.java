package com.atguigu.task.job.impl;

import com.atguigu.task.job.ScheduledService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/job")
public class ScheduledJobController extends ScheduledService {

    @GetMapping("/hello")
    @Override
    public void hello(){
        System.out.println("我是子类重写的hello ... " + new Date());
    }
}
