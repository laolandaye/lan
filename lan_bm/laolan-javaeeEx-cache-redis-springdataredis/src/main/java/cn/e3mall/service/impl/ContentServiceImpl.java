package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import cn.e3mall.redisUtil.*;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Qualifier("redisCacheManager")
    @Autowired
    private RedisCacheManager redisCacheManager;

    @Qualifier("stringRedisCacheManager")
    @Autowired
    private RedisCacheManager stringRedisCacheManager;

	@Override
	public List<TbContent> getContentById(long id) {
        //查询缓存
        try {
            //如果缓存中有直接响应结果
            String json = (String) stringRedisTemplate.opsForHash().get( RedisStatic.CONTENT_LIST,id + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(id);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        //把结果添加到缓存
        try {
            stringRedisTemplate.opsForHash().put(RedisStatic.CONTENT_LIST,  id + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

    @Override
    public TbContent updateContent(long id) {
        TbContent tbContent = contentMapper.selectByPrimaryKey(id);
        Date nowDate = new Date();
        tbContent.setCreated(nowDate);
        tbContent.setUpdated(nowDate);

        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria  = tbContentExample.createCriteria();
        criteria.andIdEqualTo(id);

        //插入到数据库
        contentMapper.updateByPrimaryKeySelective(tbContent);
        //缓存同步,删除缓存中对应的数据。
//        jedisClient.hdel(RedisStatic.CONTENT_LIST, tbContent.getId().toString());
        stringRedisTemplate.opsForHash().delete( RedisStatic.CONTENT_LIST, tbContent.getId().toString());
        return tbContent;
    }

    @Cacheable(cacheNames = { "CONTENT_LIST"}, key = "'' + #id")
    @Override
    public List<TbContent> getContentById2(long id) {
        redisCacheManager.getCache(RedisStatic.CONTENT_LIST);
        stringRedisCacheManager.getCache(RedisStatic.CONTENT_LIST);
        //如果没有查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(id);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public List<TbContent> getContentById3(long id) {
        //查询缓存
        try {
            //如果缓存中有直接响应结果
            String json = (String) RedisUtils.hget( RedisStatic.CONTENT_LIST,id + "");
//            String json = (String) stringRedisTemplate.opsForHash().get( RedisStatic.CONTENT_LIST,id + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(id);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        //把结果添加到缓存
        try {
            RedisUtils.hset(RedisStatic.CONTENT_LIST,  id + "", JsonUtils.objectToJson(list), 60);
//            stringRedisTemplate.opsForHash().put(RedisStatic.CONTENT_LIST,  id + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void testUserInfo() {
        //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
        redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) {
                int i = 0;
                for (; i < 100; i++) {
                    byte[] key = ("key:" + i).getBytes();
                    byte[] value = ("value:" + i).getBytes();
                    connection.set(key, value);
                }
                //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
                return i;

            }
        });

    }

}
