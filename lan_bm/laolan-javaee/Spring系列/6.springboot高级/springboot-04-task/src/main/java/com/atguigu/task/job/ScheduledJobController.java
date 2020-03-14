package com.atguigu.task.job;

import com.atguigu.task.service.ScheduledService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
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
