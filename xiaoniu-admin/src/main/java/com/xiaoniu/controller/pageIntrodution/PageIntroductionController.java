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
import com.xiaoniu.domain.LangType;
import com.xiaoniu.domain.PageInfoType;
import com.xiaoniu.domain.TerminalType;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/secure/pageIntrodution")
public class PageIntroductionController extends BaseController<CmpyPageIntroduction>{
	
	private Logger log = Logger.getLogger(PageIntroductionController.class);
	
	@RequestMapping("joinUsHead.html")
	public ModelAndView joinUsHeadHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/joinUsHead");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("joinUsHead", queryByCondition(lang,terminal,PageInfoType.XIAONIU_JION));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("beauty.html")
	public ModelAndView beautyHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/beauty");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("family", queryByCondition(lang,terminal,PageInfoType.FAMILY));
			mv.addObject("sports", queryByCondition(lang,terminal,PageInfoType.SPORTS));
			mv.addObject("welfare", queryByCondition(lang,terminal,PageInfoType.WELFARE));
			mv.addObject("education", queryByCondition(lang,terminal,PageInfoType.EDUCATION));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("dongtai.html")
	public ModelAndView dongtaiHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/dongtai");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("news", queryByCondition(lang,terminal,PageInfoType.NEWS));
			mv.addObject("report", queryByCondition(lang,terminal,PageInfoType.REPORT));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("voice.html")
	public ModelAndView voiceHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/voice");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("voice", queryByCondition(lang,terminal,PageInfoType.THINKING_VOICE));
			mv.addObject("thinking", queryByCondition(lang,terminal,PageInfoType.THINKING));
			mv.addObject("video", queryByCondition(lang,terminal,PageInfoType.VOICE));
			mv.addObject("journal", queryByCondition(lang,terminal,PageInfoType.NEIKAN));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	
	@RequestMapping("welcome.html")
	public ModelAndView welcomeHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/welcome");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("who", queryByCondition(lang,terminal,PageInfoType.INDEX_WHO));
			mv.addObject("doWhat", queryByCondition(lang,terminal,PageInfoType.INDEX_DO_WHAT));
			mv.addObject("family", queryByCondition(lang,terminal,PageInfoType.INDEX_FAMILY));
			mv.addObject("sports", queryByCondition(lang,terminal,PageInfoType.INDEX_SPORTS));
			mv.addObject("welfare", queryByCondition(lang,terminal,PageInfoType.INDEX_WELFARE));
			mv.addObject("education", queryByCondition(lang,terminal,PageInfoType.INDEX_EDUCATION));
			mv.addObject("voice", queryByCondition(lang,terminal,PageInfoType.INDEX_THINKING_VOICE));
			
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("doWhat.html")
	public ModelAndView doWhatHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/doWhat");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("who", queryByCondition(lang,terminal,PageInfoType.XIAONIU_PUHUI));
			mv.addObject("advance", queryByCondition(lang,terminal,PageInfoType.XIAONIU_ZAIXIAN));
			mv.addObject("manager", queryByCondition(lang,terminal,PageInfoType.XIAONIU_XINCAIFU));
			mv.addObject("princeple", queryByCondition(lang,terminal,PageInfoType.XIAONIU_FENQI));
			mv.addObject("honor", queryByCondition(lang,terminal,PageInfoType.XIAONIU_TOUZI));
			mv.addObject("culture", queryByCondition(lang,terminal,PageInfoType.SCIENCE));
		}catch(Exception e){
			log.error(e);
		}
		return mv;
	}
	
	@RequestMapping("who.html")
	public ModelAndView whoHtml(Integer lang,Integer terminal){
		ModelAndView mv = new ModelAndView("secure/who");
		try{
			mv.addObject("lang", lang);
			mv.addObject("terminal", terminal);
			mv.addObject("who", queryByCondition(lang,terminal,PageInfoType.WHO));
			mv.addObject("advance", queryByCondition(lang,terminal,PageInfoType.ADVANCE));
			mv.addObject("manager", queryByCondition(lang,terminal,PageInfoType.MANAGER));
			mv.addObject("princeple", queryByCondition(lang,terminal,PageInfoType.PRINCEPLE));
			mv.addObject("honor", queryByCondition(lang,terminal,PageInfoType.HONOR));
			mv.addObject("culture", queryByCondition(lang,terminal,PageInfoType.CULTURE));
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
			if(entity.getLang() == null){
				entity.setLang(LangType.CN);
			}
			
			if(entity.getTerminal() == null){
				entity.setTerminal(TerminalType.PC);
			}
			
			if(entity.getType() != null){
				CmpyPageIntroduction tmp = queryByCondition(entity.getLang(),entity.getTerminal(),entity.getType());
				if(tmp != null){
					entity.setId(tmp.getId());
					service.updateAll(entity);
				}else{
					service.save(entity);
				}
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
				map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
			}else{
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
				map.put(Contants.MSG, "type can not be null");
			}
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	private CmpyPageIntroduction queryByCondition(Integer lang,Integer terminal,Integer type) throws Exception{
		if(lang == null || lang != 1){
			lang = 0;
		}
		CmpyPageIntroduction entity = new CmpyPageIntroduction();
		entity.setLang(lang);
		entity.setType(type);
		return service.oneSelect(entity);
	}
}
