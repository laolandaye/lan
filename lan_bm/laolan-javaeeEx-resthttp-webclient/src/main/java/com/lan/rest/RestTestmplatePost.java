package com.lan.rest;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RestTestmplatePost {

    @ResponseBody
    @RequestMapping(path = "post", method = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
    public String post(HttpServletRequest request,
                       @RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "nick", required = false) String nick) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("result", "add " + email + " # " + nick + " success!");
        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping(path = "success")
    public String loginSuccess(String email, String nick) {
        return "welcome " + nick;
    }

    @RequestMapping(path = "post2", method = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST}, produces = "charset/utf8")
    public String post2(HttpServletRequest request, @RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "nick", required = false) String nick) {
        return "redirect:/success?email=" + email + "&nick=" + nick + "&status=success";
    }
}
