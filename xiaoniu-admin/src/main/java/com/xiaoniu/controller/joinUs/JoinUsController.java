package com.xiaoniu.controller.joinUs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyJoinUs;
import com.xiaoniu.service.joinUs.JoinUsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/secure/joinUs")
public class JoinUsController extends BaseController<CmpyJoinUs>{
	
	@Autowired
	private JoinUsService service;
	
	@RequestMapping("joinUs.html")
	public String JoinUsHtml(){
		return "secure/joinUs";
	}
	
	@RequestMapping("queryJoinUsList")
	@ResponseBody
	public Map<String,Object> queryList(Integer page,Integer  rows, String orderBy,CmpyJoinUs entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(orderBy == null || "".equals(orderBy.trim())){
				orderBy = " id desc";
			}
			PageInfo<CmpyJoinUs> pageInfo = service.queryByAddressOrType(page, rows, orderBy, entity.getAddress(), entity.getType(),entity.getPosition());
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
}
