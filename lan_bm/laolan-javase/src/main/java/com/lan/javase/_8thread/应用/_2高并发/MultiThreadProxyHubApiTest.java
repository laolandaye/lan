package com.lan.javase._8thread.应用._2高并发;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.Header;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * java模拟多线程高并发
 * 原文地址：https://blog.csdn.net/u010642004/article/details/50042781
 * @author Administrator
 *
 */
public class MultiThreadProxyHubApiTest {
    static int count = 0;
    // 总访问量是clientNum，并发量是threadNum
    int threadNum = 4;
    int clientNum = 10;

    float avgExecTime = 0;
    float sumexecTime = 0;
    long firstExecTime = Long.MAX_VALUE;
    long lastDoneTime = Long.MIN_VALUE;
    float totalExecTime = 0;

    public static void main(String[] args) {
        new MultiThreadProxyHubApiTest().run();
        System.out.println("finished!");
    }

    public void run() {

        final ConcurrentHashMap<Integer, ThreadRecord> records = new ConcurrentHashMap<Integer, ThreadRecord>();

        // 建立ExecutorService线程池，threadNum个线程可以同时访问
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 模拟clientNum个客户端访问
        final CountDownLatch doneSignal = new CountDownLatch(clientNum);

        for (int i = 0; i < clientNum; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    int index = getIndex();
                    long systemCurrentTimeMillis = System.currentTimeMillis();
                    try {
                        String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
                        String url1 = openapiUrl + "auth?";

                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("appKey", "2c9849536c502347016c506774120003");
                        map.put("appSecret", "Am3iIOHFnPkItsY");
                        //插件式配置请求参数（网址、请求参数、编码、client）
                        HttpConfig config1 = HttpConfig.custom()
                                .map(map)			//设置请求参数，没有则无需设置
                                .timeout(1000) 		//超时
                                .url(url1)           //设置请求的url
                                .encoding("utf-8")  //设置请求和返回编码，默认就是Charset.defaultCharset()
                        ;
                        String result1 = HttpClientUtil.post(config1);
                        JSONObject jsonTeaObject= JSONObject.fromObject(result1);
                        String bjghToken = jsonTeaObject.getString("token");

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
                                .encoding("utf-8")  //设置请求和返回编码，默认就是Charset.defaultCharset()
                                ;

                        //使用方式：
                        String result2 = HttpClientUtil.post(config);   //post请求
                        JSONObject jsonTeaObject2= JSONObject.fromObject(result2);
                        try {
                            System.out.print(bjghToken + "           ");
                            System.out.println(jsonTeaObject2.getString("msg"));
                        } catch (JSONException e) {
                            System.out.println(jsonTeaObject2);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    records.put(index, new ThreadRecord(systemCurrentTimeMillis, System.currentTimeMillis()));
                    doneSignal.countDown();// 每调用一次countDown()方法，计数器减1
                }
            };
            exec.execute(run);
        }

        try {
            // 计数器大于0 时，await()方法会阻塞程序继续执行
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 获取每个线程的开始时间和结束时间
         */
        for (int i : records.keySet()) {
            ThreadRecord r = records.get(i);
            sumexecTime += ((double) (r.endTime - r.startTime)) / 1000;

            if (r.startTime < firstExecTime) {
                firstExecTime = r.startTime;
            }
            if (r.endTime > lastDoneTime) {
                this.lastDoneTime = r.endTime;
            }
        }

        this.avgExecTime = this.sumexecTime / records.size();
        this.totalExecTime = ((float) (this.lastDoneTime - this.firstExecTime)) / 1000;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(4);

        System.out.println("======================================================");
        System.out.println("线程数量:\t\t" + threadNum);
        System.out.println("客户端数量:\t" + clientNum);
        System.out.println("平均执行时间:\t" + nf.format(this.avgExecTime) + "秒");
        System.out.println("总执行时间:\t" + nf.format(this.totalExecTime) + "秒");
        System.out.println("吞吐量:\t\t" + nf.format(this.clientNum / this.totalExecTime) + "次每秒");
    }

    public static int getIndex() {
        return ++count;
    }

}

class ThreadRecord {
    long startTime;
    long endTime;

    public ThreadRecord(long st, long et) {
        this.startTime = st;
        this.endTime = et;
    }

}
