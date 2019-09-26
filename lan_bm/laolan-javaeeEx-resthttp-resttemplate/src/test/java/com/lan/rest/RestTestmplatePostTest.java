package com.lan.rest;

import com.lan.RestTemplateBoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestTemplateBoot.class)
public class RestTestmplatePostTest {

    private RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }

    /**
     *  public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException;
     *
     *  public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException;
     *
     *  public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException ;
     */
    @Test
    public void testPostForObject() {
        String url = "http://localhost:8080/post";
        String email = "test@hhui.top";
        String nick = "一灰灰Blog";

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("email", email);
        request.add("nick", nick);

        URI uri = URI.create(url);
        // 使用方法一
        String ans = restTemplate.postForObject(url, request, String.class);
        System.out.println(ans);

        // 使用方法一，但是结合表单参数和uri参数的方式，其中uri参数的填充和get请求一致
        request.clear();
        request.add("email", email);
        ans = restTemplate.postForObject(url + "?nick={?}", request, String.class, nick);
        System.out.println(ans);


        // 使用方法二
        Map<String, String> params = new HashMap<>();
        params.put("nick", nick);
        ans = restTemplate.postForObject(url + "?nick={nick}", request, String.class, params);
        System.out.println(ans);

        // 使用方法三
        ans = restTemplate.postForObject(uri, request, String.class);
        System.out.println(ans);

    }

    /**
     * public URI postForLocation(String url, @Nullable Object request, Object... uriVariables) throws RestClientException ;
     *  
     * public URI postForLocation(String url, @Nullable Object request, Map<String, ?> uriVariables) throws RestClientException ;
     *  
     * public URI postForLocation(URI url, @Nullable Object request) throws RestClientException ;
     */
    @Test
    public void testPostLocation() {
        String url = "http://localhost:8080/post2";
        String email = "test@hhui.top";
        String nick = "一灰灰Blog";

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("email", email);
        request.add("nick", nick);

        // 使用方法三
        URI uri = restTemplate.postForLocation(url, request);
        System.out.println(uri);

        MultiValueMap<String, String> request2 = new LinkedMultiValueMap<>();
        String result2 = restTemplate.postForObject(uri, request2, String.class);
        System.out.println(result2);
    }

}
