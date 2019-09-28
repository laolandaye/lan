package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.service.ContentService;
import cn.e3mall.jedis.JedisClient;
import cn.e3mall.jedis.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容管理Service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public String updateContent() {
        TbContent tbContent = contentMapper.selectByPrimaryKey(28L);
        Date nowDate = new Date();
        tbContent.setCreated(nowDate);
        tbContent.setUpdated(nowDate);

        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria  = tbContentExample.createCriteria();
        criteria.andIdEqualTo(28L);

        //插入到数据库
        contentMapper.updateByPrimaryKeySelective(tbContent);
        //缓存同步,删除缓存中对应的数据。
        jedisClient.hdel("CONTENT_LIST", tbContent.getId().toString());
        return "ok";
    }

	@Override
	public List<TbContent> getContentById(long id) {
        //查询缓存
        try {
            //如果缓存中有直接响应结果
            String json = jedisClient.hget("CONTENT_LIST", id + "");
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
            jedisClient.hset("CONTENT_LIST", id + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

}
