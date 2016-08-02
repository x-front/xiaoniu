package com.xiaoniu.service.adminUserInfo;

import java.util.List;

import com.xiaoniu.db.domain.AdminUserInfo;
import com.xiaoniu.db.domain.AdminUserInfoVO;
import com.xiaoniu.service.base.BaseService;

public interface AdminUserInfoService extends BaseService<AdminUserInfo>{
	public List<AdminUserInfoVO> queryAdminUserInfoVO(Integer id,String userName,Integer page,Integer rows);
	public long queryAdminUserInfoVOTotalCount(Integer id,String userName);
}
