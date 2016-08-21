package com.xiaoniu.controller.pageIntrodution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyPageIntroduction;

@Controller
@RequestMapping("/secure/pageIntrodution")
public class PageIntroductionController extends BaseController<CmpyPageIntroduction>{
	@RequestMapping("pageIntrodution.html")
	public ModelAndView pageIntrodutionHtml(){
		ModelAndView mv = new ModelAndView("secure/pageIntrodution");
		return mv;
	}
	
	@RequestMapping("who.html")
	public ModelAndView whoHtml(){
		ModelAndView mv = new ModelAndView("secure/who");
		return mv;
	}
}
