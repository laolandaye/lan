package cn.e3mall.config;

import cn.e3mall.pubsub.Publish;
import cn.e3mall.pubsub.RedisPublish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 使用 RedisMessageListenerContainer
 */
@Configuration
public class RedisMessageListenerConfig {
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
                                            MessageListenerAdapter listenerAdapter2,
                                            MessageListenerAdapter listenerAdapter3
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);

        // 主题 topic
        PatternTopic topic = new PatternTopic("topic");
        PatternTopic topic2 = new PatternTopic("topic2");

        //可以添加多个 messageListener
        // 方式二
        container.addMessageListener(listenerAdapter, topic);
        container.addMessageListener(listenerAdapter2, topic2);
        // 方式一
        container.addMessageListener(listenerAdapter3, topic);

        return container;
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisPublish redisReceiver) {
        System.out.println("receiveMessage 消息适配器进来了");
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(RedisPublish receiveMessage2) {
        System.out.println("receiveMessage2 消息适配器进来了");
        return new MessageListenerAdapter(receiveMessage2, "receiveMessage2");
    }

    @Bean
    MessageListenerAdapter listenerAdapter3() {
        System.out.println("receiveMessage3 消息适配器进来了");
        return new MessageListenerAdapter(new Publish());
    }
}
