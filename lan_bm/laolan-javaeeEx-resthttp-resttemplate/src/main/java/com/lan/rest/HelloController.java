package com.lan.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello测试类
 * @author Administrator
 *
 */

@RestController   // 等价于@Controller+@RequstMapping
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world test!";
    }

}
