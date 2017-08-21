package com.xiaoniu.controller.moreContent;

import java.util.HashMap;
import java.util.Map;

import com.xiaoniu.controller.constant.LangType;
import com.xiaoniu.db.domain.CmpyMoreContent;
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
	public Map<String,Object> principle(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("p1", queryByCondition(11,lang,terminal));
			map.put("p2", queryByCondition(12,lang,terminal));
			map.put("p3", queryByCondition(13,lang,terminal));
			map.put("p4", queryByCondition(14,lang,terminal));
			map.put("p5", queryByCondition(15,lang,terminal));
			map.put("p6", queryByCondition(16,lang,terminal));
			map.put("p7", queryByCondition(17,lang,terminal));
		}catch(Exception e){
			log.error("",e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("advance")
	@ResponseBody
	private Map<String,Object> advanceHtml(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("p1", queryByCondition(51,lang,terminal));
			map.put("pp1", queryByCondition(52,lang,terminal));
			map.put("pp2", queryByCondition(53,lang,terminal));
			map.put("pp3", queryByCondition(54,lang,terminal));
			map.put("p2", queryByCondition(61,lang,terminal));
			map.put("p3", queryByCondition(62,lang,terminal));
			map.put("p4", queryByCondition(63,lang,terminal));
			map.put("p5", queryByCondition(64,lang,terminal));
			map.put("p6", queryByCondition(65,lang,terminal));
			map.put("p7", queryByCondition(66,lang,terminal));
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}

	private CmpyMoreContent queryByCondition(Integer id, Integer lang, Integer terminal)throws Exception{
		if(lang == null){
			lang = LangType.CN;
		}
		Integer mid = lang * 1000 + id;
		return service.selectByKey(mid);
	}
}
