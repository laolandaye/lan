package com.lan.bjgh;

import com.alibaba.fastjson.JSONArray;
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
import java.util.LinkedList;

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

    public String SP_DD_FeedBack(String openapiUrl, String appKey, String appSecret) throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getBjghToken(openapiUrl, appKey, appSecret);

        String url = openapiUrl + "sPDdFeedBack/v1?token=" + token + "&appKey=" + appKey;

        URI uri = URI.create(url);
        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("asmId", "101064");
        requestMap.add("transactorId", "70000621");
        requestMap.add("transactorName", "zzz");
        requestMap.add("orgId", "a0fhj79dl156kxxc");
        requestMap.add("asmId", "");
        requestMap.add("tsacStatus", "0");
        requestMap.add("completeTime", "2019-11-07");
        requestMap.add("progDescription", "已经完成百分之八十");
        requestMap.add("P2", "预留字段2");
        requestMap.add("P3", "预留字段3");
        requestMap.add("P4", "预留字段4");
        requestMap.add("P5", "预留字段5");
        requestMap.add("files", new FileSystemResource(new File("D:\\vpn\\aaa.txt")));
        requestMap.add("ASMResult", "执行结果ok");
        String s = " {\"chanelName\":\"渠道2\",\"createTime\":\"2019-11-06\",\"isOriginal\":1,\"title\":\"超链接URL\",\"url\":\"https://www.baidu.com\",\"description\":\"描述2\"}";
        requestMap.add("paramN", s);
        requestMap.add("paramN", s);
        requestMap.add("paramN", s);
        requestMap.add("paramN", s);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(requestMap, headers);

        return restTemplate.postForObject(uri, request, String.class);
    }
}
