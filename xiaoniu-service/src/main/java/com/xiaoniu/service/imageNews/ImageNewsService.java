/**
 * @author zxx
 * @time 2016年11月9日下午3:51:27
 */
package com.xiaoniu.service.imageNews;

import com.xiaoniu.db.domain.CmpyImageNews;
import com.xiaoniu.service.base.BaseService;

/**
 * @author zxx
 * @time 2016年11月9日
 *
 */
public interface ImageNewsService extends BaseService<CmpyImageNews>{
	public void deleteImageNewsByNewsId(Integer newsId)throws Exception;
	
	public void updateImageNewsValidByNewsId(Integer newsId,Integer valid)throws Exception;
}
