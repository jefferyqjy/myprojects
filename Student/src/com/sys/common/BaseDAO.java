package com.sys.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.admin.entity.SysAdmin;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
@Transactional
public abstract class BaseDAO<T,PK extends Serializable> implements IBaseDAO {
	
	public List getViaHql(String hql) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list();
	}
	public List getViaSql(String sql) {
		return null;
	}
	protected abstract SessionFactory getSessionFactory();
	protected abstract Class<T> getReferenceClass();
	public void add(Object obj) {
		getSessionFactory().getCurrentSession().persist(obj);
		
	}

	public void delete(Object obj) {
		getSessionFactory().getCurrentSession().delete(obj);
	}


	public void delete(Serializable key) {
		T findObj = (T) getSessionFactory().getCurrentSession().get(getReferenceClass(), key);
		this.getSessionFactory().getCurrentSession().delete(findObj);
		
	}


	public List<T> getAll() {
		StringBuffer sb = new StringBuffer();
		sb.append("from ").append(this.getReferenceClass().getName()).append(" where 1=1");
		Query query = this.getSessionFactory().getCurrentSession().createQuery(sb.toString());
		return query.list();
	}


	public T getById(Serializable key) {
		return (T)this.getSessionFactory().getCurrentSession().get(this.getReferenceClass(), key);
		
	}

	public void update(Object obj) {
		this.getSessionFactory().getCurrentSession().update(obj);
		
	}
	
	public int getRecordsNum() {
		String hql = "select count(*) from "+this.getReferenceClass().getName();
		int total = ((Long)this.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult()).intValue();
		return total;
	}
	
	public int getRevoedsNum(String where) {
		String hql = "select count(*) from "+this.getReferenceClass().getName() + " where " +where;
		int total = ((Long)this.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult()).intValue();
		return total;
	}
	
	public Page<T> getRecord(DefaultQueryCondition condition) {
		return null;
	}
}
