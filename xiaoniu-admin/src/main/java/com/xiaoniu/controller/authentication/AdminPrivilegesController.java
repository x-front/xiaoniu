package com.xiaoniu.controller.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.AdminPrivileges;

@Controller
@RequestMapping("/secure/privileges")
public class AdminPrivilegesController extends BaseController<AdminPrivileges>{
	@RequestMapping("privileges.html")
	public String privilegesHtml(){
		return "secure/privileges";
	}
}
