package com.lan.bjgh;

import com.lan.RestTemplateBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
//@SpringBootTest
// 加入启动类，方便spring 注入
@SpringBootTest(classes = RestTemplateBoot.class)
public class ChangpingTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";

    @Autowired
    private ChangpingService changpingService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_CK_AddPushTest() throws Exception {
        String result = changpingService.SP_CK_AddPush();
        System.out.println(result);
    }


}
