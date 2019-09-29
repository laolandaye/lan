package cn.e3mall.config;

import cn.e3mall.redisUtil.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单机
 * 集群： 方式三
 */
@PropertySource(name = "resourcePropertySource", value = { "classpath:redis.properties"})
@EnableCaching
@Configuration
public class RedisSingleClusterConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisSingleClusterConfig.class);

    @Autowired
    private Environment environment;

    @Value("${spring.redis.hostName}")
    private String hostName;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Value("${spring.redis.maxIdle}")
    private Integer maxIdle;

    @Value("${spring.redis.minIdle}")
    private Integer minIdle;

    @Value("${spring.redis.maxActive}")
    private String maxActive;

    @Value("${spring.redis.maxTotal}")
    private Integer maxTotal;

    @Value("${spring.redis.maxWaitMillis}")
    private Long maxWaitMillis;

    @Value("${spring.redis.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;

    @Value("${spring.redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${spring.redis.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.redis.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.redis.database}")
    private Integer database;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        return jedisPoolConfig;
    }

    // 方式
    // 参看： https://www.e-learn.cn/content/java/914867
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        Map<String, Object> source = new HashMap<>();
        Object clusterNodes = environment.getProperty("spring.redis.cluster.nodes");
        if(null != clusterNodes) {
            source.put("spring.redis.cluster.nodes", clusterNodes);
        }
        Object maxRedirects = environment.getProperty("spring.redis.cluster.max-redirects");
        if(null != maxRedirects) {
            source.put("spring.redis.cluster.max-redirects", maxRedirects);
        }
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        redisClusterConfiguration.setMaxRedirects(3);
        return redisClusterConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisClusterConfiguration redisClusterConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = null;
        // 无需判断null，坑定存在
        if(redisClusterConfiguration.getClusterNodes().size() > 0) {
            logger.info("****************** 当前启动redis集群模式 ******************");
            for (RedisNode o : redisClusterConfiguration.getClusterNodes()) {
                logger.info("****************** 节点：" + o);
            }
            jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        } else {
            logger.info("****************** 当前启动redis单机模式 *******************");
            logger.info("****************** ip: " + hostName);
            logger.info("****************** 端口: " + port);
            jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setHostName(hostName);
            jedisConnectionFactory.setPort(port);
        }
        logger.info("****************** 密码：" + password);
        logger.info("****************** 请仔细检查ip,端口,密码 ******************");
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        stringRedisTemplate.setEnableTransactionSupport(true);
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return stringRedisTemplate;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /*********************** @EnableCaching 缓存管理器 一般不用 *************************************** */
    @Primary  //将某个缓存管理器作为默认的
    @Bean
    public RedisCacheManager stringRedisCacheManager(StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(stringRedisTemplate);
        redisCacheManager.setDefaultExpiration(3000);
        redisCacheManager.setTransactionAware(true);
        return redisCacheManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(3000);
        redisCacheManager.setTransactionAware(true);
        return redisCacheManager;
    }
}
