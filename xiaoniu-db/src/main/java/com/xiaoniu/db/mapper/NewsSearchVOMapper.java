/**
 * @author zxx
 * @time 2017年7月18日下午5:51:07
 */
package com.xiaoniu.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoniu.db.domain.NewsSearchVO;

/**
 * @author zxx
 * @time 2017年7月18日
 *
 */
public interface NewsSearchVOMapper {
	public List<NewsSearchVO> search(@Param("start")Integer start, @Param("limit")Integer limit, @Param("type")Integer type, @Param("isEn")Integer isEn,
			@Param("isTop")Integer isTop, @Param("keyword")String keyword);
	
	public long searchTotalCount( @Param("type")Integer type, @Param("isEn")Integer isEn,@Param("isTop")Integer isTop, @Param("keyword")String keyword);
}
