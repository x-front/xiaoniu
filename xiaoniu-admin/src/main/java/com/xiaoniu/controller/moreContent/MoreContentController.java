package com.xiaoniu.controller.moreContent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoniu.domain.LangType;
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
	public ModelAndView principleHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/principle");
		mv.addObject("lang",lang);
		mv.addObject("terminal",terminal);
		try{

			mv.addObject("p1", queryByCondition(11,lang,terminal));
			mv.addObject("p2", queryByCondition(12,lang,terminal));
			mv.addObject("p3", queryByCondition(13,lang,terminal));
			mv.addObject("p4", queryByCondition(14,lang,terminal));
			mv.addObject("p5", queryByCondition(15,lang,terminal));
			mv.addObject("p6", queryByCondition(16,lang,terminal));
			mv.addObject("p7", queryByCondition(17,lang,terminal));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("advance.html")
	public ModelAndView advanceHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/advance");
		mv.addObject("lang",lang);
		mv.addObject("terminal",terminal);
		try{
			mv.addObject("p1", queryByCondition(51,lang,terminal));
			mv.addObject("pp1", queryByCondition(52,lang,terminal));
			mv.addObject("pp2", queryByCondition(53,lang,terminal));
			mv.addObject("pp3", queryByCondition(54,lang,terminal));
			mv.addObject("p2", queryByCondition(61,lang,terminal));
			mv.addObject("p3", queryByCondition(62,lang,terminal));
			mv.addObject("p4", queryByCondition(63,lang,terminal));
			mv.addObject("p5", queryByCondition(64,lang,terminal));
			mv.addObject("p6", queryByCondition(65,lang,terminal));
			mv.addObject("p7", queryByCondition(66,lang,terminal));
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
				CmpyMoreContent tmp = queryByCondition(entity.getId(),entity.getLang(),entity.getTerminal());
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

	private CmpyMoreContent queryByCondition(Integer id,Integer lang,Integer terminal)throws Exception{
		if(lang == null || lang.intValue() != LangType.EN.intValue()){
			lang = LangType.CN;
		}
		Integer mid = lang * 1000 + id;
		return service.selectByKey(mid);
	}

}
