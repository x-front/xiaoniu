package com.xiaoniu.controller.news;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.service.news.CmpyNewsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;
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
	
	@RequestMapping("jobnews.html")
	public ModelAndView jobnewsHtml(Integer type){
		ModelAndView mv = new ModelAndView("secure/jobnews");
		return mv;
	}
	
	@RequestMapping("societyJob.html")
	public ModelAndView societyJobHtml(Integer type){
		ModelAndView mv = new ModelAndView("secure/societyJob");
		return mv;
	}
	
	@RequestMapping("jobQA.html")
	public ModelAndView jobQAHtml(Integer type){
		ModelAndView mv = new ModelAndView("secure/jobQA");
		return mv;
	}
	
	@RequestMapping("setTop")
	@ResponseBody
	public Map<String,Object> setTop(Integer id,Integer type,Integer lang){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			
			CmpyNews tmp = new CmpyNews();
			tmp.setIsTop(1);
			tmp.setType(type);
			tmp.setLang(lang);
			List<CmpyNews> list = service.select(tmp);
			if(list != null && list.size() > 0 ){
				for(int i=0; i<list.size(); i++){
					CmpyNews en = new CmpyNews();
					en.setId(list.get(i).getId());
					en.setSerialNumber(1);
					en.setIsTop(0);
					service.updateNotNull(en);
				}
			}
			
			Date now = new Date();
			CmpyNews entity = new CmpyNews();
			entity.setId(id);
//			entity.setSerialNumber(-9999);
			entity.setIsTop(1);
			entity.setUpdateTime(now);
			service.updateNotNull(entity);
			
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Map<String,Object> find(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			CmpyNews entity = service.selectByKey(id);
			map.put("entity", entity);
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("batchUpdateNewsLang")
	@ResponseBody
	public Map<String,Object> batchUpdateNewsLang(Integer lang,String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			for (int i = 0; i < ids.length; i++) {
				CmpyNews entity  = new CmpyNews();
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

	@RequestMapping("queryNewsList")
	@ResponseBody
	public Map<String,Object> queryList(Integer page,Integer  rows, String orderBy,CmpyNews entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(orderBy == null || "".equals(orderBy.trim())){
				orderBy = " id desc";
			}
			PageInfo<CmpyNews> pageInfo = service.queryNewsList(page, rows, orderBy, entity);
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
}
