package com.xiaoniu.service.base;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.xiaoniu.db.domain.BaseVO;

public interface BaseService <T extends BaseVO> extends InitializingBean {

    public T selectByKey(Integer id);

    public T save(T entity);
    
    public List<T> select(T entity);
    
    public T oneSelect(T entity);

    public int delete(Integer id);
    
    public int delete(Integer ...id);

    public int updateAll(T entity);
    
    public int selectCount(T entity);

    public int updateNotNull(T entity);

    public List<T> selectAll();
    
    public List<T> selectByExample(Object example);
}
