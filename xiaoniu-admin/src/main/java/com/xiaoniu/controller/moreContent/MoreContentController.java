package com.xiaoniu.controller.moreContent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.db.domain.CmpyMoreContent;
import com.xiaoniu.service.moreContent.MoreContentService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/secure/moreContent")
public class MoreContentController {
	private Logger log = Logger.getLogger(MoreContentController.class);
	
	@Autowired
	private MoreContentService service;
	
	@RequestMapping("principle.html")
	private ModelAndView principleHtml(){
		ModelAndView mv = new ModelAndView("secure/principle");
		try{
			mv.addObject("p1", service.selectByKey(51));
			mv.addObject("pp1", service.selectByKey(52));
			mv.addObject("pp2", service.selectByKey(53));
			mv.addObject("pp3", service.selectByKey(54));
			mv.addObject("p2", service.selectByKey(61));
			mv.addObject("p3", service.selectByKey(62));
			mv.addObject("p4", service.selectByKey(63));
			mv.addObject("p5", service.selectByKey(64));
			mv.addObject("p6", service.selectByKey(65));
			mv.addObject("p7", service.selectByKey(66));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("advance.html")
	private ModelAndView advanceHtml(){
		ModelAndView mv = new ModelAndView("secure/advance");
		try{
			mv.addObject("p1", service.selectByKey(11));
			mv.addObject("p2", service.selectByKey(12));
			mv.addObject("p3", service.selectByKey(13));
			mv.addObject("p4", service.selectByKey(14));
			mv.addObject("p5", service.selectByKey(15));
			mv.addObject("p6", service.selectByKey(16));
			mv.addObject("p7", service.selectByKey(17));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(CmpyMoreContent entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Date now = new Date();
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
			if(entity.getId() != null){
				CmpyMoreContent tmp = service.selectByKey(entity.getId());
				if(tmp != null){
					service.updateAll(entity);
				}else{
					service.save(entity);
				}
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
				map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
			}else{
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
				map.put(Contants.MSG, "ID 不能为空");
			}
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_FAILED.getMsg());
		}
		return map;
	}
}
