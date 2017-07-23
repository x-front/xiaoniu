package com.xiaoniu.controller.honor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyHonor;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;

@Controller
@RequestMapping("/secure/honor")
public class HonorController extends BaseController<CmpyHonor>{
	@RequestMapping("honor.html")
	public ModelAndView HonorHtml(){
		ModelAndView mv = new ModelAndView("secure/honor");
		return mv;
	}
	
	@RequestMapping("batchUpdateHonorLang")
	@ResponseBody
	public Map<String,Object> batchUpdateHonorLang(Integer lang,String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			for (int i = 0; i < ids.length; i++) {
				CmpyHonor entity  = new CmpyHonor();
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
