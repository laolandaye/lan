package cn.e3mall.pubsub.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//定时器
@Component
public class TestController2 {

    @Autowired
    private Consumers consumers;
    @Autowired
    private Producers producers;



    @PostConstruct
    public void sendMessage2(){
        consumers.carConsumer();
    }
}
