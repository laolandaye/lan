package com.lan.bjgh;

import com.alibaba.fastjson.JSONObject;
import com.lan.RestTemplateBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
//@SpringBootTest
// 加入启动类，方便spring 注入
@SpringBootTest(classes = RestTemplateBoot.class)
public class MonitoreSupervisionTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";

    @Autowired
    private MonitoreSupervisionService monitoreSupervisionService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_MS_GetBaseAccountListTest() throws Exception {
        String result = monitoreSupervisionService.SP_MS_GetBaseAccountList();
        System.out.println(result);
    }

    @Test
    public void SP_MS_GetSpiderResultListTest() throws Exception {
        String result = monitoreSupervisionService.SP_MS_GetSpiderResultList();
        System.out.println(result);
    }


    @Test
    public void SP_MS_GetSensitiveProgramListTest() throws Exception {
        String result = monitoreSupervisionService.SP_MS_GetSensitiveProgramList();
        System.out.println(result);

    }


}
