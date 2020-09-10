package com.websocket.config;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.net.URI;

@Component
public class WebSocketJavaClient {

    public void webSocketClient(String id, WebSocketSession session) {
       try {
           // 这里用的binance的socket接口，国内调用需要VPN，使用换成你的就行
           String url = "ws://114.115.223.240:8083/ws/asset";
           if(id.equals("8083")) {
               url = "ws://localhost:8083/ws/asset";
//               url = "ws://114.115.223.240:8083/ws/asset";
           } else if (id.equals("8084")) {
               url = "ws://localhost:8084/ws/asset";
//               url = "ws://114.115.223.240:8083:8084/ws/asset";
           }
           URI uri = new URI(url);
           WebSocketClient mWs = new WebSocketClient(uri){

               @Override
               public void onOpen(ServerHandshake serverHandshake) {

               }

               @Override
               public void onMessage(String s) {
                   WebsocketHandler webSocketServer = new WebsocketHandler();
                   try {
                       webSocketServer.handleTextMessage(session, new TextMessage(s));
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }

               @Override
               public void onClose(int i, String s, boolean b) {
                   WebsocketHandler webSocketServer = new WebsocketHandler();
                   try {
                       webSocketServer.afterConnectionClosed(session, CloseStatus.NO_STATUS_CODE);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }

               @Override
               public void onError(Exception error) {
                   WebsocketHandler webSocketServer = new WebsocketHandler();
                   try {
                       webSocketServer.handleTransportError(session, error);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           };
           mWs.connect();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

}



