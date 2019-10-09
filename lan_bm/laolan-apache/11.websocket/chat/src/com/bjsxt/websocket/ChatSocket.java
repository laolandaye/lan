package com.bjsxt.websocket;

import com.bjsxt.vo.Message;
import com.google.gson.Gson;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;


@ServerEndpoint("/chatSocket")
public class ChatSocket {


	private  static  Set<ChatSocket>  sockets=new HashSet<ChatSocket>();
//	private  static  Map<String, ChatSocket>  sMap=new HashMap<String, ChatSocket>();

	private  static  List<String>   names=new ArrayList<String>();
	private  Session  session;
	private String username;
	private Gson  gson=new Gson();

	@OnOpen
	public  void open(Session  session){
		this.session=session;
		sockets.add(this);

		String queryString2 = session.getQueryString();
		String  queryString =  "";
		try {
			//解决中文乱码
			queryString =  URLDecoder.decode(queryString2, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(queryString);
		this.username = queryString.substring(queryString.indexOf("=")+1);
		names.add(this.username);


		Message   message=new Message();
		message.setAlert(this.username+"进入聊天室！！");
		message.setNames(names);

		broadcast(sockets, gson.toJson(message) );

	}
	@OnMessage
	public  void message(Session  session, String msg){

		Message  message=new Message();
		message.setSendMsg(msg);
		message.setFrom(this.username);
		message.setDate(new Date().toLocaleString());

		broadcast(sockets, gson.toJson(message));
	}

	@OnClose
	public  void close(Session session){
		sockets.remove(this);
		names.remove(this.username);

		Message   message=new Message();
		message.setAlert(this.username+"退出聊天室！！");
		message.setNames(names);

		broadcast(sockets, gson.toJson(message));
	}

	public void broadcast(Set<ChatSocket>  ss, String msg){

		for (Iterator iterator = ss.iterator(); iterator.hasNext();) {
			ChatSocket chatSocket = (ChatSocket) iterator.next();
			try {
				chatSocket.session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
