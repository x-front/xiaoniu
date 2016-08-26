package com.xiaoniu.controller.news;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.service.news.CmpyNewsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.xiaoniu.db.domain.CmpyNews;
import com.xiaoniu.controller.base.BaseController;

@Controller
@RequestMapping("/secure/news")
public class CmpyNewsController extends BaseController<CmpyNews>{
	
	@Autowired
	private CmpyNewsService service;
	
	@RequestMapping("news.html")
	public ModelAndView newsHtml(Integer type){
		ModelAndView mv = new ModelAndView("secure/news");
		mv.addObject("type", type);
		return mv;
	}
	
	@RequestMapping("setTop")
	@ResponseBody
	public Map<String,Object> setTop(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			
			CmpyNews tmp = new CmpyNews();
			tmp.setTop(1);
			List<CmpyNews> list = service.select(tmp);
			if(list != null && list.size() > 0 ){
				for(int i=0; i<list.size(); i++){
					CmpyNews en = new CmpyNews();
					en.setId(list.get(i).getId());
					en.setSerialNumber(1);
					en.setTop(0);
					service.updateNotNull(en);
				}
			}
			
			Date now = new Date();
			CmpyNews entity = new CmpyNews();
			entity.setId(id);
			entity.setSerialNumber(-9999);
			entity.setTop(1);
			entity.setUpdateTime(now);
			service.updateNotNull(entity);
			
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
}
