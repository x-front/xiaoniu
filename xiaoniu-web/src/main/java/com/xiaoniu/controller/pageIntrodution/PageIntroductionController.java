package com.xiaoniu.controller.pageIntrodution;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Map<String,Object> find(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("entity", service.selectByKey(id));
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
	public Map<String,Object> indexPageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("voice", service.selectByKey(29));
			map.put("family", service.selectByKey(25));
			map.put("sports", service.selectByKey(26));
			map.put("welfare", service.selectByKey(27));
			map.put("education", service.selectByKey(28));
			map.put("who", service.selectByKey(23));
			map.put("doWhat", service.selectByKey(24));
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
	public Map<String,Object> joinUsPageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("joinUsHead", service.selectByKey(22));
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
	public Map<String,Object> beautyPageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("family", service.selectByKey(18));
			map.put("sports", service.selectByKey(19));
			map.put("welfare", service.selectByKey(20));
			map.put("education", service.selectByKey(21));
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
	public Map<String,Object> dongtaiPageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("news", service.selectByKey(16));
			map.put("report", service.selectByKey(17));
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
	public Map<String,Object> voicePageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("voice", service.selectByKey(13));
			map.put("thinking", service.selectByKey(14));
			map.put("video", service.selectByKey(15));
			map.put("journal", service.selectByKey(30));
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
	public Map<String,Object> doWhatPageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("who", service.selectByKey(7));
			map.put("advance", service.selectByKey(8));
			map.put("manager", service.selectByKey(9));
			map.put("princeple", service.selectByKey(10));
			map.put("honor", service.selectByKey(11));
			map.put("culture", service.selectByKey(12));
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
	public Map<String,Object> whoPageInfo(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("who", service.selectByKey(1));
			map.put("advance", service.selectByKey(2));
			map.put("manager", service.selectByKey(3));
			map.put("princeple", service.selectByKey(4));
			map.put("honor", service.selectByKey(5));
			map.put("culture", service.selectByKey(6));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
}
