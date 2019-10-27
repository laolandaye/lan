package com.lan.bjgh;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BjghToken {

    private static final Logger logger = LoggerFactory.getLogger(BjghToken.class);

    // 数据中台
    public static String getMonitoreSupervision(String openapiUrl) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appKey", "2c9849536c502347016c506774120003");
        map.put("appSecret", "Am3iIOHFnPkItsY");
        return getBjghToken(openapiUrl, map);
    }

    // 获取token，这里采用post 表单提交， 其他方式参看《产品理解》
    private static String getBjghToken(String openapiUrl, Map<String, Object> map) throws HttpProcessException {
        String url = openapiUrl + "auth";

        //插件式配置请求参数（网址、请求参数、编码、client）
        HttpConfig config = HttpConfig.custom()
//                .headers(headers)	//设置headers，不需要时则无需设置
                .timeout(1000) 		//超时
                .url(url)           //设置请求的url
                .map(map)			//设置请求参数，没有则无需设置
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
        logger.info("token结果：" + result2);
        JSONObject jsonTeaObject= JSONObject.fromObject(result2);
        return jsonTeaObject.getString("token");
    }
}
