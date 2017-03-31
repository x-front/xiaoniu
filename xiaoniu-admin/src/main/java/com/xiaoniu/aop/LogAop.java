/**
 * @author zxx
 * @time 2017年3月31日下午4:00:15
 */
package com.xiaoniu.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xiaoniu.utils.LoginUserInfoUtil;


/**
 * @author zxx
 * @time 2017年3月31日
 *
 */
@Component
@Aspect
public class LogAop {
	private Logger log = Logger.getLogger(LogAop.class);
	@Before("execution(* com.xiaoniu.controller..*.*(..)) and !execution(* com.xiaoniu.controller.AliyunOSSController..*.*(..))")
	public void keepEveryRequireRecord(JoinPoint joinPoint){
		StringBuilder sb = new StringBuilder();
		String method = joinPoint.getSignature().getName();
		String username = LoginUserInfoUtil.getCurrentLoginUserName();
		String targetPackage = joinPoint.getTarget().getClass().getName();
		sb.append(username);
		sb.append(" request ");
		sb.append(targetPackage);
		sb.append('.');
		sb.append(method);
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		Enumeration<String> params = request.getParameterNames();
		if(params != null){
			sb.append(". params:\n");
			while(params.hasMoreElements()){
				String param = params.nextElement();
				String value = request.getParameter(param);
				sb.append(param);
				sb.append('=');
				sb.append(value);
				sb.append('\n');
			}
		}
		log.info(sb.toString());
	}
}
