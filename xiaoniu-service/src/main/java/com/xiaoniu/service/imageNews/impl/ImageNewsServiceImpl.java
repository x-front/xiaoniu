/**
 * @author zxx
 * @time 2016年11月9日下午3:52:44
 */
package com.xiaoniu.service.imageNews.impl;

import org.springframework.stereotype.Service;

import com.xiaoniu.db.domain.CmpyImageNews;
import com.xiaoniu.service.base.impl.BaseServiceImpl;
import com.xiaoniu.service.imageNews.ImageNewsService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author zxx
 * @time 2016年11月9日
 *
 */
@Service
public class ImageNewsServiceImpl extends BaseServiceImpl<CmpyImageNews> implements ImageNewsService{

	/* (non-Javadoc)
	 * @see com.xiaoniu.service.imageNews.ImageNewsService#deleteImageNewsByNewsId(java.lang.Integer)
	 * @author zxx
	 * @time 2016年12月18日
	 */
	@Override
	public void deleteImageNewsByNewsId(Integer newsId) throws Exception {
		if(newsId == null){
			return;
		}
		CmpyImageNews entity = new CmpyImageNews();
		entity.setNewsId(newsId);
		mapper.delete(entity);
	}

	/* (non-Javadoc)
	 * @see com.xiaoniu.service.imageNews.ImageNewsService#updateImageNewsValidByNewsId(java.lang.Integer)
	 * @author zxx
	 * @time 2016年12月18日
	 */
	@Override
	public void updateImageNewsValidByNewsId(Integer newsId,Integer valid) throws Exception {
		if(newsId == null){
			return;
		}
		final Example example = new Example(getActualTypeClass());
		Criteria cr = example.createCriteria();
		cr.andCondition("news_id", newsId);
		CmpyImageNews entity = new CmpyImageNews();
		entity.setNewsId(newsId);
		entity.setValid(valid);
		mapper.updateByExampleSelective(entity, example);
	}

}
