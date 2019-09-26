package com.lan.demo;

import com.lan.RestTemplateBoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 *  getForObject
 *      public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException ;
 *      public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException ;
 *      public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException;
 *  getForEntity
 *     public <T> T getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException ;
 *     public <T> T getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException ;
 *     public <T> T getForEntity(URI url, Class<T> responseType) throws RestClientException;
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplateBoot.class)
public class ExchangeDemoTest {

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void init() {
    }

    // 3.Exchange
    // 3.1.1.Get带header参数
    // curl -H "Content-Type:application/json" -X GET 'https://story.hhui.top/detail?id=666106231640'
    @Test
    public void testGetHeaderByContentType() {
        // 使用方法一，不带参数
        String url = "https://story.hhui.top/detail?id=666106231640";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(headers);

        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        System.out.println(res);

    }


}
