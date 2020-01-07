package com.lan.bjgh;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lan.RestTemplateBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
//@SpringBootTest
// 加入启动类，方便spring 注入
@SpringBootTest(classes = RestTemplateBoot.class)
public class CentralKichenTest {

//    public String openapiUrl = "http://47.95.182.97:8081/openapi/service/";
    private String openapiUrl = "https://datagov.beijingcloud.com.cn:18081/openapi/service/";
    private String appKey = "2c9849536c502347016c506720ee0001";
    private String appSecret = "6AOrH0nIqOsz4cN";

    @Autowired
    private CentralKitchenService centralKitchenService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void SP_CK_ScriptTotalCountTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_ScriptTotalCount();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_TodayUploadScriptCountTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_SingleDayUploadScriptCount();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_SingleDayUploadScriptCountTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_SingleDayUploadScriptCount();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_ClueTotalCountTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_ClueTotalCount();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_TodayUploadClueCountTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_TodayUploadClueCount();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_ScriptTotalCountByStatusTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_ScriptTotalCountByStatus();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_TodayScriptTotalCountByStatusTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_TodayScriptTotalCountByStatus();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_NewMediaChannelPublishStatisticsTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_NewMediaChannelPublishStatistics();
        System.out.println(result.getBody());
    }

    @Test
    public void SP_CK_AddPushTest() throws Exception {
        ResponseEntity<String> result = centralKitchenService.SP_CK_AddPush(openapiUrl, appKey, appSecret);
        System.out.println(result.getBody());
    }
    @Test
    public void SP_CK_AddPushThreadTest() throws Exception {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Mdemo-pool-%d").build();
        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5,200,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1024),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        // 缓存线程池，无上限
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    ResponseEntity<String> result = null;
                    try {
                        CentralKitchenService centralKitchenService = new CentralKitchenService();
                        result = centralKitchenService.SP_CK_AddPush(openapiUrl, appKey, appSecret);
                        System.out.println(result.getBody());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
//        cachedThreadPool.shutdown();
    }


}
