package cn.e3mall.controller;

import cn.e3mall.pojo.TbContent;
import cn.e3mall.redisUtil.RedisUseUtils;
import cn.e3mall.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 首页展示Controller
 */
@Controller
public class ContentController {
	

	@Autowired
	private ContentService contentService;

	/* 原始逻辑 */
	@RequestMapping("/content/{id}")
	@ResponseBody
	public List<TbContent> showIndex(Model model, @PathVariable(name = "id") Long id) {
		return contentService.getContentById(id);
	}

	/* spring-cache */
	@RequestMapping("/content/springcache/{id}")
	@ResponseBody
	public List<TbContent> showIndex2(Model model, @PathVariable(name = "id") Long id) {
		return contentService.getContentById2(id);
	}

	/* AOP 结合RedisUtils */
	@RequestMapping("/content/aop/{id}")
	@ResponseBody
	public List<TbContent> showIndex3(Model model, @PathVariable(name = "id") Long id) {
		return contentService.getContentById3(id);
	}


	@RequestMapping(value="/content/update/{id}")
	@ResponseBody
	public TbContent updateContent( @PathVariable(name = "id") Long id) {
		return contentService.updateContent(id);
	}
}
