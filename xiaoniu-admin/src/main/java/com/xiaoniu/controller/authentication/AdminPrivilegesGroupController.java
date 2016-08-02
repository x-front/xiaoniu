package com.xiaoniu.controller.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.AdminPrivilegesGroup;

@Controller
@RequestMapping("/secure/adminUserPrivilege")
public class AdminPrivilegesGroupController extends BaseController<AdminPrivilegesGroup>{
	@RequestMapping("adminUserPrivilege.html")
	public String adminUserPrivilegeHtml(){
		return "secure/adminUserPrivilege";
	}
}
