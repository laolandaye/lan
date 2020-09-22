package com.kun.sc.config;

import com.kun.sc.dto.ScReq;
import com.kun.sc.dto.ScResp;
import com.kun.sc.enums.ScCodeEnum;
import com.kun.sc.utils.JsonJacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledJob {

    @Autowired
    private WebSocketJavaClient webSocketJavaClient;

    @Scheduled(fixedRate = 1000 * 50, initialDelay = 50 * 1000)
    public void webSocketHeart(){
        ScResp resp = new ScResp();
        resp.setRetCode(ScCodeEnum.TEST.getState());
        resp.setRetStr(ScCodeEnum.TEST.getName());
        try {
            webSocketJavaClient.sendMessage(JsonJacksonUtil.objectToJson(resp), resp);
        } catch (WebsocketNotConnectedException wnce) {
            log.info("定时器测试连接服务器端信息失败，断线重连: {}", wnce.getMessage());
            webSocketJavaClient.clientConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
