package com.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TContract;

/**
 * Data access object (DAO) for domain model class TAdmin.
 * 
 * @see com.model.TAdmin
 * @author MyEclipse Persistence Tools
 */

public class TContractDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TContractDAO.class);

	// property constants
	public static final String ADMIN_ID = "adminId";
	
	public static final String USER_ID = "userId";
	
	public static final String STATUS = "status";
	
	public static final String PARENT_CONTRACT_ID = "parentContractId";

	protected void initDao() {
		// do nothing
	}

	public void save(TContract transientInstance) {
		log.debug("saving TContract instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TContract persistentInstance) {
		log.debug("deleting TContract instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TContract findById(java.lang.Integer id) {
		log.debug("getting TContract instance with id: " + id);
		try {
			TContract instance = (TContract) getHibernateTemplate().get(
					"com.model.TContract", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TContract instance) {
		log.debug("finding TContract instance by example");
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
		log.debug("finding TContract instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TContract as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdminId(Object adminId) {
		return findByProperty(ADMIN_ID, adminId);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}
	
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	
	public List findByParentContractId(Object parentContractId) {
		return findByProperty(PARENT_CONTRACT_ID, parentContractId);
	}

	public List findAll() {
		log.debug("finding all TContract instances");
		try {
			String queryString = "from TContract";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TContract merge(TContract detachedInstance) {
		log.debug("merging TContract instance");
		try {
			TContract result = (TContract) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TContract instance) {
		log.debug("attaching dirty TContract instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TContract instance) {
		log.debug("attaching clean TContract instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TContractDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TContractDAO) ctx.getBean("TContractDAO");
	}
}