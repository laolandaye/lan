package com.lan.demo;

import com.lan.RestTemplateBoot;
import com.lan.bjgh.BjghToken;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
public class GetDemoTest {

    private static final Logger logger = LoggerFactory.getLogger(BjghToken.class);

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void init() {
    }

    @Test
    public void testWithCookie(){
        Mono<String> resp = WebClient.create()
                .method(HttpMethod.GET)
                .uri("http://baidu.com")
                .cookie("token","xxxx")
                .cookie("JSESSIONID","XXXX")
                .retrieve()
                .bodyToMono(String.class);
        logger.info("result:{}",resp.block());
    }

    // 1.1.1.简单Get 请求
    // curl -X GET 'https://story.hhui.top/'
    @Test
    public void testGetSimple() {
        // 使用方法一，不带参数
        String url = "https://story.hhui.top";
        String res = restTemplate.getForObject(url, String.class);
        System.out.println(res);

        // 使用方法三，URI访问
        URI uri = URI.create("https://story.hhui.top/");
        res = restTemplate.getForObject(uri, String.class);
        System.out.println(res);

        // 使用方法一，不带参数
        String url2 = "https://story.hhui.top";
        ResponseEntity<String> res2 = restTemplate.getForEntity(url2, String.class);
        System.out.println(res2);

        // 使用方法三，URI访问
        URI uri2 = URI.create("https://story.hhui.top");
        res = restTemplate.getForObject(uri2, String.class);
        System.out.println(res);

    }

    // 1.1.2.Get带url参数
    // cur-X GET 'https://story.hhui.top/detail?id=666106231640'
    @Test
    public void testGetSimpleUrl() {
        // 使用方法一，传参替换
        String url = "https://story.hhui.top/detail?id={?}";
        InnerRes res = restTemplate.getForObject(url, InnerRes.class, "666106231640");
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

        /**************************** getForEntity ***********************************************/

        // 使用方法一，传参替换
        String url2 = "https://story.hhui.top/detail?id={?}";
        ResponseEntity<InnerRes>  res2 = restTemplate.getForEntity(url2, InnerRes.class, "666106231640");
        System.out.println(res2);

        // 使用方法二，map传参
        url2 = "https://story.hhui.top/detail?id={id}";
        Map<String, Object> params2 = new HashMap<>();
        params2.put("id", 666106231640L);
        res2 = restTemplate.getForEntity(url2, InnerRes.class, params2);
        System.out.println(res2);

        // 使用方法三，URI访问
        URI uri2 = URI.create("https://story.hhui.top/detail?id=666106231640");
        res2 = restTemplate.getForEntity(uri2, InnerRes.class);
        System.out.println(res2);
    }

    // 1.2.Get带header参数
    //1.2.1.Content-Type
    // curl -H "Content-Type:application/json" -X GET 'https://story.hhui.top/detail?id=666106231640'
    @Test
    public void testGetHeaderByContentType() {
        // 使用方法一，传参替换

        // 使用方法二，map传参

        // 使用方法三，URI访问
        URI uri = URI.create("https://story.hhui.top/detail?id=666106231640");
        InnerRes res = restTemplate.getForObject(uri, InnerRes.class);
        System.out.println(res);

        /**************************** getForEntity ***********************************************/

        // 使用方法一，传参替换

        // 使用方法二，map传参

        // 使用方法三，URI访问
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

}
