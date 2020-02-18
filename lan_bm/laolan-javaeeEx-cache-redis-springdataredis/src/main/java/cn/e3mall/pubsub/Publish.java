package cn.e3mall.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class Publish implements MessageListener {

    private static Logger LOG = LoggerFactory.getLogger(Publish.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        JdkSerializationRedisSerializer redisSerializer = new JdkSerializationRedisSerializer();
        String className = (String) redisSerializer.deserialize(message.getBody());
        LOG.info(className);
    }
}
