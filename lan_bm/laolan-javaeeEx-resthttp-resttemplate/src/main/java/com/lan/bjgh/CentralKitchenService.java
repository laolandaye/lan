package com.lan.bjgh;

import com.alibaba.fastjson.JSON;
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

    public ResponseEntity<String> SP_CK_AddPush(String openapiUrl, String appKey, String appSecret) throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getBjghToken(openapiUrl, appKey, appSecret);

        String url = openapiUrl + "sPCkAddPush/v1?token=" + token + "&appKey=" + appKey;
        url += "&accessKeyId=00099FD49C804CDF87C0138C28909282";
        url += "&accessSecret=000AFA4725B84D1F8A72384F78646228";

        URI uri = URI.create(url);
        String requestStr = "{" +
                "    \"data\":{" +
                "        \"content\":{" +
                "            \"bgurl\":\"\"," +
                "            \"descp\":\"测试共享标题fsdfsdfsdfsdfsdfsdfsds测试共享标题fsdfsdfsdfsdfsdfsdfsds测试共享标题fsdfsdfsdfsdfsdfsdfsds\"," +
                "            \"groupId\":\"2\"," +
                "            \"originInfo\":\"\"," +
                "            \"sourceId\":\"" + System.currentTimeMillis() + "\"," +
                "            \"tag\":\"\"," +
                "            \"sourceType\":3," +
                "            \"instId\":\"6564850639522759683\"," +
                "            \"title\":\"测试共享标题fsdfsdfsdfsdfsdfsdfsds\"," +
                "            \"type\":0" +
                "        }" +
                "    }" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(JSON.parseObject(requestStr), headers);
        return restTemplate.postForEntity(uri, request, String.class);

    }

    public ResponseEntity<String> SP_CK_AddClue(String openapiUrl, String appKey, String appSecret) throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getBjghToken(openapiUrl, appKey, appSecret);

        String url = openapiUrl + "sPCkAddClue/v1?token=" + token + "&appKey=" + appKey;
        url += "&accessKeyId=00099FD49C804CDF87C0138C28909282";
        url += "&accessSecret=000AFA4725B84D1F8A72384F78646228";

        URI uri = URI.create(url);
        String requestStr = "{" +
                "    \"data\":{" +
                "        \"userId\":\"6564850645529003011\"," +
                "        \"title\":\"邢台市污水处理二厂美能超滤膜系统交付使用\"," +
                "        \"createdTime\":\"2019-02-28 17:28:00\"," +
                "        \"content\":\"<p>近日。 </p> \"," +
                "        \"origin\":\"东方头条-社会新闻\"," +
                "        \"originUrl\":\"http://mini.eastday.com/a/190228172858847.html\"," +
                "        \"originId\":\"1fb843839a853b68ae1814f6aa9180fe\"," +
                "        \"instId\":\"6564850639522759683\"," +
                "        \"tag\": \"打游戏\"" +
                "    }" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(JSON.parseObject(requestStr), headers);
        return restTemplate.postForEntity(uri, request, String.class);

    }

    public ResponseEntity<String> SP_CK_DisSaveToShare(String openapiUrl, String appKey, String appSecret) throws Exception {
        // 1. 先获取token
        String token = bjghTokenService.getBjghToken(openapiUrl, appKey, appSecret);

        String url = openapiUrl + "sPCkDisSaveToShare/v1?token=" + token + "&appKey=" + appKey;
        url += "&accessKeyId=00099FD49C804CDF87C0138C28909282";
        url += "&accessSecret=000AFA4725B84D1F8A72384F78646228";

        URI uri = URI.create(url);
        String requestStr = "{" +
                "    \"data\":{" +
                "        \"userId\":\"\"," +
                "        \"instId\":\"6564850639522759683\"," +
                "        \"folderId\":\"\"," +
                "        \"name\":\"测试选题1发送到发送到发送到发生\"," +
                "        \"type\":0," +
                "        \"descp\":\"111212121\"," +
                "        \"groupId\":\"1\"" +
                "    }" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(JSON.parseObject(requestStr), headers);
        return restTemplate.postForEntity(uri, request, String.class);

    }

}
