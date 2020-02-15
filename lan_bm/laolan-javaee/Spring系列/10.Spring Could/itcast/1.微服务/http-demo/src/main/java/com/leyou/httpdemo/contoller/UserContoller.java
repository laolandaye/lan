package com.leyou.httpdemo.contoller;

import com.leyou.httpdemo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserContoller {

    @GetMapping("hello")
    public User hello() {
        User user = new User();
        user.setAge(22);
        user.setBirthday(new Date());
        user.setCreated(new Date());
        user.setId(1L);
        user.setUserName("老兰");
        return user;
    }
}
