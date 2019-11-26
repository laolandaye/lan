package cn.e3mall.pubsub.list;

import cn.e3mall.redisUtil.RedisUseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Service
public class Producers {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private Jedis jedis;

    public void productCar() {
        int i = 0;
        while (true) {
            System.out.println("我是一个汽车生产者，正在生产汽车");
            jedis.rpush("car", "宝马" + (i++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
