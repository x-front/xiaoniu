/**
 * @author zxx
 * @time 2017年4月24日下午2:44:41
 */
package com.xiaoniu.controller.media;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyMedia;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;

/**
 * @author zxx
 * @time 2017年4月24日
 *
 */
@Controller
@RequestMapping("/secure/media")
public class MediaController extends BaseController<CmpyMedia>{
	@RequestMapping("media.html")
	public ModelAndView mediaHtml(Integer type){
		ModelAndView mv = new ModelAndView("secure/media");
		mv.addObject("type", type);
		return mv;
	}
	
	@RequestMapping("batchUpdateMediaLang")
	@ResponseBody
	public Map<String,Object> batchUpdateMediaLang(Integer lang,String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			for (int i = 0; i < ids.length; i++) {
				CmpyMedia entity  = new CmpyMedia();
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
