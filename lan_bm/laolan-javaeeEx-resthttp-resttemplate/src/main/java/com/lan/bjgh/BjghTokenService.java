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

@Service
public class BjghTokenService {

    private static final Logger logger = LoggerFactory.getLogger(BjghTokenService.class);

    @Autowired
    private RestTemplate restTemplate;

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

    // 获取token，这里采用post 表单提交， 其他方式参看《产品理解》
    private String getBjghToken(String openapiUrl, MultiValueMap<String, Object> map) throws Exception {
        String url = openapiUrl + "auth";

        URI uri = URI.create(url);

        String result = restTemplate.postForObject(uri, map, String.class);
        logger.info(result);
        JSONObject jsonTeaObject= JSONObject.fromObject(result);
        return jsonTeaObject.getString("token");
    }
}
