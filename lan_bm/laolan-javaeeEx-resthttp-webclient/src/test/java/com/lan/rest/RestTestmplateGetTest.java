package com.lan.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
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
@SpringBootTest
public class RestTestmplateGetTest {

    private RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }

    @lombok.Data
    static class InnerRes {
        private Status status;
        private Data result;
    }

    @lombok.Data
    static class Status {
        int code;
        String msg;
    }

    @lombok.Data
    static class Data {
        long id;
        String theme;
        String title;
        String dynasty;
        String explain;
        String content;
        String author;
    }

    /**
     * @Nullable
     * public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException ;
     *  
     * @Nullable
     * public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException ;
     *  
     * @Nullable
     * public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException;
     */
    @Test
    public void testGetForObject() {
        // 使用方法一，不带参数
        String url = "https://story.hhui.top/detail?id=666106231640";
        InnerRes res = restTemplate.getForObject(url, InnerRes.class);
        System.out.println(res);

        // 使用方法一，传参替换
        url = "https://story.hhui.top/detail?id={?}";
        res = restTemplate.getForObject(url, InnerRes.class, "666106231640");
        System.out.println(res);

        // 使用方法二，map传参
        url = "https://story.hhui.top/detail?id={id}";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 666106231640L);
        res = restTemplate.getForObject(url, InnerRes.class, params);
        System.out.println(res);

        // 使用方法三，URI访问
        URI uri = URI.create("https://story.hhui.top/detail?id=666106231640");
        res = restTemplate.getForObject(uri, InnerRes.class);
        System.out.println(res);
    }

    @Test
    public void testGetForEntity() {
        // 使用方法一，不带参数
        String url = "https://story.hhui.top/detail?id=666106231640";
        ResponseEntity<InnerRes> res = restTemplate.getForEntity(url, InnerRes.class);
        System.out.println(res);

        // 使用方法一，传参替换
        url = "https://story.hhui.top/detail?id={?}";
        res = restTemplate.getForEntity(url, InnerRes.class, "666106231640");
        System.out.println(res);

        // 使用方法二，map传参
        url = "https://story.hhui.top/detail?id={id}";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 666106231640L);
        res = restTemplate.getForEntity(url, InnerRes.class, params);
        System.out.println(res);

        // 使用方法三，URI访问
        URI uri = URI.create("https://story.hhui.top/detail?id=666106231640");
        res = restTemplate.getForEntity(uri, InnerRes.class);
        System.out.println(res);
    }

}
