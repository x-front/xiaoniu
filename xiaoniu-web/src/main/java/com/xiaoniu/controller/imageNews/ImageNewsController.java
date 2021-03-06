/**
 * @author zxx
 * @time 2017年6月26日下午4:02:06
 */
package com.xiaoniu.controller.imageNews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoniu.controller.constant.LangType;
import com.xiaoniu.db.domain.CmpyImageNews;
import com.xiaoniu.db.domain.CmpyImageNewsHead;
import com.xiaoniu.service.imageNews.ImageNewsHeadService;
import com.xiaoniu.service.imageNews.ImageNewsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

/**
 * @author zxx
 * @time 2017年6月26日
 *
 */
@Controller
@RequestMapping("/imageNews")
public class ImageNewsController {
	
	@Autowired
	private ImageNewsService service;
	
	@Autowired
	private ImageNewsHeadService headService;
	
	@RequestMapping("queryImageHeadList")
	@ResponseBody
	public Map<String,Object> queryImageHeadList(Integer page,Integer  rows,Integer lang){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			CmpyImageNewsHead entity = new CmpyImageNewsHead();
			entity.setValid(MsgCode.TRUE.getCode());
			if (lang == null) {
				entity.setLang(LangType.CN);
			} else {
				entity.setLang(lang);
			}
			PageInfo<CmpyImageNewsHead> pageInfo = headService.queryList(page, rows, "serial_number desc,id desc", entity);
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("queryImageNews")
	@ResponseBody
	public Map<String,Object> queryImageNews(Integer id,Integer count,Integer lang){
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject jsObj = new JSONObject();
		List<CmpyImageNewsHead> dl = new ArrayList<CmpyImageNewsHead>();
		try{
			if(count == null || count < 1 || count > 20){
				count = 10;
			}
			CmpyImageNewsHead hre = headService.selectByKey(id);
			if(hre.getValid().intValue() != MsgCode.TRUE.getCode().intValue()){
				throw new Exception("not valid");
			}
			CmpyImageNews entity = new CmpyImageNews();
			entity.setValid(MsgCode.TRUE.getCode());
			entity.setNewsId(id);
			List<CmpyImageNews> reList = service.select(entity);
			
			Example example = new Example(CmpyImageNewsHead.class);
			Criteria crt = example.createCriteria();
			crt.andEqualTo("valid", MsgCode.TRUE.getCode());
			crt.andBetween("id", id - count > 0 ? id -count : 0 , id + count);
			if(lang == null){
				lang = LangType.CN;
			}
			crt.andEqualTo("lang", lang);
			List<CmpyImageNewsHead> dlist = headService.selectByExample(example );
			if(dlist!= null && dlist.size() > 0){
				int di = (dlist.size() - count);
				int cu = 0;
				if(di > 0){
					cu = di / 2 ;
				}
				for (int i = cu; i < count && i < dlist.size(); i++) {
					dl.add(dlist.get(i));
				}
			}
			
			jsObj.put("hd", hre);
			jsObj.put("imgs", reList);
			jsObj.put("dl", dl);
			map.put(Contants.DATA, jsObj);
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
}
