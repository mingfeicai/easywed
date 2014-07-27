package com.easywed.dao;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mingfei
 * Date: 7/26/14
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDao<T> {
	T findById(Long id);
	List<T> findByIds(Collection<Long> ids);
	void refresh(T entity);
	void save(T entity);
	void saveAll(Collection<T> entity);
	void update(T entity);
	T merge(T entity);
	void updateAll(List<T> entity);
	void delete(T entity);
	List<T> findAll();
	void saveOrUpdate(T entity);
}
