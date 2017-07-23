/**
 * @author zxx
 * @time 2016年11月9日下午3:57:52
 */
package com.xiaoniu.controller.imageNews;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyImageNews;
import com.xiaoniu.db.domain.CmpyImageNewsHead;
import com.xiaoniu.domain.LangType;
import com.xiaoniu.service.imageNews.ImageNewsHeadService;
import com.xiaoniu.service.imageNews.ImageNewsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;

/**
 * @author zxx
 * @time 2016年11月9日
 *
 */
@Controller
@RequestMapping("/secure/imageNews")
public class ImageNewsController extends BaseController<CmpyImageNews>{
	
	@Autowired
	private ImageNewsService service;
	
	@Autowired
	private ImageNewsHeadService headService;
	
	@RequestMapping("imageNews.html")
	public String imageNewsHtml(){
		return "secure/imageNews";
	}
	
	@RequestMapping("queryImageHeadList")
	@ResponseBody
	public Map<String,Object> queryImageHeadList(Integer page,Integer  rows, String orderBy,CmpyImageNewsHead entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(orderBy == null || "".equals(orderBy.trim())){
				orderBy = " id desc";
			}
			PageInfo<CmpyImageNewsHead> pageInfo = headService.queryList(page, rows, orderBy, entity);
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("updateHeadImageNews")
	@ResponseBody
	public Map<String,Object> updateHeadImageNews(CmpyImageNewsHead entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(entity.getId() == null){
				throw new Exception("id can not be null");
			}
			headService.updateNotNull(entity);
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_SUCCESS.getCode());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("batchUpdateHeadImageNewsLang")
	@ResponseBody
	public Map<String,Object> batchUpdateHeadImageNewsLang(Integer lang,String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			for (int i = 0; i < ids.length; i++) {
				CmpyImageNewsHead entity  = new CmpyImageNewsHead();
				entity.setId(ids[i]);
				entity.setLang(lang);
				headService.updateNotNull(entity);
			}
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_SUCCESS.getCode());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("saveImageNews")
	@ResponseBody
	public Map<String,Object> saveImageNews(Integer id,Integer valid,String title, Long showTime, String img1,String img2,String img3,Integer lang,String data){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			CmpyImageNewsHead imageNewsHead = new CmpyImageNewsHead();
			if(id != null && id > 0){
				service.deleteImageNewsByNewsId(id);
				headService.delete(id);
				imageNewsHead.setId(id);
			}
			
			JSONArray list = JSONObject.parseArray(data);
			if(list != null && list.size() > 2){
				Date now = new Date();
				imageNewsHead.setValid(valid);
				imageNewsHead.setTitle(title);
				imageNewsHead.setCreateTime(now);
				imageNewsHead.setUpdateTime(now);
				imageNewsHead.setShowTime(showTime);
				imageNewsHead.setImgUrl1(img1);
				imageNewsHead.setImgUrl2(img2);
				imageNewsHead.setImgUrl3(img3);
				if(lang == null){
					lang = LangType.CN;
				}
				imageNewsHead.setLang(lang);
				imageNewsHead = headService.save(imageNewsHead);
				Integer newId = null;
				if(id != null && id > 0){
					newId = id;
				}else{
					newId = imageNewsHead.getId();
				}
				for(int i=0; i<list.size();i++){
					JSONObject jsObj = list.getJSONObject(i);
					CmpyImageNews entity = new CmpyImageNews();
					entity.setCreateTime(now);
					entity.setUpdateTime(now);
					entity.setImage(jsObj.getString("image"));
					entity.setNewsId(newId);
					entity.setContent(jsObj.getString("content"));
					entity.setSerialNumber(jsObj.getInteger("serialNumber"));
					entity.setValid( MsgCode.TRUE.getCode());
					service.save(entity);
				}
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
				map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
			}else{
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
				map.put(Contants.MSG, "json解析出错。");
			}
			
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	
	@RequestMapping("queryImageNewsByNewsId")
	@ResponseBody
	public Map<String,Object> queryImageNewsByNewsId(Integer newsId){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			CmpyImageNews entity = new CmpyImageNews();
			entity.setNewsId(newsId);
			List<CmpyImageNews> list = service.select(entity);
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
			map.put("list", list);
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("batchUpdateValids")
	@ResponseBody
	public Map<String,Object> batchUpdateValid(String strIds,Integer valid){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			headService.batchUpdateValid(valid, ids);
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.UPDATE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("batchDel")
	@ResponseBody
	public Map<String,Object> batchDel(String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			headService.delete(ids);
			for (int i = 0; i < ids.length; i++) {
				service.deleteImageNewsByNewsId(ids[i]);
			}
			map.put(Contants.RESULT_CODE, MsgCode.DELETE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.DELETE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.DELETE_FAILED.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("queryImageNews")
	@ResponseBody
	public Map<String,Object> queryImageNews(Integer id,Integer count){
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject jsObj = new JSONObject();
		List<CmpyImageNewsHead> dl = new ArrayList<CmpyImageNewsHead>();
		try{
			if(count == null || count < 1 || count > 20){
				count = 10;
			}
			CmpyImageNewsHead hre = headService.selectByKey(id);
			CmpyImageNews entity = new CmpyImageNews();
			entity.setValid(MsgCode.TRUE.getCode());
			entity.setNewsId(id);
			List<CmpyImageNews> reList = service.select(entity);
			
			Example example = new Example(CmpyImageNewsHead.class);
			Criteria crt = example.createCriteria();
			crt.andBetween("id", id - count > 0 ? id -count : 0 , id + count);
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
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e.getMessage());
		}
		return map;
	}

}
