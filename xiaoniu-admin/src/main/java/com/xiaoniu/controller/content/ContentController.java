package com.xiaoniu.controller.content;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyContent;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/secure/content")
public class ContentController extends BaseController<CmpyContent>{
	Logger log = Logger.getLogger(ContentController.class);
	@RequestMapping("content.html")
	public ModelAndView contentHtml(Integer type,Integer id){
		ModelAndView mv = new ModelAndView("secure/content");
		mv.addObject("type", type);
		mv.addObject("id", id);
		try {
			CmpyContent entity = service.selectByKey(id);
			mv.addObject("content", entity);
		} catch (Exception e) {
			log.error(e);
		}
		return mv;
	}
	
	
	@RequestMapping("hr-xc.html")
	public ModelAndView xcHtml(Integer type,Integer id){
		ModelAndView mv = new ModelAndView("secure/hr-xc");
		mv.addObject("type", type);
		mv.addObject("id", id);
		try {
			CmpyContent entity = service.selectByKey(id);
			mv.addObject("content", entity);
		} catch (Exception e) {
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("hr-j.html")
	public ModelAndView hrjHtml(Integer type,Integer id){
		ModelAndView mv = new ModelAndView("secure/hr-j");
		mv.addObject("type", type);
		mv.addObject("id", id);
		try {
			CmpyContent entity = service.selectByKey(id);
			mv.addObject("content", entity);
		} catch (Exception e) {
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(CmpyContent entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Date now = new Date();
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
			CmpyContent tmp = service.selectByKey(entity.getId());
			if(tmp == null){
				service.save(entity);
			}else{
				service.updateAll(entity);
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
