package com.lan.springbootwebscoketctrl.databasestatus.controller;

import com.lan.springbootwebscoketctrl.databasestatus.entity.User;
import com.lan.springbootwebscoketctrl.databasestatus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userRepository.findOne(id);
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }

}
