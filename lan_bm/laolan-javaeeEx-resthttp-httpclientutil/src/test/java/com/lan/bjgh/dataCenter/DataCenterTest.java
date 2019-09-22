package com.lan.bjgh.dataCenter;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.SSLs;
import com.lan.bjgh.BjghToken;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DataCenterTest {

    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_MS_GetBaseAccountListTest() throws Exception {
        // 1. 先获取token
        String bjghToken = BjghToken.getDataCenterToken(openapiUrl);

        //插件式配置Header（各种header信息、自定义header）
        Header[] headers 	= HttpHeader.custom()
                .contentType("application/json")
                .build();

        String url = openapiUrl + "sPMsGetBaseAccountList?token=" + bjghToken + "&appKey=2c9849536c502347016c506774120003";

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
                .headers(headers)	//设置headers，不需要时则无需设置
                .timeout(1000) 		//超时
                .url(url)           //设置请求的url
//                .map(map)			//设置请求参数，没有则无需设置
                .encoding("utf-8")  //设置请求和返回编码，默认就是Charset.defaultCharset()
//                .client(client)     //如果只是简单使用，无需设置，会自动获取默认的一个client对象
                //.inenc("utf-8")   //设置请求编码，如果请求返回一直，不需要再单独设置
                //.inenc("utf-8")   //设置返回编码，如果请求返回一直，不需要再单独设置
                //.json("json字符串") //json方式请求的话，就不用设置map方法，当然二者可以共用。
                //.context(HttpCookies.custom().getContext())      //设置cookie，用于完成携带cookie的操作
                //.out(new FileOutputStream("保存地址"))              //下载的话，设置这个方法,否则不要设置
                //.files(new String[]{"d:/1.txt","d:/2.txt"})      //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                ;


        //使用方式：
        String result2 = HttpClientUtil.post(config);   //post请求
        System.out.println(result2);
    }

    @Test
    public void SP_MS_GetSpiderResultListTest() throws Exception {
        // 1. 先获取token
        String bjghToken = BjghToken.getDataCenterToken(openapiUrl);

        //插件式配置Header（各种header信息、自定义header）
        Header[] headers 	= HttpHeader.custom()
                .contentType("application/json")
                .build();

        String url = openapiUrl + "sPMsGetSpiderResultList?token=" + bjghToken + "&appKey=2c9849536c502347016c506774120003";

        String jsonStr = "{" +
                "\"accountCode\":\"22_RMTWZ_BJDCOMCN_00_110000\"," +
                "\"startTime\":\"2018-11-10 20:00:10\"," +
                "\"endTime\":\"2019-11-10 20:00:10\"," +
                "\"pageSize\":12, " +
                "\"pageIndex\":1" +
                "}";

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
                .headers(headers)	//设置headers，不需要时则无需设置
                .timeout(1000) 		//超时
                .url(url)           //设置请求的url
//                .map(map)			//设置请求参数，没有则无需设置
                .encoding("utf-8")  //设置请求和返回编码，默认就是Charset.defaultCharset()
//                .client(client)     //如果只是简单使用，无需设置，会自动获取默认的一个client对象
                //.inenc("utf-8")   //设置请求编码，如果请求返回一直，不需要再单独设置
                //.inenc("utf-8")   //设置返回编码，如果请求返回一直，不需要再单独设置
                .json(jsonStr) //json方式请求的话，就不用设置map方法，当然二者可以共用。
                //.context(HttpCookies.custom().getContext())      //设置cookie，用于完成携带cookie的操作
                //.out(new FileOutputStream("保存地址"))              //下载的话，设置这个方法,否则不要设置
                //.files(new String[]{"d:/1.txt","d:/2.txt"})      //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                ;


        //使用方式：
        String result2 = HttpClientUtil.post(config);   //post请求
        System.out.println(result2);
    }

    @Test
    public void SP_MS_GetSensitiveProgramListTest() throws Exception {
        // 1. 先获取token
        String bjghToken = BjghToken.getDataCenterToken(openapiUrl);

        //插件式配置Header（各种header信息、自定义header）
        Header[] headers 	= HttpHeader.custom()
                .contentType("application/json")
                .build();

        String url = openapiUrl + "sPMsGetSensitiveProgramList/v1?token=" + bjghToken + "&appKey=2c9849536c502347016c506774120003";

        String jsonStr = "{" +
                "\"accountCode\":\"1_WB_BJRBDKHD_00_110000\"," +
                "\"startTime\":\"2018-11-10 20:00:10\"," +
                "\"endTime\":\"2019-11-10 20:00:10\"," +
                "\"pageSize\":12, " +
                "\"pageIndex\":1" +
                "}";

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
                .headers(headers)	//设置headers，不需要时则无需设置
                .timeout(1000) 		//超时
                .url(url)           //设置请求的url
//                .map(map)			//设置请求参数，没有则无需设置
                .encoding("utf-8")  //设置请求和返回编码，默认就是Charset.defaultCharset()
//                .client(client)     //如果只是简单使用，无需设置，会自动获取默认的一个client对象
                //.inenc("utf-8")   //设置请求编码，如果请求返回一直，不需要再单独设置
                //.inenc("utf-8")   //设置返回编码，如果请求返回一直，不需要再单独设置
                .json(jsonStr) //json方式请求的话，就不用设置map方法，当然二者可以共用。
                //.context(HttpCookies.custom().getContext())      //设置cookie，用于完成携带cookie的操作
                //.out(new FileOutputStream("保存地址"))              //下载的话，设置这个方法,否则不要设置
                //.files(new String[]{"d:/1.txt","d:/2.txt"})      //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                ;


        //使用方式：
        String result2 = HttpClientUtil.post(config);   //post请求
        System.out.println(result2);
    }
}
