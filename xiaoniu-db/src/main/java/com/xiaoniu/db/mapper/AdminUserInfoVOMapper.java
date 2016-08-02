package com.xiaoniu.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.db.domain.AdminUserInfoVO;

public interface AdminUserInfoVOMapper {
	public List<AdminUserInfoVO> queryAdminUserInfoVO(@Param("id")Integer  id,@Param("userName") String userName,
			@Param("start")Integer start,@Param("limit")Integer limit);
	
	public long queryAdminUserInfoVOTotalCount(@Param("id")Integer  id,@Param("userName") String userName);
}
