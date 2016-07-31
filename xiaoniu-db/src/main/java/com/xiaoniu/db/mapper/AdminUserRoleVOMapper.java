package com.xiaoniu.db.mapper;
import java.util.List;

import com.xiaoniu.db.domain.AdminUserRoleVO;
public interface AdminUserRoleVOMapper {
	public List<AdminUserRoleVO> queryAdminUserRole(Integer userId);
}
