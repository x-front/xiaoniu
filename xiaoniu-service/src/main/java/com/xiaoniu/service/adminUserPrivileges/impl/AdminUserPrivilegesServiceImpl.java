package com.xiaoniu.service.adminUserPrivileges.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.db.domain.AdminUserPrivileges;
import com.xiaoniu.db.domain.AdminUserPrivilegesVO;
import com.xiaoniu.db.mapper.AdminUserPrivilegesVOMapper;
import com.xiaoniu.service.adminUserPrivileges.AdminUserPrivilegesService;
import com.xiaoniu.service.base.impl.BaseServiceImpl;

@Service
public class AdminUserPrivilegesServiceImpl extends BaseServiceImpl<AdminUserPrivileges> implements AdminUserPrivilegesService{
	
	@Autowired
	private AdminUserPrivilegesVOMapper adminUserPrivilegesVOMapper;

	@Override
	public List<AdminUserPrivilegesVO> queryAdminUserPrivileges(Integer userId) {
		return adminUserPrivilegesVOMapper.queryAdminUserPrivileges(userId);
	}

}
