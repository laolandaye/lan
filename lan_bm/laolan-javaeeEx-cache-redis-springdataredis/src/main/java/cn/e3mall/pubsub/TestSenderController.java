package cn.e3mall.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pubsub")
public class TestSenderController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(){
        String message = "receiveMessage & topic";
        stringRedisTemplate.convertAndSend("topic", message);
        return message;
    }

    @RequestMapping("/sendMessage2")
    @ResponseBody
    public String sendMessage2(){
        String message = "receiveMessage2 & topic2";
        stringRedisTemplate.convertAndSend("topic2", message);
        return message;
    }

    @RequestMapping("/sendMessage3")
    @ResponseBody
    public String sendMessage3(){
        String message = "receiveMessage3 & topic";
        stringRedisTemplate.convertAndSend("topic", message);
        return message;
    }
}
