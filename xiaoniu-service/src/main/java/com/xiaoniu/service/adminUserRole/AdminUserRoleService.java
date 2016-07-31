package com.xiaoniu.service.adminUserRole;

import java.util.List;

import com.xiaoniu.db.domain.AdminUserRole;
import com.xiaoniu.db.domain.AdminUserRoleVO;
import com.xiaoniu.service.base.BaseService;

public interface AdminUserRoleService extends BaseService<AdminUserRole>{
	public List<AdminUserRoleVO> queryAdminUserRole(Integer userId);
}
