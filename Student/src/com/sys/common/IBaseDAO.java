package com.sys.common;

import java.io.Serializable;
import java.util.List;

import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;

public interface IBaseDAO<T,PK extends Serializable> {
	public void add(T obj);
	public void delete(T obj);
	public void update(T obj);
	public T getById(PK key);
	public List<T> getAll();
	public void delete(PK key);
	public List<T> search(DefaultQueryCondition<T> condition);
	public int getRecordsNum();
	public Page<T> getRecord(DefaultQueryCondition<T> condition);
	public List<T> getViaHql(String hql);
	public List<T> getViaSql(String sql);
	public int getRevoedsNum(String where);
}
