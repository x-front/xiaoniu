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

import com.xiaoniu.service.adminUserInfo.AdminUserInfoService;


@Component
public class AdminUserDetailsService implements UserDetailsService{
	private Logger log = Logger.getLogger(AdminUserDetailsService.class);
	@Autowired
	private AdminUserInfoService adminUserInfoService;
	@Autowired
	private UserPrivilegeService userPrivilegeService;
	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		if(userName == null || "".equals(userName.trim())){
			throw new UsernameNotFoundException("用户名不能为空");
		}
		try{
			//查询用户信息,这里的userName其实就是登录帐号，也就是对应loginCode
			List<AdminUserInfoVO> adminUserInfoList = adminUserInfoService.queryAdminUser(null, userName,null, null);
			if ( adminUserInfoList == null ){
				throw new UsernameNotFoundException("没有找到该用户信息");
			}
			if ( adminUserInfoList.size() > 1 ) {
				throw new UsernameNotFoundException("存在多个同名帐号");
			}
			
			//查询用户对应的权限 
			List<UserPrivilegeVO> userPrivileges = userPrivilegeService.queryUserPrivilege(null, adminUserInfoList.get(0).getId(), null, Tag.TRUE);
			if ( userPrivileges == null){
				throw new UsernameNotFoundException("查询用户权限出错");
			}
			if ( userPrivileges.size() < 1) {
				throw new UsernameNotFoundException("用户没有权限");
			}
			
			//查询角色
			List<UserRoleVO> roles = userRoleService.queryUserRole(null, adminUserInfoList.get(0).getId(), null, Tag.TRUE);
			if ( roles == null ) {
				throw new UsernameNotFoundException("查询用户角色出错");
			}
			if ( roles.size() < 1 ) {
				throw new UsernameNotFoundException("该用户没有对应的角色");
			}
			
			//构造认证信息
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for ( UserPrivilegeVO userPrivilege : userPrivileges) {
				authorities.add(new SimpleGrantedAuthority(userPrivilege.getPvgName()));
			}
			for ( UserRoleVO role : roles) {
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
