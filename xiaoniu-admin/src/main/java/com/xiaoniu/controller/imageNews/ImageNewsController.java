/**
 * @author zxx
 * @time 2016年11月9日下午3:57:52
 */
package com.xiaoniu.controller.imageNews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoniu.controller.base.BaseController;
import com.xiaoniu.db.domain.CmpyImageNews;

/**
 * @author zxx
 * @time 2016年11月9日
 *
 */
@Controller
@RequestMapping("/secure/imageNews")
public class ImageNewsController extends BaseController<CmpyImageNews>{
	
	@RequestMapping("imageNews.html")
	public String imageNewsHtml(){
		return "secure/imageNews";
	}

}
