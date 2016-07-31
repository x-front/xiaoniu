package com.xiaoniu.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.xiaoniu.db.domain.AdminUserInfo;
import com.xiaoniu.db.domain.AdminUserPrivilegesVO;
import com.xiaoniu.db.domain.AdminUserRoleVO;
import com.xiaoniu.domain.AdminUserDetailsVO;
import com.xiaoniu.service.adminUserInfo.AdminUserInfoService;
import com.xiaoniu.service.adminUserPrivileges.AdminUserPrivilegesService;
import com.xiaoniu.service.adminUserRole.AdminUserRoleService;
import com.zxx.common.enums.MsgCode;


@Component
public class AdminUserDetailsService implements UserDetailsService{
	
	private Logger log = Logger.getLogger(AdminUserDetailsService.class);
	@Autowired
	private AdminUserInfoService adminUserInfoService;
	@Autowired
	private AdminUserPrivilegesService userPrivilegeService;
	@Autowired
	private AdminUserRoleService userRoleService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		if(userName == null || "".equals(userName.trim())){
			throw new UsernameNotFoundException("用户名不能为空");
		}
		try{
			//查询用户信息,这里的userName其实就是登录帐号，也就是对应loginCode
			AdminUserInfo adminUserInfoVO = new AdminUserInfo();
			adminUserInfoVO.setLoginCode(userName);
			adminUserInfoVO.setValid(MsgCode.TRUE.getCode());
			List<AdminUserInfo> adminUserInfoList = adminUserInfoService.select(adminUserInfoVO);
			if ( adminUserInfoList == null ){
				throw new UsernameNotFoundException("没有找到该用户信息");
			}
			if ( adminUserInfoList.size() != 1 ) {
				throw new UsernameNotFoundException("账户异常");
			}
			
			//查询用户对应的权限 
			List<AdminUserPrivilegesVO> userPrivileges = userPrivilegeService.queryAdminUserPrivileges(adminUserInfoList.get(0).getId());
			if ( userPrivileges == null){
				throw new UsernameNotFoundException("查询用户权限出错");
			}
			if ( userPrivileges.size() < 1) {
				throw new UsernameNotFoundException("用户没有权限");
			}
			
			//查询角色
			
			List<AdminUserRoleVO> roles = userRoleService.queryAdminUserRole(adminUserInfoList.get(0).getId());
			if ( roles == null ) {
				throw new UsernameNotFoundException("查询用户角色出错");
			}
			if ( roles.size() < 1 ) {
				throw new UsernameNotFoundException("该用户没有对应的角色");
			}
			
			//构造认证信息
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for ( AdminUserPrivilegesVO userPrivilege : userPrivileges) {
				authorities.add(new SimpleGrantedAuthority(userPrivilege.getPvgName()));
			}
			for ( AdminUserRoleVO role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
			
			AdminUserDetailsVO adminUserDetailsVO = new AdminUserDetailsVO(adminUserInfoList.get(0),authorities);
			return adminUserDetailsVO;
		}catch(Exception e){
			if(log.isDebugEnabled()){
				log.info(e.getMessage());
			}
			throw new UsernameNotFoundException("查找用户信息失败");
		}
	}
}
