package com.websocket.config;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.net.URI;

@Component
public class WebSocketJavaClient {

    public void webSocketClient(String id, Session session) {
       try {
           // 这里用的binance的socket接口，国内调用需要VPN，使用换成你的就行
           String url = "";
           if(id.equals("8083")) {
//               url = "ws://localhost:8083/ws/asset";
               url = "ws://114.115.223.240:8083/ws/asset";
           } else if (id.equals("8084")) {
//               url = "ws://localhost:8084/ws/asset";
               url = "ws://114.115.223.240:8083:8084/ws/asset";
           }
           URI uri = new URI(url);
           WebSocketClient mWs = new WebSocketClient(uri){

               @Override
               public void onOpen(ServerHandshake serverHandshake) {

               }

               @Override
               public void onMessage(String s) {
                   WebSocketServer webSocketServer = new WebSocketServer();
                   webSocketServer.onMessage(s, session);
               }

               @Override
               public void onClose(int i, String s, boolean b) {
                   WebSocketServer webSocketServer = new WebSocketServer();
                   webSocketServer.onClose(session);
               }

               @Override
               public void onError(Exception e) {
                   WebSocketServer webSocketServer = new WebSocketServer();
                   webSocketServer.onError(session, e);
               }
           };
           mWs.connect();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

}



