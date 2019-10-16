package com.lan.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {


    @RequestMapping(value="/execDp",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public BaseResult getResult() throws Exception {


        BaseResult baseResult = new BaseResult();
        baseResult.setCode("200");
        baseResult.setMsg("正在调用");

        return baseResult;


    }
}
