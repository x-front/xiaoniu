/**
 * @author zxx
 * @time 2017年6月26日下午4:02:06
 */
package com.xiaoniu.controller.imageNews;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
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
	public Map<String,Object> queryImageHeadList(Integer page,Integer  rows, String orderBy,CmpyImageNewsHead entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(orderBy == null || "".equals(orderBy.trim())){
				orderBy = "serial_number asc,id desc";
			}
			PageInfo<CmpyImageNewsHead> pageInfo = headService.queryList(page, rows, orderBy, entity);
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	
}
