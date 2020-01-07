package com.lan.bjgh;

import com.lan.RestTemplateBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
//@SpringBootTest
// 加入启动类，方便spring 注入
@SpringBootTest(classes = RestTemplateBoot.class)
public class PeopleDailyTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";
    private String appKey = "2c9849536e21bb29016e6e16840f007e";
    private String appSecret = "XfB91IKGj7t26hx";

    @Autowired
    private CentralKitchenService centralKitchenService;

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
        ResponseEntity<String> result = centralKitchenService.SP_CK_AddPush(openapiUrl, appKey, appSecret);
        System.out.println(result);
    }


}
