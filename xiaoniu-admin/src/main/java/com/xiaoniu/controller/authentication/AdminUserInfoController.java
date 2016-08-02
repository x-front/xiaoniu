package com.xiaoniu.controller.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.AdminUserInfo;
import com.xiaoniu.db.domain.AdminUserInfoVO;
import com.xiaoniu.db.domain.AdminUserRole;
import com.xiaoniu.service.adminUserInfo.AdminUserInfoService;
import com.xiaoniu.service.adminUserRole.AdminUserRoleService;
import com.xiaoniu.utils.ZxxzxjDESPasswordEncoder;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;


@Controller
@RequestMapping("/secure/adminUserInfo")
public class AdminUserInfoController extends BaseController<AdminUserInfo>{
	
	@Autowired
	private AdminUserRoleService adminUserRoleService;
	
	@Autowired
	private AdminUserInfoService service;
	
	@RequestMapping("adminUserInfo.html")
	public ModelAndView html(){
		ModelAndView mv = new ModelAndView("secure/adminUserInfo");
		return mv;
	}
	
	@RequestMapping("queryAdminUserInfoVOList")
	@ResponseBody
	public Map<String,Object> queryAdminUserInfoVO(AdminUserInfo entity,Integer page,Integer rows){
		Map<String,Object> map = new HashMap<String,Object>();
		List<AdminUserInfoVO> list = null;
		try{
			long r = service.queryAdminUserInfoVOTotalCount(entity.getId(), entity.getUserName());
			if(r > 0 ){
				list = service.queryAdminUserInfoVO(entity.getId(), entity.getUserName(), page, rows);
			}
			map.put(Contants.TOTAL, r);
			map.put(Contants.ROWS, list);
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("insertAdminUserInfo")
	@ResponseBody
	public Map<String,Object> insertAdminUserInfo(AdminUserInfo entity,Integer roleId){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Date now = new Date();
			ZxxzxjDESPasswordEncoder des = new ZxxzxjDESPasswordEncoder();
			String encPassword = des.encodePassword(entity.getPassword(), null);
			entity.setPassword(encPassword);
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
			entity.setValid(MsgCode.TRUE.getCode());
			AdminUserInfo result = service.save(entity);
			
			
			AdminUserRole adminUserRoleVO = new AdminUserRole();
			adminUserRoleVO.setRoleId(roleId);
			adminUserRoleVO.setUserId(result.getId());
			adminUserRoleVO.setValid(MsgCode.TRUE.getCode());
			adminUserRoleVO.setCreateTime(now);
			adminUserRoleVO.setUpdateTime(now);
			adminUserRoleService.save(adminUserRoleVO);
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("updateAdminUserInfo")
	@ResponseBody
	public Map<String,Object> updateAdminUserInfo(AdminUserInfo entity,Integer roleId){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Date now = new Date();
			if(entity.getPassword() != null && !"".equals(entity.getPassword().trim())){
				ZxxzxjDESPasswordEncoder des = new ZxxzxjDESPasswordEncoder();
				String encPassword = des.encodePassword(entity.getPassword(), null);
				entity.setPassword(encPassword);
			}
			entity.setUpdateTime(now);
			service.updateNotNull(entity);
			
			AdminUserRole adminUserRoleVO = new AdminUserRole();
			adminUserRoleVO.setRoleId(roleId);
			adminUserRoleVO.setUserId(entity.getId());
			adminUserRoleVO.setValid(MsgCode.TRUE.getCode());
			adminUserRoleVO.setUpdateTime(now);
			adminUserRoleService.updateNotNull(adminUserRoleVO);
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	} 
}
