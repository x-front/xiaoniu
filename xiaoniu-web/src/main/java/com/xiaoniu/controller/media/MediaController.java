/**
 * @author zxx
 * @time 2017年7月16日下午7:58:47
 */
package com.xiaoniu.controller.media;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyMedia;
import com.xiaoniu.service.media.MediaService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

/**
 * @author zxx
 * @time 2017年7月16日
 *
 */
@Controller
@RequestMapping("/media")
public class MediaController {
	@Autowired
	private MediaService service; 
	private Logger log = Logger.getLogger(MediaController.class);
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer  rows,CmpyMedia entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			entity.setValid(MsgCode.TRUE.getCode());
			PageInfo<CmpyMedia> pageInfo = service.queryList(page, rows, " serial_number desc,id desc ", entity);
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, pageInfo.getList());
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
}
