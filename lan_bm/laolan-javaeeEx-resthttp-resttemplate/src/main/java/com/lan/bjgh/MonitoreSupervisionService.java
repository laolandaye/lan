package com.lan.bjgh;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.security.provider.MD5;

import java.net.URI;

@Service
public class MonitoreSupervisionService {

    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";

    @Autowired
    private BjghTokenService bjghTokenService;

    @Autowired
    private RestTemplate restTemplate;

    public String SP_MS_GetBaseAccountList() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getMonitoreSupervision(openapiUrl);

        String url = openapiUrl + "sPMsGetBaseAccountList?token=" + token + "&appKey=2c9849536c502347016c506774120003";

        URI uri = URI.create(url);
        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestMap, headers);

        return restTemplate.postForObject(uri, request, String.class);
    }

    public String SP_MS_GetSpiderResultList() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getMonitoreSupervision(openapiUrl);

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

        return restTemplate.postForObject(uri, request, String.class);
    }

    public String SP_MS_GetSensitiveProgramList() throws Exception {

        // 1. 先获取token
        String token = bjghTokenService.getMonitoreSupervision(openapiUrl);

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

        return restTemplate.postForObject(uri, request, String.class);
    }
}
