package com.bjsxt.websocket.init;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class SocketConfig  implements ServerApplicationConfig {

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> ss) {
		System.out.println("endPoint扫描到的数量："+ss.size());
		return ss;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(
			Set<Class<? extends Endpoint>> ss) {
		System.out.println("实现EndPoint接口的类数量："+ss.size());
		return null;
	}

}
