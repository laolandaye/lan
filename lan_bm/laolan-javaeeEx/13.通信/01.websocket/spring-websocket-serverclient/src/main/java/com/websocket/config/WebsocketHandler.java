package com.websocket.config;

import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


public class WebsocketHandler extends TextWebSocketHandler {

    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
    }
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static ConcurrentHashMap<String, CopyOnWriteArraySet<WebSocketSession>> SessionMapSet = new ConcurrentHashMap<>();

    public WebsocketHandler(){
    }


    /**
     * 连接成功时候，会触发页面上onopen方法
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String id = "111111111";
        try {
            SessionMapSet.get(id).add(session);
        } catch (NullPointerException npe) {
            CopyOnWriteArraySet<WebSocketSession> set = new CopyOnWriteArraySet<>();
            set.add(session);
            SessionMapSet.put(id, set);
        }
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        SendMessage(session, new TextMessage("连接成功"));

        WebSocketJavaClient testApp = new WebSocketJavaClient();
        testApp.webSocketClient(id, session);
    }

    /**
     * 关闭连接时触发
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        for (String s : SessionMapSet.keySet()) {
            CopyOnWriteArraySet<WebSocketSession> set = SessionMapSet.get(s);
            if(set.contains(session)) {
                SessionMapSet.get(s).remove(session);
                break;
            }
        }
        int cnt = OnlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("来自客户端的消息：{}",message);
        SendMessage(session, message);
    }

    /**
     * 出现错误
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable error) throws Exception {
        log.error("发生错误：{}，Session ID： {}",error.getMessage(),session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(WebSocketSession session, TextMessage message) {
        try {
//            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
            session.sendMessage(message);
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
