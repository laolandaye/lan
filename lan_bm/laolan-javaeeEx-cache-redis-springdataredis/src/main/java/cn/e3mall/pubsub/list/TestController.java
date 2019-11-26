package cn.e3mall.pubsub.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//定时器
@Component
public class TestController {

    @Autowired
    private Consumers consumers;
    @Autowired
    private Producers producers;


    //向redis消息队列index通道发布消息
    @PostConstruct
    public void sendMessage(){
        producers.productCar();
    }

}
