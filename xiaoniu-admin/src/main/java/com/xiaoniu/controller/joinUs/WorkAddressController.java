package com.xiaoniu.controller.joinUs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyWorkAddr;

@Controller
@RequestMapping("/secure/workAddr")
public class WorkAddressController extends BaseController<CmpyWorkAddr>{
	@RequestMapping("workAddress.html")
	public String workAddressHtml(){
		return "secure/workAddress";
	}
}
