package com.lan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/bjsjfc")
    private String helloWorld(){
        return "bjsjfc";
    }
}
