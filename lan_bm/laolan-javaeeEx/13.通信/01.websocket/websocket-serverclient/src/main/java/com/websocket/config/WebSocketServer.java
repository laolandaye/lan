package com.websocket.config;

import com.websocket.utils.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/ws/asset/{id}")//此处需要放入之前自定义的configurator类
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static ConcurrentHashMap<String, CopyOnWriteArraySet<Session>> SessionMapSet = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        try {
            SessionMapSet.get(id).add(session);
        } catch (NullPointerException npe) {
            CopyOnWriteArraySet<Session> set = new CopyOnWriteArraySet<>();
            set.add(session);
            SessionMapSet.put(id, set);
        }
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        SendMessage(session, "连接成功");

        // 测试获得连接地址
//        InetSocketAddress remoteAddress = WebSocketUtil.getRemoteAddress(session);
////
////        try {
////            System.out.println("客户端IP: " + remoteAddress.getHostName());
////        } catch (Exception e) {
////            System.out.println("客户端IP出错 ");
////        }
        WebSocketJavaClient testApp = new WebSocketJavaClient();
        testApp.webSocketClient(id, session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        for (String s : SessionMapSet.keySet()) {
            CopyOnWriteArraySet<Session> set = SessionMapSet.get(s);
            if(set.contains(session)) {
                SessionMapSet.get(s).remove(session);
                break;
            }
        }
        int cnt = OnlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}",message);
        SendMessage(session, "收到消息，消息内容："+message);

    }

    /**
     * 出现错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}",error.getMessage(),session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
