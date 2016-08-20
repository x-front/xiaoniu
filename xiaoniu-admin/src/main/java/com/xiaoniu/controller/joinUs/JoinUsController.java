package com.xiaoniu.controller.joinUs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyJoinUs;

@Controller
@RequestMapping("/secure/joinUs")
public class JoinUsController extends BaseController<CmpyJoinUs>{
	@RequestMapping("joinUs.html")
	public String JoinUsHtml(){
		return "secure/joinUs";
	}
}
