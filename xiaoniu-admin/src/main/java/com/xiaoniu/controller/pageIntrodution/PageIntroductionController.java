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
	
	@RequestMapping("joinUsHead.html")
	public ModelAndView joinUsHeadHtml(){
		ModelAndView mv = new ModelAndView("secure/joinUsHead");
		try{
			mv.addObject("joinUsHead", service.selectByKey(22));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("beauty.html")
	public ModelAndView beautyHtml(){
		ModelAndView mv = new ModelAndView("secure/beauty");
		try{
			mv.addObject("family", service.selectByKey(18));
			mv.addObject("sports", service.selectByKey(19));
			mv.addObject("welfare", service.selectByKey(20));
			mv.addObject("education", service.selectByKey(21));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("dongtai.html")
	public ModelAndView dongtaiHtml(){
		ModelAndView mv = new ModelAndView("secure/dongtai");
		try{
			mv.addObject("news", service.selectByKey(16));
			mv.addObject("report", service.selectByKey(17));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("voice.html")
	public ModelAndView voiceHtml(){
		ModelAndView mv = new ModelAndView("secure/voice");
		try{
			mv.addObject("voice", service.selectByKey(13));
			mv.addObject("thinking", service.selectByKey(14));
			mv.addObject("video", service.selectByKey(15));
			mv.addObject("journal", service.selectByKey(30));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	
	@RequestMapping("welcome.html")
	public ModelAndView welcomeHtml(){
		ModelAndView mv = new ModelAndView("secure/welcome");
		try{
			mv.addObject("voice", service.selectByKey(29));
			mv.addObject("family", service.selectByKey(25));
			mv.addObject("sports", service.selectByKey(26));
			mv.addObject("welfare", service.selectByKey(27));
			mv.addObject("education", service.selectByKey(28));
			mv.addObject("who", service.selectByKey(23));
			mv.addObject("doWhat", service.selectByKey(24));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("doWhat.html")
	public ModelAndView doWhatHtml(){
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
