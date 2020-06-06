package com.lan.bjgh;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URI;

@Service
public class DataCenterService {

    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";

    @Autowired
    private BjghTokenService bjghTokenService;

    @Autowired
    private RestTemplate restTemplate;

    public String SP_DS_ArticleList() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getDataCenter(openapiUrl);

        String url = openapiUrl + "sPDsArticleList/v1?token=" + token + "&appKey=4028b8816bfed7b8016bfef3ec960000";

        URI uri = URI.create(url);
        JSONObject reauestJson = new JSONObject();
        reauestJson.put("sPDsArticleList", false);
        reauestJson.put("ext2", -1);
        reauestJson.put("ext4Str",  "1");
        reauestJson.put("limit", 10);
        reauestJson.put("mainKeyword", "aaa");
        reauestJson.put("matchScope", 3);
        reauestJson.put("offset", 0);
        reauestJson.put("offset", 1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(reauestJson, headers);

        return restTemplate.postForObject(uri, request, String.class);

    }

}
