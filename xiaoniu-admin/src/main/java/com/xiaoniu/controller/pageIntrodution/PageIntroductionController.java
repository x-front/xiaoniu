package com.xiaoniu.controller.pageIntrodution;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyPageIntroduction;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/secure/pageIntrodution")
public class PageIntroductionController extends BaseController<CmpyPageIntroduction>{
	
	private Logger log = Logger.getLogger(PageIntroductionController.class);
	
	@RequestMapping("doWhat.html")
	public ModelAndView pageIntrodutionHtml(){
		ModelAndView mv = new ModelAndView("secure/doWhat");
		try{
			mv.addObject("who", service.selectByKey(7));
			mv.addObject("advance", service.selectByKey(8));
			mv.addObject("manager", service.selectByKey(9));
			mv.addObject("princeple", service.selectByKey(10));
			mv.addObject("honor", service.selectByKey(11));
			mv.addObject("culture", service.selectByKey(12));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("who.html")
	public ModelAndView whoHtml(){
		ModelAndView mv = new ModelAndView("secure/who");
		try{
			mv.addObject("who", service.selectByKey(1));
			mv.addObject("advance", service.selectByKey(2));
			mv.addObject("manager", service.selectByKey(3));
			mv.addObject("princeple", service.selectByKey(4));
			mv.addObject("honor", service.selectByKey(5));
			mv.addObject("culture", service.selectByKey(6));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(CmpyPageIntroduction entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Date now = new Date();
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
			if(entity.getId() != null){
				CmpyPageIntroduction tmp = service.selectByKey(entity.getId());
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
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
}
