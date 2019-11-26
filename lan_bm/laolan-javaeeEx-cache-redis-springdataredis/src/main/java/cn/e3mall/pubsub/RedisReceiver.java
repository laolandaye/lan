package cn.e3mall.pubsub;

import org.springframework.stereotype.Service;

@Service
public class RedisReceiver {

    public void receiveMessage(String message) {
        System.out.println("inxex1："+message);
        //这里是收到通道的消息之后执行的方法
    }

    public void receiveMessage2(String message) {
        System.out.println("index2："+message);
        //这里是收到通道的消息之后执行的方法
    }
}
