package cn.e3mall.pubsub.list;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Service
public class Consumers {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private Jedis jedis;

    public void carConsumer() {
        while (true) {
            System.out.println("我是一个汽车消费者，正在消费汽车.....................");
            //阻塞式brpop，List中无数据时阻塞，参数0表示一直阻塞下去，直到List出现数据
            List<String> listingList = jedis.blpop(10, "car");
            System.out.println("正在消费汽车：{}" + listingList.get(1));
        }

    }


}
