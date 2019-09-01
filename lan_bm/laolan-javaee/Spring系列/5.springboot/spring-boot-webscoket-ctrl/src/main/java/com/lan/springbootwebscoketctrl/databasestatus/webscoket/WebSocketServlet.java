package com.lan.springbootwebscoketctrl.databasestatus.webscoket;

import com.lan.springbootwebscoketctrl.databasestatus.timer.MyThread;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/getCount")
@Component
public class WebSocketServlet {

    MyThread thread1 = new MyThread();

    Thread thread = new Thread(thread1);

    private static Logger logger = LogManager.getLogger(WebSocketServlet.class.getName());

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServlet> wsClientMap = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session 当前会话session
     */
    @OnOpen
    public void onOpen (Session session){
        this.session = session;
        wsClientMap.add(this);
        addOnlineCount();
        logger.info(session.getId()+"有新链接加入，当前链接数为：" + wsClientMap.size());

        /* 开始自己操作 */
        thread.start();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose (){
        wsClientMap.remove(this);
        subOnlineCount();
        logger.info("有一链接关闭，当前链接数为：" + wsClientMap.size());
    }

    /**
     * 收到客户端消息
     * @param message 客户端发送过来的消息
     * @param session 当前会话session
     * @throws IOException
     */
    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        logger.info("来终端的警情消息:" + message);
        sendMsgToAll(message);
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("wsClientMap发生错误!");
        error.printStackTrace();
    }

    /**
     * 给所有客户端群发消息
     * @param message 消息内容
     * @throws IOException
     */
    public void sendMsgToAll(String message) throws IOException {
        for ( WebSocketServlet item : wsClientMap ){
            item.session.getBasicRemote().sendText(message);
        }
        logger.info("成功群送消息:" + wsClientMap.size());
    }

    public void sendMessage (String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        logger.info("成功发送一条消息:" + message);
    }

    public static synchronized  int getOnlineCount (){
        return WebSocketServlet.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        WebSocketServlet.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        WebSocketServlet.onlineCount--;
    }
}
