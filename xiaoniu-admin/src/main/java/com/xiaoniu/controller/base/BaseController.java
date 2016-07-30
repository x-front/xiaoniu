package com.xiaoniu.controller.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoniu.db.domain.BaseVO;
import com.xiaoniu.service.base.BaseService;
import com.zxx.common.contants.Contants;
import com.zxx.common.enums.MsgCode;
import com.zxx.common.utils.StringUtils;

public class BaseController<T extends BaseVO> implements InitializingBean{

	@Autowired
	protected BaseService<T> service; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 获取泛型 class 对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getActualTypeClass(){
	 Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<T>)p[0];
        }
        return entityClass;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(T entity){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			service.save(entity);
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.SAVE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.SAVE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			service.delete(id);
			map.put(Contants.RESULT_CODE, MsgCode.DELETE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.DELETE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.DELETE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("batchDelete")
	@ResponseBody
	public Map<String,Object> batchDelete(String strIds){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			service.delete(ids);
			map.put(Contants.RESULT_CODE, MsgCode.DELETE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.DELETE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.DELETE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	
	@RequestMapping("batchUpdateValid")
	@ResponseBody
	public Map<String,Object> batchUpdateValid(String strIds,Integer valid){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer[] ids = StringUtils.convertStringToIds(strIds);
			service.batchUpdateValid(valid, ids);
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_SUCCESS.getCode());
			map.put(Contants.MSG, MsgCode.UPDATE_SUCCESS.getMsg());
		}catch(Exception e){
			map.put(Contants.RESULT_CODE, MsgCode.UPDATE_FAILED.getCode());
			map.put(Contants.MSG, e);
		}
		return map;
	}
	

}
