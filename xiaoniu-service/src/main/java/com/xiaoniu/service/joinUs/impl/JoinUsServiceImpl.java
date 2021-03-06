package com.xiaoniu.service.joinUs.impl;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyJoinUs;
import com.xiaoniu.service.base.impl.BaseServiceImpl;
import com.xiaoniu.service.joinUs.JoinUsService;

@Service
public class JoinUsServiceImpl extends BaseServiceImpl<CmpyJoinUs> implements JoinUsService{

	@Override
	public PageInfo<CmpyJoinUs> queryByAddressOrType(int  page,int rows,String orderBy,String address,Integer type,String position) throws Exception {
		final Example example = new Example(getActualTypeClass());
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		if (address != null && !"".equals(address.trim())) {
			criteria.andLike("address", "%" + address + "%");
		}
		if(type != null){
			criteria.andEqualTo("type", type);
		}
		if(position != null && !"".equals(position.trim())){
			criteria.andLike("position", "%" + position + "%");
		}
		PageInfo<CmpyJoinUs> pageInfo = PageHelper.startPage(page, rows, orderBy).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				mapper.selectByExample(example);
			}
		});
		return pageInfo;
	}
	
}
