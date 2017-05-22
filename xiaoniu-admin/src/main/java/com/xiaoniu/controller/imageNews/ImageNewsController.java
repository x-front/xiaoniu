/**
 * @author zxx
 * @time 2016年11月9日下午3:57:52
 */
package com.xiaoniu.controller.imageNews;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyImageNews;
import com.xiaoniu.db.domain.CmpyImageNewsHead;
import com.xiaoniu.service.imageNews.ImageNewsHeadService;
import com.xiaoniu.service.imageNews.ImageNewsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

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
	
	@RequestMapping("saveImageNews")
	public Map<String,Object> saveImageNews(String data){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			JSONObject jsObjData = JSONObject.parseObject(data);
			JSONObject jsObjImgHead = jsObjData.getJSONObject("imgNewsHead");
			JSONArray jsArray = jsObjData.getJSONArray("imgNewsDataList");
			if(jsArray != null && jsArray.size() > 3){
//				Integer newsId = jsArray.getJSONObject(0).getInteger("newsId");
				Date now = new Date();
				
				CmpyImageNewsHead imageNewsHead = new CmpyImageNewsHead();
				imageNewsHead.setTitle(jsObjImgHead.getString("title"));
				imageNewsHead.setCreateTime(now);
				imageNewsHead.setUpdateTime(now);
				imageNewsHead.setShowTime(jsObjImgHead.getLongValue("showTime"));
				imageNewsHead.setImgUrl1(jsObjImgHead.getString("image1"));
				imageNewsHead.setImgUrl2(jsObjImgHead.getString("image2"));
				imageNewsHead.setImgUrl3(jsObjImgHead.getString("image3"));
				imageNewsHead.setValid(MsgCode.TRUE.getCode());
				imageNewsHead = headService.save(imageNewsHead);
				
				
//				service.deleteImageNewsByNewsId(newsId);
//				service.updateImageNewsValidByNewsId(imageNewsHead.getId(), MsgCode.FALSE.getCode());
				for(int i=0; i<jsArray.size();i++){
					JSONObject jsObj = jsArray.getJSONObject(i);
					CmpyImageNews entity = new CmpyImageNews();
					entity.setCreateTime(now);
					entity.setUpdateTime(now);
					entity.setImage(jsObj.getString("image"));
					entity.setNewsId(imageNewsHead.getId());
					entity.setContent(jsObj.getString("content"));
					entity.setSerialNumber(jsObj.getInteger("serialNumber"));
					entity.setValid( MsgCode.TRUE.getCode());
					service.save(entity);
				}
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
				map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
			}else{
				map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
				map.put(Contants.MSG, "json解析出错。\ndata："+data);
			}
			
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	
	@RequestMapping("queryImageNewsByNewsId")
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
			map.put(Contants.MSG, e);
		}
		return map;
	}

}
