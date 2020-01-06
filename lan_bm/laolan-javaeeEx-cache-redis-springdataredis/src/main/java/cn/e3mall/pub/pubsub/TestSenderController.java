package cn.e3mall.pub.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

//定时器
@Controller
@RequestMapping("/pubsub")
public class TestSenderController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //向redis消息队列index通道发布消息
    @RequestMapping("/index")
    @ResponseBody
    public String sendMessage(){
        for (int i = 0; i < 5; i++) {
            stringRedisTemplate.convertAndSend("topic", "receiveMessage.............." + i);
        }
        return "see console; RedisReceiver receiveMessage";
    }

    @RequestMapping("/index2")
    @ResponseBody
    public String sendMessage2(){
        for (int i = 0; i < 5; i++) {
            stringRedisTemplate.convertAndSend("topic2", "receiveMessage2.............." + i);
        }
        return "see console; RedisReceiver receiveMessage2";
    }
}
