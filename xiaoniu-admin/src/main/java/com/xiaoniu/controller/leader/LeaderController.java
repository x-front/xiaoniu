package com.xiaoniu.controller.leader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyLeader;

@Controller
@RequestMapping("/secure/leader")
public class LeaderController extends BaseController<CmpyLeader>{
	@RequestMapping("leader.html")
	public String leaderHtml(){
		return "secure/leader";
	}
}
