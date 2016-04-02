package com.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TFund;

/**
 * Data access object (DAO) for domain model class TAdmin.
 * 
 * @see com.model.TAdmin
 * @author MyEclipse Persistence Tools
 */

public class TFundDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TFundDAO.class);

	// property constants
	public static final String CONTRACT_ID = "contractId";
	
	public static final String STATUS = "status";
	
	public static final String RECEIPT_NUMBER = "receiptNumber";

	protected void initDao() {
		// do nothing
	}

	public void save(TFund transientInstance) {
		log.debug("saving TFund instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TFund persistentInstance) {
		log.debug("deleting TFund instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TFund findById(java.lang.Integer id) {
		log.debug("getting TFund instance with id: " + id);
		try {
			TFund instance = (TFund) getHibernateTemplate().get(
					"com.model.TFund", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TFund instance) {
		log.debug("finding TFund instance by example");
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
		log.debug("finding TFund instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TFund as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByReceiptNumber(Object receiptNumber) {
		return findByProperty(RECEIPT_NUMBER, receiptNumber);
	}
	
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	
	public List findByContractId(Object contractId) {
		return findByProperty(CONTRACT_ID, contractId);
	}

	public List findAll() {
		log.debug("finding all TFund instances");
		try {
			String queryString = "from TFund";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TFund merge(TFund detachedInstance) {
		log.debug("merging TFund instance");
		try {
			TFund result = (TFund) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TFund instance) {
		log.debug("attaching dirty TContract instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TFund instance) {
		log.debug("attaching clean TFund instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TFundDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TFundDAO) ctx.getBean("TFundDAO");
	}
}