package com.xiaoniu.controller.joinUs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyJoinUs;
import com.xiaoniu.service.joinUs.JoinUsService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;

@RequestMapping("joinUs")
@Controller
public class JoinUsController {
	@Autowired
	private JoinUsService service;
	
	private Logger log = Logger.getLogger(JoinUsController.class);
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer  rows,CmpyJoinUs entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			entity.setValid(MsgCode.TRUE.getCode());
			PageInfo<CmpyJoinUs> pageInfo = service.queryList(page, rows, " serial_number desc,id desc ", entity);
			
			List<CmpyJoinUs> list = pageInfo.getList();
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
	
	@RequestMapping("queryJoinUsList")
	@ResponseBody
	public Map<String,Object> queryList(Integer page,Integer  rows, String orderBy,CmpyJoinUs entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(page == null || page < 0){
				page = 1;
			}
			if(rows == null || rows < 1 || rows > 20){
				rows = 20;
			}
			if(orderBy == null || "".equals(orderBy.trim())){
				orderBy = " id desc";
			}
			PageInfo<CmpyJoinUs> pageInfo = service.queryByAddressOrType(page, rows, " serial_number desc,id desc ", entity.getAddress(), entity.getType(),entity.getPosition());
			
			List<CmpyJoinUs> list = pageInfo.getList();
			if(list != null){
				for(int i=0;i<list.size();i++){
					list.get(i).setContent("");
				}
			}
			
			map.put(Contants.TOTAL, pageInfo.getTotal());
			map.put(Contants.ROWS, list);
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.FALSE.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Map<String,Object> find(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			map.put("entity", service.selectByKey(id));
			map.put(Contants.RESULT_CODE, MsgCode.SUCCESS.getCode());
		}catch(Exception e){
			log.error(e);
			map.put(Contants.RESULT_CODE, MsgCode.FAILED.getCode());
			map.put(Contants.MSG, MsgCode.FAILED.getMsg());
		}
		return map;
	}
}
