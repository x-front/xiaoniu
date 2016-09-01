package com.xiaoniu.controller.honor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyHonor;
import com.xiaoniu.service.honor.HonorService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@RequestMapping("honor")
@Controller
public class HonorController {
	@Autowired
	private HonorService service;
	
	private Logger log = Logger.getLogger(HonorController.class);
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer  rows,CmpyHonor entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			entity.setValid(MsgCode.TRUE.getCode());
			PageInfo<CmpyHonor> pageInfo = service.queryList(page, rows, " serial_number asc,id desc ", entity);
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
