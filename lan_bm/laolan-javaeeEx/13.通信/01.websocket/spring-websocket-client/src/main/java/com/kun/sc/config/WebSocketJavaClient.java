package com.kun.sc.config;

import com.kun.sc.dto.ScResp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Component
@Data
public class WebSocketJavaClient {

    @Value("${jd.sc.server.url}")
    private String serverUrl;

    @Autowired
    private RestTemplate restTemplate;

    private WebSocketClient mWs = null;

    public WebSocketClient clientConnect() {
       try {
           URI uri = new URI("ws://" + serverUrl + "/ws/asset");
           log.info("连接服务器端：{}", serverUrl);
           mWs = new WebSocketClient(uri){

               @Override
               public void onOpen(ServerHandshake serverHandshake) {
               }

               @Override
               public void onMessage(String s) {
                   log.info("数采客户端->RPA：" + s);
               }

               @Override
               public void onClose(int i, String s, boolean b) {
                   log.error("客户端关闭：{}", s);
               }

               @Override
               public void onError(Exception error) {
                   log.error("客户端错误：{}", error.getMessage());
               }
           };
           mWs.connect();
           return mWs;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }

    /**
     *
     * @param msg
     */
    public void sendMessage(String msg, ScResp resp) {
        log.info("远程调用传递方式：ws");
        mWs.send(msg);
    }

}



