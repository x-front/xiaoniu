package com.xiaoniu.controller.leader;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyLeader;
import com.xiaoniu.service.leader.LeaderService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/leader")
public class LeaderController {
	private Logger log = Logger.getLogger(LeaderController.class);
	
	@Autowired
	private LeaderService service;
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> report(Integer page,Integer  rows,CmpyLeader entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			PageInfo<CmpyLeader> pageInfo = service.queryList(page, rows, " serial_number asc,id desc ", entity);
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
}
