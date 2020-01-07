package com.lan.bjgh;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

/**
 *  appKey                              appSecret
 *  2c9849536c502347016c5066d04d0000    5XoT5esvS0CS8oF     用户管理
 *  2c9849536c502347016c506720ee0001    6AOrH0nIqOsz4cN     中央厨房
 *  2c9849536c502347016c50674baf0002    BSzzG2TwjCoZw36     指挥调度
 *  2c9849536c502347016c506774120003    Am3iIOHFnPkItsY    监测监管
 *  2c9849536da9faae016db38edd310011    dKvpVM0GgOkMns6     昌平
 */
@Service
public class BjghTokenService {

    private static final Logger logger = LoggerFactory.getLogger(BjghTokenService.class);

    @Autowired
    private RestTemplate restTemplate;

    // 中央厨房
    public String getCentralKitchen(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849536c502347016c506720ee0001");
        map.add("appSecret", "6AOrH0nIqOsz4cN");
        return getBjghToken(openapiUrl, map);
    }


    // 指挥调度
    public String getCentralKitchen3(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849536c502347016c50674baf0002");
        map.add("appSecret", "BSzzG2TwjCoZw36");
        return getBjghToken(openapiUrl, map);
    }

    // 监测监管
    public String getMonitoreSupervision(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849536c502347016c506774120003");
        map.add("appSecret", "Am3iIOHFnPkItsY");
        return getBjghToken(openapiUrl, map);
    }

    // 昌平
    public String getChangping(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849536da9faae016db38edd310011");
        map.add("appSecret", "dKvpVM0GgOkMns6");
        return getBjghToken(openapiUrl, map);
    }

    // 延庆大屏
    public String getChangping2(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849546d39b4bd016d624fbbf600bb");
        map.add("appSecret", "JCuyAi1FFKj2pZo");
        return getBjghToken(openapiUrl, map);
    }

    // 门头沟
    public String getChangping3(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849546d39b4bd016d6255beb100bc");
        map.add("appSecret", "ZmO36ib7jWf3AfQ");
        return getBjghToken(openapiUrl, map);
    }

    // 数据中台
    public String getDataCenter(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "4028b8816bfed7b8016bfef3ec960000");
        map.add("appSecret", "Z9hUspQxjnEzwuF");
        return getBjghToken(openapiUrl, map);
    }

    // 平谷区
    public String getPingGu(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849546e21bbe9016e3ffaad4b0069");
        map.add("appSecret", "gl37bQK1gMfOxl0");
        return getBjghToken(openapiUrl, map);
    }

    // 人民日报
    public String getPeopleDaily(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849536e21bb29016e6e16840f007e");
        map.add("appSecret", "XfB91IKGj7t26hx");
        return getBjghToken(openapiUrl, map);
    }

    // 获取token，这里采用post 表单提交， 其他方式参看《产品理解》
    private String getBjghToken(String openapiUrl, MultiValueMap<String, Object> map) throws Exception {
        String url = openapiUrl + "auth";

        URI uri = URI.create(url);

        String result = restTemplate.postForObject(uri, map, String.class);
        logger.info(result);
        JSONObject jsonTeaObject= JSONObject.fromObject(result);
        return jsonTeaObject.getString("token");
    }

    public String getBjghToken(String openapiUrl, String appKey, String appSecret) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", appKey);
        map.add("appSecret", appSecret);
        return getBjghToken(openapiUrl, map);
    }
}
