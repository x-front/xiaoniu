package com.xiaoniu.service.news.impl;

import java.util.List;

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
	public List<NewsSearchVO> search(Integer page, Integer rows, Long totalCount, Integer type, Integer isEn,
			Integer isTop, String keyword) {
		
		return searchMapper.search((page-1)*rows, rows, type, isEn, isTop, keyword);
	}

	@Override
	public long searchTotalCount(Integer type, Integer isEn, Integer isTop, String keyword) {
		return searchMapper.searchTotalCount(type, isEn, isTop, keyword);
	}

}
