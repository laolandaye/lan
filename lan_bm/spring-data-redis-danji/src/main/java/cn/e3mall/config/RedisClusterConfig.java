package cn.e3mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

//@PropertySource(name = "resourcePropertySource", value = {"redis.properties", "classpath:redis.properties"}, factory = ResourcePropertySource.class)
@EnableCaching
@Configuration
public class RedisClusterConfig {

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

    /*@Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setMaxRedirects(3);
        Set<RedisNode> nodes = new HashSet<>();
        nodes.add(new RedisClusterNode("192.168.25.101", 6380));
        nodes.add(new RedisClusterNode("192.168.25.101", 6381));
        nodes.add(new RedisClusterNode("192.168.25.101", 6382));
        redisClusterConfiguration.setClusterNodes(nodes);
        return redisClusterConfiguration;
    }
*/
    @Bean
    public ResourcePropertySource resourcePropertySource() {
        Resource resource = PropertiesLoaderUtils.loadProperties(new EncodedResource("classpath:redis.properties"));
        ResourcePropertySource resourcePropertySource = new ResourcePropertySource("redis.properties", );
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setMaxRedirects(3);
        Set<RedisNode> nodes = new HashSet<>();
        nodes.add(new RedisClusterNode("192.168.25.101", 6380));
        nodes.add(new RedisClusterNode("192.168.25.101", 6381));
        nodes.add(new RedisClusterNode("192.168.25.101", 6382));
        redisClusterConfiguration.setClusterNodes(nodes);
        return redisClusterConfiguration;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisClusterConfiguration redisClusterConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        jedisConnectionFactory.setTimeout(timeout);
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
