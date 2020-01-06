package cn.e3mall.config;

import cn.e3mall.pub.pubsub.RedisReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig {
    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param jedisConnectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(JedisConnectionFactory jedisConnectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);

        //可以添加多个 messageListener
        container.addMessageListener(listenerAdapter, new PatternTopic("topic"));
        container.addMessageListener(listenerAdapter2, new PatternTopic("topic2"));

        return container;
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     * @param redisReceiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisReceiver redisReceiver) {
        System.out.println("receiveMessage 消息适配器进来了");
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(RedisReceiver receiveMessage2) {
        System.out.println("receiveMessage2 消息适配器进来了");
        return new MessageListenerAdapter(receiveMessage2, "receiveMessage2");
    }

}
