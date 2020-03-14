package com.lan.bjsc;

import com.lan.bjgh.BjghTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URI;

@Service
public class BjfcService {

    @Autowired
    private BjghTokenService bjghTokenService;

    @Autowired
    private RestTemplate restTemplate;

    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行(并指定线程池的名字)
//    @Async("taskExecutor")
    public void SP_DD_FeedBack(String openapiUrl, String appKey, String appSecret, int i) throws Exception {
        String token = bjghTokenService.getBjghToken(openapiUrl, appKey, appSecret);
        String url = openapiUrl + "testFilter/v1?token=" + token + "&appKey=" + appKey;
        restTemplate.getForObject(url, String.class);
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }
}
