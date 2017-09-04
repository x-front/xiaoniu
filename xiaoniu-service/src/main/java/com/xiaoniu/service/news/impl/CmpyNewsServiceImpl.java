package com.xiaoniu.service.news.impl;

import java.util.List;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoniu.db.domain.CmpyNews;
import com.xiaoniu.db.domain.NewsSearchVO;
import com.xiaoniu.db.mapper.NewsSearchVOMapper;
import com.xiaoniu.service.base.impl.BaseServiceImpl;
import com.xiaoniu.service.news.CmpyNewsService;

@Service
public class CmpyNewsServiceImpl extends BaseServiceImpl<CmpyNews> implements CmpyNewsService{
	
	@Autowired
	private NewsSearchVOMapper searchMapper;
	
	@Override
	public List<NewsSearchVO> search(Integer page, Integer rows, Long totalCount, Integer type, Integer lang,
			Integer isTop, String keyword) {
		List<NewsSearchVO> list = null;
		list =  searchMapper.search((page-1)*rows, rows, type, lang, isTop, keyword,null);
		if(list == null || list.size() == 0){
			list = searchMapper.search((page-1)*rows, rows, type, lang, isTop, null,keyword);
		}
		return list;
	}

	@Override
	public long searchTotalCount(Integer type, Integer lang, Integer isTop, String keyword) {
		long re = 0;
		re =  searchMapper.searchTotalCount(type, lang, isTop, keyword,null);
		if ( re == 0 ){
			re = searchMapper.searchTotalCount(type, lang, isTop, null,keyword);
		}
		return re;
	}

	@Override
	public PageInfo<CmpyNews> queryNewsList(Integer page, Integer rows, String orderBy, final CmpyNews entity) throws Exception {
		PageInfo<CmpyNews> pageInfo = PageHelper.startPage(page, rows, orderBy).doSelectPageInfo(new ISelect() {

			@Override
			public void doSelect() {
				searchMapper.queryNewsList(entity);
			}
		});
		return pageInfo;
	}

}
