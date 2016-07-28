/**
 * @author zxx
 * @time 2016年7月28日下午3:51:24
 */
package com.xiaoniu.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alibaba.fastjson.JSON;

/**
 * @author zxx
 * @time 2016年7月28日
 *
 */
public class BaseVO implements Serializable {



	/**
	 * @author zxx
	 * @time 2016年7月28日
	 * 
	 */
	private static final long serialVersionUID = 3797578617323243381L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	
	@Column(name="create_time")
	protected Date createTime;
	
	@Column(name="update_time")
	protected Date updateTime;
	
	@Column(name="valid")
	protected Integer valid;

	

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public <T> T convertExt(Class<T> clazz){
		String json = JSON.toJSONString(this);
		T t =  JSON.parseObject(json,clazz);
		return t;
	}


	@Override
	public final boolean equals(Object obj) {
		if(obj instanceof BaseVO&&this.id!=null){
			return this.id.equals(((BaseVO)obj).getId());
		}
		return super.equals(obj);
	}
	
}
