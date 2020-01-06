package cn.e3mall.service;

import cn.e3mall.pojo.TbContent;

import java.util.List;

public interface ContentService {

	List<TbContent> getContentById(long id);
	TbContent updateContent(long id);

	List<TbContent> getContentById2(long id);

	List<TbContent> getContentById3(long id);

}
