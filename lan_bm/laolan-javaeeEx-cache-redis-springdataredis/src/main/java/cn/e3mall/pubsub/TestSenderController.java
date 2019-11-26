package cn.e3mall.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//定时器
@EnableScheduling
@Component
public class TestSenderController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //向redis消息队列index通道发布消息
//    @Scheduled(fixedRate = 3000)
    public void sendMessage(){
        stringRedisTemplate.convertAndSend("index", "receiveMessage..............");
    }

//    @Scheduled(fixedRate = 5000)
    public void sendMessage2(){
        stringRedisTemplate.convertAndSend("index2", "receiveMessage2222222222222222");
    }
}
