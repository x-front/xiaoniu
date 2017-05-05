/**
 * @author zxx
 * @time 2017年4月24日下午2:44:41
 */
package com.xiaoniu.controller.media;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyMedia;

/**
 * @author zxx
 * @time 2017年4月24日
 *
 */
@Controller
@RequestMapping("/secure/media")
public class MediaController extends BaseController<CmpyMedia>{
	@RequestMapping("media.html")
	public ModelAndView mediaHtml(Integer type){
		ModelAndView mv = new ModelAndView("secure/media");
		mv.addObject("type", type);
		return mv;
	}
	
}
