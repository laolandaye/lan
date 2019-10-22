package com.lan.bjgh.dataCenter;

import com.alibaba.fastjson.JSONObject;
import com.lan.RestTemplateBoot;
import com.lan.bjgh.BjghToken;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
//@SpringBootTest
// 加入启动类，方便spring 注入
@SpringBootTest(classes = RestTemplateBoot.class)
public class DataCenterTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    public String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";

    @Autowired
    public BjghToken bjghToken;

    @Autowired
    public RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_MS_GetBaseAccountListTest() throws Exception {
        // 1. 先获取token
        String token = bjghToken.getDataCenterToken(openapiUrl);

        String url = openapiUrl + "sPMsGetBaseAccountList?token=" + token + "&appKey=2c9849536c502347016c506774120003";

        URI uri = URI.create(url);
        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestMap, headers);

        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);
    }

    @Test
    public void SP_MS_GetSpiderResultListTest() throws Exception {
        // 1. 先获取token
        String token = bjghToken.getDataCenterToken(openapiUrl);

        String url = openapiUrl + "sPMsGetSpiderResultList?token=" + token + "&appKey=2c9849536c502347016c506774120003";

        URI uri = URI.create(url);
        JSONObject reauestJson = new JSONObject();
        reauestJson.put("accountCode", "22_RMTWZ_BJDCOMCN_00_110000");
        reauestJson.put("startTime", "2018-11-10 20:00:10");
        reauestJson.put("endTime", "2019-11-10 20:00:10");
        reauestJson.put("pageSize", 12);
        reauestJson.put("pageIndex", 1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(reauestJson, headers);

        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);
    }

    @Test
    public void SP_MS_GetSensitiveProgramListTest() throws Exception {
        // 1. 先获取token
        String token = bjghToken.getDataCenterToken(openapiUrl);

        String url = openapiUrl + "sPMsGetSensitiveProgramList/v1?token=" + token + "&appKey=2c9849536c502347016c506774120003";

        URI uri = URI.create(url);
        JSONObject reauestJson = new JSONObject();
        reauestJson.put("accountCode", "22_RMTWZ_BJDCOMCN_00_110000");
        reauestJson.put("startTime", "2018-11-10 20:00:10");
        reauestJson.put("endTime", "2019-11-10 20:00:10");
        reauestJson.put("pageSize", 12);
        reauestJson.put("pageIndex", 1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(reauestJson, headers);

        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);

    }
}
