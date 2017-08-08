package com.xiaoniu.controller.pageIntrodution;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoniu.controller.constant.PageInfoType;
import com.xiaoniu.db.domain.CmpyPageIntroduction;
import com.xiaoniu.service.pageIntroduction.PageIntrodutionService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@RequestMapping("/pageInfo")
@Controller
public class PageIntroductionController {
	
	private Logger log = Logger.getLogger(PageIntroductionController.class);
	
	@Autowired
	private PageIntrodutionService service;
	
	@RequestMapping("find")
	@ResponseBody
	public Map<String,Object> find(Integer lang,Integer terminal,Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("entity", queryByCondition(lang,terminal,id));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("index")
	@ResponseBody
	public Map<String,Object> indexPageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("who", queryByCondition(lang,terminal,PageInfoType.INDEX_WHO));
			map.put("doWhat", queryByCondition(lang,terminal,PageInfoType.INDEX_DO_WHAT));
			map.put("family", queryByCondition(lang,terminal,PageInfoType.INDEX_FAMILY));
			map.put("sports", queryByCondition(lang,terminal,PageInfoType.INDEX_SPORTS));
			map.put("welfare", queryByCondition(lang,terminal,PageInfoType.INDEX_WELFARE));
			map.put("education", queryByCondition(lang,terminal,PageInfoType.INDEX_EDUCATION));
			map.put("voice", queryByCondition(lang,terminal,PageInfoType.INDEX_THINKING_VOICE));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	
	@RequestMapping("joinUs")
	@ResponseBody
	public Map<String,Object> joinUsPageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("joinUsHead",  queryByCondition(lang,terminal,PageInfoType.XIAONIU_JION));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("beauty")
	@ResponseBody
	public Map<String,Object> beautyPageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("family", queryByCondition(lang,terminal,PageInfoType.FAMILY));
			map.put("sports", queryByCondition(lang,terminal,PageInfoType.SPORTS));
			map.put("welfare", queryByCondition(lang,terminal,PageInfoType.WELFARE));
			map.put("education", queryByCondition(lang,terminal,PageInfoType.EDUCATION));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("dongtai")
	@ResponseBody
	public Map<String,Object> dongtaiPageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("news", queryByCondition(lang,terminal,PageInfoType.NEWS));
			map.put("report", queryByCondition(lang,terminal,PageInfoType.REPORT));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("voice")
	@ResponseBody
	public Map<String,Object> voicePageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("voice", queryByCondition(lang,terminal,PageInfoType.THINKING_VOICE));
			map.put("thinking", queryByCondition(lang,terminal,PageInfoType.THINKING));
			map.put("video", queryByCondition(lang,terminal,PageInfoType.VOICE));
			map.put("journal", queryByCondition(lang,terminal,PageInfoType.NEIKAN));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("doWhat")
	@ResponseBody
	public Map<String,Object> doWhatPageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			
			map.put("who", queryByCondition(lang,terminal,PageInfoType.XIAONIU_PUHUI));
			map.put("advance", queryByCondition(lang,terminal,PageInfoType.XIAONIU_ZAIXIAN));
			map.put("manager", queryByCondition(lang,terminal,PageInfoType.XIAONIU_XINCAIFU));
			map.put("princeple", queryByCondition(lang,terminal,PageInfoType.XIAONIU_FENQI));
			map.put("honor", queryByCondition(lang,terminal,PageInfoType.XIAONIU_TOUZI));
			map.put("culture", queryByCondition(lang,terminal,PageInfoType.SCIENCE));
			
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("who")
	@ResponseBody
	public Map<String,Object> whoPageInfo(Integer lang,Integer terminal){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("who", queryByCondition(lang,terminal,PageInfoType.WHO));
			map.put("advance", queryByCondition(lang,terminal,PageInfoType.ADVANCE));
			map.put("manager", queryByCondition(lang,terminal,PageInfoType.MANAGER));
			map.put("princeple", queryByCondition(lang,terminal,PageInfoType.PRINCEPLE));
			map.put("honor", queryByCondition(lang,terminal,PageInfoType.HONOR));
			map.put("culture", queryByCondition(lang,terminal,PageInfoType.CULTURE));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
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
