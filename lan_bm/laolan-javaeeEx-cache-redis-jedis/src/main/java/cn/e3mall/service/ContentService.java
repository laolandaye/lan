package cn.e3mall.service;

import cn.e3mall.pojo.TbContent;

import java.util.List;

public interface ContentService {

	String updateContent();

	List<TbContent> getContentById(long cid);
}
