package com.xiaoniu.db.mapper;
import java.util.List;

import com.xiaoniu.db.domain.AdminUserPrivilegesVO;

public interface AdminUserPrivilegesVOMapper {
	public List<AdminUserPrivilegesVO> queryAdminUserPrivileges(Integer userId);
}
