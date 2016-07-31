/**
 * @author zxx
 * @time 2016年7月31日上午11:05:34
 */
package com.xiaoniu.controller.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zxx
 * @time 2016年7月31日
 *
 */
@Controller
@RequestMapping("/")
public class AuthenticationController {
	
	@RequestMapping("index.html")
	public String indexHtml(){
		return "index";
	}
	
	@RequestMapping("login.html")
	public String loginHtml(){
		return "login";
	}
}
