package com.itheima;

import com.itheima.entity.User;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

public class Client {

    @Test
    public void testSave(){
        User user = new User();
        user.setId(100);
        user.setUsername("Jerry");
        user.setCity("gz");
        // 通过WebClient对象远程调用服务端
        WebClient
                    .create("http://localhost:8001/ws/userService/user")
                    .type(MediaType.APPLICATION_JSON)  // 指定请求的数据格式为json
                    .post(user);
    }

    @Test public void update() throws Exception {
        WebClient.create("http://localhost:8001/ws/userService/user") // 地址
         .type(MediaType.APPLICATION_JSON) // 请求数据格式是json
         .put(new User(100,"Kobe","gz",null)); // 发送请求的类型
    }

    @Test public void delete() throws Exception {

    }

    @Test
    public void testGet(){
        // 查询一个
        User user =
            WebClient
                    .create("http://localhost:8001/ws/userService/user/1")
                   .accept(MediaType.APPLICATION_JSON)
                    .get(User.class);
        System.out.println(user);
    }

    @Test public void findAll() throws Exception {
        Collection<? extends User> collection = WebClient
                .create("http://localhost:8001/ws/userService/user")
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(User.class);
        System.out.println(collection);
    }
}
