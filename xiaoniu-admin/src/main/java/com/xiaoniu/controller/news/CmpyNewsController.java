package com.xiaoniu.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.service.news.CmpyNewsService;
import com.xiaoniu.db.domain.CmpyNews;
import com.xiaoniu.controller.base.BaseController;

@Controller
@RequestMapping("/secure/news")
public class CmpyNewsController extends BaseController<CmpyNews>{
	
	@Autowired
	private CmpyNewsService service;
	
	@RequestMapping("news.html")
	public String newsHtml(){
		return "secure/news";
	}
}
