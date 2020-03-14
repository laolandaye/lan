package com.lan.bjgh;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CentralKitchenService {

    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";

    @Autowired
    private BjghTokenService bjghTokenService;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> SP_CK_ScriptTotalCount() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "SPCKScriptTotalCount/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    @Async("taskExector")
    public void SP_CK_ScriptTotalCountThread(int i) throws Exception {
        ResponseEntity<String> result = SP_CK_ScriptTotalCount();
        System.out.println(i + "result:" + result.getBody());
    }

    public ResponseEntity<String> SP_CK_TodayUploadScriptCount() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "sPCkTodayUploadScriptCount/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> SP_CK_SingleDayUploadScriptCount() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "sPCkSingleDayUploadScriptCount/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001&zeroTimeOfDay=1562774400";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> SP_CK_ClueTotalCount() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "sPCkClueTotalCount/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> SP_CK_TodayUploadClueCount() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "sPCkTodayUploadClueCount/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> SP_CK_ScriptTotalCountByStatus() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "sPCkScriptTotalCountByStatus/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001&status=0";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> SP_CK_TodayScriptTotalCountByStatus() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "spCkTodayScriptTotalCountByStatus/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001&status=0";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<String> SP_CK_NewMediaChannelPublishStatistics() throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getCentralKitchen(openapiUrl);

        String url = openapiUrl + "sPCkNewMediaChannelPublishStatistics/v1?token=" + token + "&appKey=2c9849536c502347016c506720ee0001";

        URI uri = URI.create(url);

        return restTemplate.getForEntity(uri, String.class);
    }

    @Async("taskExector")
    public ResponseEntity<String> SP_CK_AddPush(String openapiUrl, String appKey, String appSecret) throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getBjghToken(openapiUrl, appKey, appSecret);

        String url = openapiUrl + "sPCkAddPush/v1?token=" + token + "&appKey=" + appKey;

        URI uri = URI.create(url);

        JSONObject reauestJson = new JSONObject();
        JSONObject data = new JSONObject();
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
        data.put("content", content);
        data.put("clips", clips);
        reauestJson.put("data", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
        map.add("data", reauestJson);

        HttpEntity<JSONObject> request = new HttpEntity<>(reauestJson, headers);

        return restTemplate.postForEntity(uri, request, String.class);

    }

}
