package com.xiaoniu.service.adminUserRole.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.db.domain.AdminUserRole;
import com.xiaoniu.db.domain.AdminUserRoleVO;
import com.xiaoniu.db.mapper.AdminUserRoleVOMapper;
import com.xiaoniu.service.adminUserRole.AdminUserRoleService;
import com.xiaoniu.service.base.impl.BaseServiceImpl;

@Service
public class AdminUserRoleServiceImpl extends BaseServiceImpl<AdminUserRole> implements AdminUserRoleService{

	@Autowired
	private AdminUserRoleVOMapper adminUserRoleVOMapper;
	
	/* (non-Javadoc)
	 * @see com.xiaoniu.service.adminUserRole.AdminUserRoleService#queryAdminUserRole(java.lang.Integer)
	 * @author zxx
	 * @time 2016年7月31日
	 */
	@Override
	public List<AdminUserRoleVO> queryAdminUserRole(Integer userId) {
		return adminUserRoleVOMapper.queryAdminUserRole(userId);
	}

}
