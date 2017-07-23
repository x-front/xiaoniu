package com.xiaoniu.controller.indexNews;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyIndexNews;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;

@Controller
@RequestMapping("/secure/indexNews")
public class IndexNewsController extends BaseController<CmpyIndexNews>{
	@RequestMapping("indexNews.html")
	public String IndexNewsHtml(){
		return "secure/indexNews";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(CmpyIndexNews entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Date now = new Date();
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
			CmpyIndexNews tmp = service.selectByKey(entity.getId());
			if(tmp == null){
				service.save(entity);
			}else{
				service.updateNotNull(entity);
			}
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("batchUpdateIndexNewsLang")
	@ResponseBody
	public Map<String,Object> batchUpdateIndexNewsLang(Integer lang,String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			for (int i = 0; i < ids.length; i++) {
				CmpyIndexNews entity  = new CmpyIndexNews();
				entity.setId(ids[i]);
				entity.setLang(lang);
				service.updateNotNull(entity);
			}
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_SUCCESS.getCode());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
}
