/**
 * @author zxx
 * @time 2017年7月18日下午5:51:07
 */
package com.xiaoniu.db.mapper;

import java.util.List;

import com.xiaoniu.db.domain.CmpyNews;
import org.apache.ibatis.annotations.Param;

import com.xiaoniu.db.domain.NewsSearchVO;

/**
 * @author zxx
 * @time 2017年7月18日
 *
 */
public interface NewsSearchVOMapper {
	public List<NewsSearchVO> search(@Param("start")Integer start, @Param("limit")Integer limit, @Param("type")Integer type, @Param("lang")Integer lang,
			@Param("isTop")Integer isTop, @Param("title")String title, @Param("summary")String summary);
	
	public long searchTotalCount( @Param("type")Integer type, @Param("lang")Integer lang,@Param("isTop")Integer isTop, @Param("title")String title, @Param("summary")String summary);

	public List<CmpyNews> queryNewsList(CmpyNews entity);
}
