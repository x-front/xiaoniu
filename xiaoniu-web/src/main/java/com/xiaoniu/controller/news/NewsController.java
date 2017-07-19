package com.xiaoniu.controller.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyNews;
import com.xiaoniu.db.domain.NewsSearchVO;
import com.xiaoniu.service.news.CmpyNewsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Autowired
	private CmpyNewsService service;
	
	private Logger log = Logger.getLogger(NewsController.class);
	
	@RequestMapping("find")
	@ResponseBody
	public Map<String,Object> find(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			CmpyNews entity = service.selectByKey(id);
			map.put("entity", entity);
			if(entity != null){
				CmpyNews tmp = new CmpyNews();
				tmp.setId(id);
				tmp.setClickTimes(entity.getClickTimes()+1);
				service.updateNotNull(tmp);
			}
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer  rows,Integer top,CmpyNews entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			if(top != null && entity.getIsTop() == null){
				entity.setIsTop(top);
			}
			entity.setValid(MsgCode.TRUE.getCode());
			PageInfo<CmpyNews> pageInfo = service.queryList(page, rows, " serial_number desc,id desc ", entity);
			List<CmpyNews> list = pageInfo.getList();
			if(list != null){
				for(int i=0;i<list.size();i++){
					list.get(i).setContent("");
				}
			}
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, list);
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("newsList")
	@ResponseBody
	public Map<String,Object> newsList(Integer page,Integer  rows,Integer top,CmpyNews entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			if(top != null && entity.getIsTop() == null){
				entity.setIsTop(top);
			}
			entity.setValid(MsgCode.TRUE.getCode());
			PageInfo<CmpyNews> pageInfo = service.queryList(page, rows, " id asc ", entity);
			List<CmpyNews> list = pageInfo.getList();
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, list);
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Map<String,Object> search(Integer page,Integer  rows,Long totalCount,Integer type,Integer isEn,Integer isTop,String keyword){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			List<NewsSearchVO> list = service.search(page, rows, totalCount, type, isEn, isTop, keyword);
			Long reTotalCount = 0L;
			if(totalCount != null && totalCount < 1){
				service.searchTotalCount(type, isEn, isTop, keyword);
			}else{
				reTotalCount = totalCount;
			}
			map.put(Contants.TOTAL, reTotalCount);
			map.put(Contants.ROWS, list);
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
	
}
