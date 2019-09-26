package com.lan.bjgh;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class BjghToken {

    private static final Logger logger = LoggerFactory.getLogger(BjghToken.class);

    @Autowired
    private RestTemplate restTemplate;

    // 数据中台
    public String getDataCenterToken(String openapiUrl) throws Exception {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("appKey", "2c9849536c502347016c506774120003");
        map.add("appSecret", "Am3iIOHFnPkItsY");
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
