package com.xiaoniu.controller.moreContent;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xiaoniu.service.moreContent.MoreContentService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/moreContent")
public class MoreContentController {
	Logger log = Logger.getLogger(MoreContentController.class);
	@Autowired
	private MoreContentService service;
	
	
	@RequestMapping("principle")
	@ResponseBody
	public Map<String,Object> principle(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("p1", service.selectByKey(11));
			map.put("p2", service.selectByKey(12));
			map.put("p3", service.selectByKey(13));
			map.put("p4", service.selectByKey(14));
			map.put("p5", service.selectByKey(15));
			map.put("p6", service.selectByKey(16));
			map.put("p7", service.selectByKey(17));
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("advance")
	@ResponseBody
	private Map<String,Object> advanceHtml(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("p1", service.selectByKey(51));
			map.put("pp1", service.selectByKey(52));
			map.put("pp2", service.selectByKey(53));
			map.put("pp3", service.selectByKey(54));
			map.put("p2", service.selectByKey(61));
			map.put("p3", service.selectByKey(62));
			map.put("p4", service.selectByKey(63));
			map.put("p5", service.selectByKey(64));
			map.put("p6", service.selectByKey(65));
			map.put("p7", service.selectByKey(66));
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
}
