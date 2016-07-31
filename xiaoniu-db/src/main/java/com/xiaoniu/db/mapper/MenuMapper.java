package com.xiaoniu.db.mapper;

import java.util.List;

import com.xiaoniu.db.domain.MenuVO;


public interface MenuMapper {
	/**
	 * 查询用户对应的菜单
	 * @param userId
	 * @return
	 */
	public List<MenuVO> queryMenu(Integer userId);
}
