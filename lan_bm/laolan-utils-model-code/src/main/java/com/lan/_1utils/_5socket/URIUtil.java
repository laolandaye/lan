package com.lan._1utils._5socket;

import java.io.IOException;
import java.net.*;

public class URIUtil {

	public static void main(String[] args) {
		String testOpenApiAuth = URIUtil.serverPortTest("http://honeycombtest.peopleyun.cn/dam/service/auth?", 30 * 1000); // 测试地址是否通
		System.out.println(testOpenApiAuth);
	}

	/**
	 * 测试服务器端口是否开通
	 * @param ip
	 * @param port
	 * @param timeout
	 * @return success 表示成功
	 */
	public static String serverPortTest(String ip,int port,int timeout){
		if(port==-1) port=80;
		String result="success";
		Socket client = new Socket();
		try{
			SocketAddress socketAddress = new InetSocketAddress(ip, port);
			if(timeout<0){
				client.connect(socketAddress);
			}else{
				client.connect(socketAddress,timeout);
			}
		    client.close();
		}catch(Exception e){
			result=e.getMessage();
		}finally{
			if(client!=null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	/**
	 * 测试服务器端口是否开通
	 * @param uri
	 * @param timeout
	 * @return
	 */
	public static String serverPortTest(URI uri,int timeout){
		int port = uri.getPort();
		if(port==-1) {
			// 根据协议来判断默认端口
			switch (uri.getScheme()) {
				case "https":
					port = 443;
					break;
				case "http":
					port = 80;
					break;
				default:
					break;
			}
		}
		return serverPortTest(uri.getHost(), port, timeout);
	}
	/**
	 * 测试服务器端口是否开通
	 * @param ip
	 * @param timeout
	 * @return
	 */
	public static String serverPortTest(String url,int timeout){
		URI uri=null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return serverPortTest(uri,timeout);
	}
}
