package com.xiaoniu.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.xiaoniu.domain.AdminUserDetailsVO;

public class LoginUserInfoUtil {
	public static Integer getCurrentLoginUserId() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( obj instanceof AdminUserDetailsVO){
			AdminUserDetailsVO user = (AdminUserDetailsVO) obj;
			return user.getAdminUserInfo().getId();
		}else{
			return null;
		}
	}
	
	public static String getCurrentLoginUserName() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( obj instanceof AdminUserDetailsVO){
			AdminUserDetailsVO user = (AdminUserDetailsVO) obj;
			return user.getAdminUserInfo().getUserName();
		}else{
			return null;
		}
	}
}
