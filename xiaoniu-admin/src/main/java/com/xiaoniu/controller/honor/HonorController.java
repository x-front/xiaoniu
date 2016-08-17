package com.xiaoniu.controller.honor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyHonor;

@Controller
@RequestMapping("/secure/honor")
public class HonorController extends BaseController<CmpyHonor>{
	@RequestMapping("honor.html")
	public ModelAndView HonorHtml(){
		ModelAndView mv = new ModelAndView("secure/honor");
		return mv;
	}
}
