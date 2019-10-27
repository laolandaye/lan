package com.lan.bjgh;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ChangpingService {

    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";

    @Autowired
    private BjghTokenService bjghTokenService;

    @Autowired
    private RestTemplate restTemplate;

    public String SP_CK_AddPush() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getChangping(openapiUrl);

        String url = openapiUrl + "sPCkAddPush/v1?token=" + token + "&appKey=2c9849536da9faae016db38edd310011";

        URI uri = URI.create(url);

        JSONObject reauestJson = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("groupId", 1);
        content.put("sourceId", System.currentTimeMillis() + "");
        content.put("type", 3);
        content.put("title", "“回天有我”出实招，民主议事解难题！");
        content.put("instId", "6569491947226924037");
        JSONArray clips = new JSONArray();
        JSONObject clip = new JSONObject();
        clip.put("groupId", 1);
        clip.put("title", "“回天有我”出实招，民主议事解难题！");
        clip.put("type", 3);
        clip.put("descp", "<p>一转眼，2018年即将挥手远去，再过两周就将进入新的一年。社区党员、志愿者和居民参与“回天有我”社会服务活动的热情更加高涨。各社区纷纷开起了民主议事座谈会，为共建美好幸福新家园出谋划策。</p>");
        clips.add(clip);
        reauestJson.put("content", content);
        reauestJson.put("clips", clips);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
        map.add("data", reauestJson);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForObject(uri, request, String.class);

    }

}
