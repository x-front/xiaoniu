package com.xiaoniu.service.menu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.db.domain.MenuVO;
import com.xiaoniu.db.mapper.MenuMapper;
import com.xiaoniu.service.menu.MenuService;


@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<MenuVO> queryMenu(Integer userId)throws Exception{
		List<MenuVO> list = menuMapper.queryMenu(userId);
		return list;
	}
}
