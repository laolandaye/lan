package com.lan.bjgh;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import org.apache.http.Header;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonitoreSupervisionTest {

    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    public String appKey = "2c9849536c502347016c506774120003";
    public String appSecret = "Am3iIOHFnPkItsY";

    private MonitoreSupervision monitoreSupervision;

    @Before
    public void setUp() throws Exception {
        monitoreSupervision = new MonitoreSupervision();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_MS_GetBaseAccountListTest() throws Exception {
        String result = monitoreSupervision.SP_MS_GetBaseAccountList(openapiUrl, appKey, appSecret);
        System.out.println(result);
    }

    @Test
    public void SP_MS_GetSpiderResultListTest() throws Exception {
        String result = monitoreSupervision.SP_MS_GetSpiderResultList(openapiUrl, appKey, appSecret);
        System.out.println(result);
    }

    @Test
    public void SP_MS_GetSensitiveProgramListTest() throws Exception {
        String result = monitoreSupervision.SP_MS_GetSensitiveProgramList(openapiUrl, appKey, appSecret);
        System.out.println(result);
    }
}
