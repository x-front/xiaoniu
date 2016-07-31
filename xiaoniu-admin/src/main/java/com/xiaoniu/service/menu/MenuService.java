package com.xiaoniu.service.menu;

import java.util.List;

import com.xiaoniu.db.domain.MenuVO;


public interface MenuService {
	public List<MenuVO> queryMenu(Integer userId)throws Exception;
}
