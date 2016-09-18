package com.xiaoniu.service.joinUs;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyJoinUs;
import com.xiaoniu.service.base.BaseService;

public interface JoinUsService extends BaseService<CmpyJoinUs>{
	public PageInfo<CmpyJoinUs> queryByAddressOrType(int  page,int rows,String orderBy,String address,Integer type)throws Exception;
}
