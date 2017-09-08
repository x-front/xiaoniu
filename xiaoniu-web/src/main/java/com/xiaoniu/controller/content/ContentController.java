package com.xiaoniu.controller.content;

import java.util.HashMap;
import java.util.Map;

import com.xiaoniu.controller.constant.LangType;
import com.xiaoniu.db.domain.CmpyContent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoniu.service.content.ContentService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@RequestMapping("/content")
@Controller
public class ContentController {
	Logger log = Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService service;
	
	@RequestMapping("find")
	@ResponseBody
	public Map<String,Object> find(Integer id,Integer terminal, Integer lang){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
//			map.put("entity", service.selectByKey(id));
			map.put("entity", queryByCondition(lang,terminal,id));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}

	private CmpyContent queryByCondition(Integer lang, Integer terminal, Integer type) throws Exception{
		if(lang == null || lang.intValue() != LangType.EN.intValue()){
			lang = LangType.CN;
		}
		CmpyContent entity = new CmpyContent();
		entity.setLang(lang);
		entity.setType(type);
//		entity.setTerminal();
		return service.oneSelect(entity);
	}
}
