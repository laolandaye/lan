package com.lan.bjgh;

import com.kun.framework.crypto.Crypto;
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
public class PingGuTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";
    private String appKey = "2c9849546e21bbe9016e3ffaad4b0069";
    private String appSecret = "gl37bQK1gMfOxl0";

    @Autowired
    private ScheduleCommandService scheduleCommandService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_DD_FeedBackTest() throws Exception {
        String result = scheduleCommandService.SP_DD_FeedBack(openapiUrl, appKey, appSecret);
        System.out.println("解密前：" + result);

        Crypto crypto = (Crypto) Class.forName("com.kun.framework.crypto.impl.Base64CodecWrapper").newInstance();
        result = crypto.decrypt(result);
        System.out.println("解密后：" + result);
    }


}
