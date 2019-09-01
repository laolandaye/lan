package com.lan.springbootwebscoketctrl.databasestatus.timer;

import com.lan.springbootwebscoketctrl.databasestatus.entity.User;
import com.lan.springbootwebscoketctrl.databasestatus.repository.UserRepository;
import com.lan.springbootwebscoketctrl.databasestatus.webscoket.WebSocketServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Date;

public class MyThread implements Runnable {

    @Override
    public void run()  {
        //查询数据更新时间
        WebSocketServlet wbs= null;
        try {
            wbs=new WebSocketServlet();
            WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            UserRepository userRepository=(UserRepository)context.getBean("userRepository");
            userRepository.getCount();
//            wbs.onMessage(sum);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

