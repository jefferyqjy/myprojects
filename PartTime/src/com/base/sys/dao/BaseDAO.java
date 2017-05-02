package com.base.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import com.base.log.log4j.util.LogFactory;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

@Transactional
public abstract class BaseDAO<T, PK extends Serializable> implements IBaseDAO {

	public Page<T> getPagers(DefaultQueryCondition condition, Criterion... criterions) {
		Page<T> pager = null;
		try {
			Criteria criteria = this.getSession().createCriteria(this.getReferenceClass());
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
			criteria.setFirstResult(condition.getFirstResult());
			criteria.setMaxResults(condition.getPageSize());
			
			List<T> result = criteria.list();
			pager = new Page<T>(result, totalCount, condition.getPageIndex(), condition.getPageSize());
		} catch (Exception e) {
			LogFactory.getLogger().error(e);
		}
		return pager;
	}

	public Page<T> getPagers(String column, String order, DefaultQueryCondition condition, Criterion... criterions) {
		Page<T> pager = null;
		try {
			Criteria criteria = this.getSession().createCriteria(this.getReferenceClass());
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
			criteria.setFirstResult(condition.getFirstResult());
			criteria.setMaxResults(condition.getPageSize());
			
			/*Criteria cr = session.createCriteria(Student.class);cr.add(Restrictions.like(“name”, “F%”);
			cr.addOrder(Order.asc(“name”));List list = cr.list();Student stu = (Student)list.get(0);

			调用Order.asc的方法应是Criteria的addOrder()方法。*/
			if(column != null && column != "" && order != null && order != ""){
				if(order.equalsIgnoreCase("DESC")){
					criteria.addOrder(Order.desc(column));
				}else{
					criteria.addOrder(Order.asc(column));
				}
			}
			
			
			
			
			List<T> result = criteria.list();
			pager = new Page<T>(result, totalCount, condition.getPageIndex(), condition.getPageSize());
		} catch (Exception e) {
			LogFactory.getLogger().error(e);
		}
		return pager;
	}
	
	public List<T> getViaHql(String hql) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<T> getViaSql(String sql) {
		//return null;
		List list = this.getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		return list;
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
		return (T) this.getSessionFactory().getCurrentSession().get(this.getReferenceClass(), key);

	}

	public void update(Object obj) {
		this.getSessionFactory().getCurrentSession().update(obj);

	}

	public Session getSession() {
		return this.getSessionFactory().getCurrentSession();
	}
}
