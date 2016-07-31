package com.xiaoniu.controller.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoniu.db.domain.MenuVO;
import com.xiaoniu.domain.AdminUserDetailsVO;
import com.xiaoniu.service.menu.MenuService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;


@Controller
@RequestMapping("/secure/menu/*")
public class MenuController {
	@Autowired
	private MenuService service;
	
	
	@RequestMapping(value="queryMenu",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryMenu() {
		 Map<String,Object> modelMap = new HashMap<String,Object>();
		 try{
			 AdminUserDetailsVO user = (AdminUserDetailsVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 List<MenuVO> list = service.queryMenu(user.getAdminUserInfo().getId());
			 modelMap.put(Contants.DATA, list);
			 modelMap.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		 }catch(Exception e){
			 modelMap.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			 modelMap.put(Contants.MSG, e.getMessage());
		 }
		 return modelMap;
	}
}
