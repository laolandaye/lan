package com.lan.httpclient.httpclientutil;

import com.lan.httpclient.httpclientutil.SimpleHttpClientDemo.SimpleHttpClientIgnoreVerifySSL;
import com.lan.httpclient.httpclientutil.SimpleHttpClientDemo.SimpleHttpClientPost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/** 
 *
 *
 * 参看代码：com.arronlong.httpclientutil.test.SimpleHttpClientDemo
 *
 *
 */
public class _1SimpleHttpClientDemo {

    private SimpleHttpClientPost simpleHttpClientPost = null;
    private SimpleHttpClientIgnoreVerifySSL simpleHttpClientIgnoreVerifySSL = null;

    @Before
    public void setUp() throws Exception {
        simpleHttpClientPost = new SimpleHttpClientPost();
        simpleHttpClientIgnoreVerifySSL = new SimpleHttpClientIgnoreVerifySSL();
    }

    @After
    public void tearDown() throws Exception {
    }

    //参看网址：轻松把玩HttpClient之模拟post请求示例
    // https://blog.csdn.net/xiaoxian8023/article/details/49863967
    @Test
    public void SimpleHttpClientPostTest() throws Exception {
        String url="http://php.weather.sina.com.cn/iframe/index/w_cl.php";
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "js");
        map.put("day", "0");
        map.put("city", "上海");
        map.put("dfc", "1");
        map.put("charset", "utf-8");
        String body = simpleHttpClientPost.send(url, map,"utf-8");
        System.out.println("交易响应结果：");
        System.out.println(body);

        System.out.println("-----------------------------------");

        map.put("city", "北京");
        body = simpleHttpClientPost.send(url, map, "utf-8");
        System.out.println("交易响应结果：");
        System.out.println(body);
    }

    //参看网址：轻松把玩HttpClient之配置ssl，采用绕过证书验证实现https
    // https://blog.csdn.net/xiaoxian8023/article/details/49865335
    @Test
    public void SimpleHttpClientIgnoreVerifySSLTest() throws Exception {
        String url = "https://blog.csdn.net/yxl_1207/article/details/80973622";
        String body = simpleHttpClientIgnoreVerifySSL.send(url, null, "utf-8");
        System.out.println("交易响应结果：");
        System.out.println(body);
        System.out.println("-----------------------------------");


    }
}
