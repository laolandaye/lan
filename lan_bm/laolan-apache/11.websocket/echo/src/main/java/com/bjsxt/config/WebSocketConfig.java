package com.bjsxt.config;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

public class WebSocketConfig  implements ServerApplicationConfig {

    /*
     * 上面的两个方法都是用来注册 webSocket的。   只不过注册的方式不同。
     * 1.  getAnnotatedEndpointClasses  1方法是 注解的方式
     * 2.  getEndpointConfigs  2方法是 接口的方式
     * 显然 注解的方式更加的 灵活简单。  接口的方式更加的传统，严谨。
     */

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return null;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> arg0) {
        System.out.println("正在扫描所有的webSocket服务！！！" + arg0.size());
        return arg0;
    }
}

