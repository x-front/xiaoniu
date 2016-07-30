package com.xiaoniu.service.adminUserPrivileges;

import java.util.List;

import com.xiaoniu.db.domain.AdminUserPrivileges;
import com.xiaoniu.db.domain.AdminUserPrivilegesVO;
import com.xiaoniu.service.base.BaseService;

public interface AdminUserPrivilegesService extends BaseService<AdminUserPrivileges>{
	public List<AdminUserPrivilegesVO> queryAdminUserPrivileges(Integer userId);
}
