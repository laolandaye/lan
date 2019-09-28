package cn.e3mall.jedis;

import java.util.List;

public interface JedisClient {

	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	//存入map的数据
	Long hset(String key, String field, String value);
	//获得map的数据
	String hget(String key, String field);
	Long hdel(String key, String... field);
	Boolean hexists(String key, String field);
	List<String> hvals(String key);
	Long del(String key);
}
