package com.base.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

public interface IBaseDAO<T, PK extends Serializable> {
	void add(T entity);

	void delete(T entity);

	void update(T entity);

	T getById(PK pk);

	List<T> getAll();

	void delete(PK pk);

	List<T> getViaHql(String hql);

	List<T> getViaSql(String sql);

	Page<T> getPagers(DefaultQueryCondition<T> condition, Criterion... criterions);
	
	Page<T> getRecord(DefaultQueryCondition condition);
	
	Page<T> getRecord(String column, String order,DefaultQueryCondition condition);

}
