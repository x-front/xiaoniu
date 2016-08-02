package com.xiaoniu.controller.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.AdminUserPrivileges;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;

@Controller
@RequestMapping("/secure/adminUserPrivilege")
public class AdminUserPrivilegesController extends BaseController<AdminUserPrivileges>{
	
	@RequestMapping("adminUserPrivilege.html")
	public String adminUserPrivilegeHtml(){
		return "secure/adminUserPrivilege";
	}
	
	@RequestMapping("batchInsert")
	@ResponseBody
	public Map<String,Object> batchInsert(Integer userId,String privilegeIds,Integer valid){
		Map<String,Object>map = new HashMap<String,Object>();
		try {
			Date now = new Date();
			Integer[] ids = StringUtils.convertStringToIds(privilegeIds);
			for(int i=0; i<ids.length; i++){
				AdminUserPrivileges entity = new AdminUserPrivileges();
				entity.setUserId(userId);
				entity.setPvgId(ids[i]);
				entity.setValid(valid);
				entity.setUpdateTime(now);
				service.save(entity);
			}
			
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
}
