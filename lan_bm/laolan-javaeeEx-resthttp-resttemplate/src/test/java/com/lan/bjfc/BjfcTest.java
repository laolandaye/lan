package com.lan.bjfc;

import com.lan.RestTemplateBoot;
import com.lan.bjsc.BjfcService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
// 加入启动类，方便spring 注入
@SpringBootTest(classes = RestTemplateBoot.class)
public class BjfcTest {

    //    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    private String openapiUrl = "http://localhost:8080/dam/service/";
    private String appKey = "8aa68abb6e5854e4016e5d2dd124000a";
    private String appSecret = "5qVYSqvtkMcn11u";

    @Autowired
    private BjfcService bjfcService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_DD_FeedBackTest1() throws Exception {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                bjfcService.SP_DD_FeedBack(openapiUrl, appKey, appSecret, finalI);
                            } catch (Exception e) {
                            }
                        }
                    }
            ).start();
        }



    }


}
