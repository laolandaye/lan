package com.lan.demo;

import com.lan.RestTemplateBoot;
import com.lan.bjgh.MonitoreSupervisionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplateBoot.class)
public class PostDemoTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MonitoreSupervisionService monitoreSupervisionService;

    @Before
    public void init() {
    }

    @Test
    public void testGetSimple() {

    }

    @Test
    public void testGetSimpleUrl() {
    }

    // 2.3.1.发送json格式
   // curl  -H "Content-Type:application/json" -X POST --data '{"accountCode":"22_RMTWZ_BJDCOMCN_00_110000","startTime":"2018-11-10 20:00:10","endTime":"2019-11-10 20:00:10","pageSize":12,"pageIndex":1}' "http://pom.beijingcloud.com.cn:8080/TrinityAres-POM/jcjg/api/cm/getSpiderResultList"
    @Test
    public void testPostHeaderByContentType() throws Exception {
        String result = monitoreSupervisionService.SP_MS_GetSpiderResultList();
        System.out.println(result);
    }


}
