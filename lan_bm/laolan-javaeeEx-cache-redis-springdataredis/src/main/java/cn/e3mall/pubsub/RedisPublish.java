package cn.e3mall.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * SUBSCRIBE
 * Subscribe
 */
@Service
public class RedisPublish {

    private static Logger LOG = LoggerFactory.getLogger(RedisPublish.class);

    public void receiveMessage(String message) {
        LOG.info(message);
    }

    public void receiveMessage2(String message) {
        LOG.info(message);
    }
}
