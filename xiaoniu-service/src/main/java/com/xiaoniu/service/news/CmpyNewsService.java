package com.xiaoniu.service.news;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.xiaoniu.db.domain.CmpyNews;
import com.xiaoniu.db.domain.NewsSearchVO;
import com.xiaoniu.service.base.BaseService;

public interface CmpyNewsService extends BaseService<CmpyNews>{
	public List<NewsSearchVO> search(Integer page,Integer  rows,Long totalCount,Integer type,Integer lang,Integer isTop,String keyword);
	public long searchTotalCount(Integer type,Integer lang,Integer isTop,String keyword);

	/**
	 * 分页查询。
	 * @param page
	 * @param rows
	 * @param orderBy
	 * @param entity title和summary采用like
	 * @return
	 * @throws Exception
	 */
	public PageInfo<CmpyNews> queryNewsList(Integer page, Integer rows, String orderBy, final CmpyNews entity) throws Exception;
}
