package cn.e3mall.controller;

import cn.e3mall.pojo.TbContent;
import cn.e3mall.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 首页展示Controller
 */
@Controller
public class IndexController {
	

	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	@ResponseBody
	public List<TbContent> showIndex(Model model) {
		return contentService.getContentById(28);
	}

	@RequestMapping(value="/content/save")
	@ResponseBody
	public String updateContent() {
		return contentService.updateContent();
	}
}
