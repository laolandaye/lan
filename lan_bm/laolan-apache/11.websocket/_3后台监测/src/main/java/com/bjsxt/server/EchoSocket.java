package com.bjsxt.server;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// 客户端连接时的url： ws://localhost:8080/echo/echoSocket

@ServerEndpoint("/echoSocket")
public class EchoSocket {

	public EchoSocket() {
		System.out.println("EchoSocket.EchoSocket()实列化");
	}

	// @Onpen 表示 通道建立时，执行的响应方法。
	@OnOpen
	public  void open(Session  session ){
		// session 代表一次 socket连接。  可以接收数据， 也可以返回数据。
		System.out.println("连接建立成功！！！"+session.getId());
	}

	@OnMessage
	public  void receive(Session session, String msg){
		System.out.println(msg);

		try {
			session.getBasicRemote().sendText("你也好。。。");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@OnClose
	public void  close(Session  session){
		System.out.println(session.getId()+"session 关闭啦");
	}



}
