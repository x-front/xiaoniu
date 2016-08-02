package com.xiaoniu.controller.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.AdminPrivilegesGroup;

@Controller
@RequestMapping("/secure/adminUserPrivilegesGroup")
public class AdminPrivilegesGroupController extends BaseController<AdminPrivilegesGroup>{
	@RequestMapping("adminPrivilegesGroup.html")
	public String adminUserPrivilegeHtml(){
		return "secure/adminPrivilegesGroup";
	}
}
