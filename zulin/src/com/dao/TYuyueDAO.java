package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TYuyue;

/**
 * A data access object (DAO) providing persistence and search support for
 * TYuyue entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.model.TYuyue
 * @author MyEclipse Persistence Tools
 */

public class TYuyueDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TYuyueDAO.class);
	// property constants
	public static final String HUODONG_ID = "huodongId";
	public static final String XINGMING = "xingming";
	public static final String LIANXI = "lianxi";
	public static final String ZHUZHI = "zhuzhi";

	protected void initDao() {
		// do nothing
	}

	public void save(TYuyue transientInstance) {
		log.debug("saving TYuyue instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TYuyue persistentInstance) {
		log.debug("deleting TYuyue instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TYuyue findById(java.lang.Integer id) {
		log.debug("getting TYuyue instance with id: " + id);
		try {
			TYuyue instance = (TYuyue) getHibernateTemplate().get(
					"com.model.TYuyue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TYuyue instance) {
		log.debug("finding TYuyue instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TYuyue instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TYuyue as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHuodongId(Object huodongId) {
		return findByProperty(HUODONG_ID, huodongId);
	}

	public List findByXingming(Object xingming) {
		return findByProperty(XINGMING, xingming);
	}

	public List findByLianxi(Object lianxi) {
		return findByProperty(LIANXI, lianxi);
	}

	public List findByZhuzhi(Object zhuzhi) {
		return findByProperty(ZHUZHI, zhuzhi);
	}

	public List findAll() {
		log.debug("finding all TYuyue instances");
		try {
			String queryString = "from TYuyue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TYuyue merge(TYuyue detachedInstance) {
		log.debug("merging TYuyue instance");
		try {
			TYuyue result = (TYuyue) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TYuyue instance) {
		log.debug("attaching dirty TYuyue instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TYuyue instance) {
		log.debug("attaching clean TYuyue instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TYuyueDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TYuyueDAO) ctx.getBean("TYuyueDAO");
	}
}