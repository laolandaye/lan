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
public class DataCenterTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";

    @Autowired
    private DataCenterService dataCenterService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_DS_ArticleListTest() throws Exception {
        String result = dataCenterService.SP_DS_ArticleList();
        System.out.println(result);
    }


}
