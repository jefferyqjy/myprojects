package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TChe;

/**
 * Data access object (DAO) for domain model class TChe.
 * 
 * @see com.model.TChe
 * @author MyEclipse Persistence Tools
 */

public class TCheDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TCheDAO.class);

	protected void initDao()
	{
		// do nothing
	}

	public void save(TChe transientInstance)
	{
		log.debug("saving TChe instance");
		try
		{
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re)
		{
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TChe persistentInstance)
	{
		log.debug("deleting TChe instance");
		try
		{
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re)
		{
			log.error("delete failed", re);
			throw re;
		}
	}

	public TChe findById(java.lang.Integer id)
	{
		log.debug("getting TChe instance with id: " + id);
		try
		{
			TChe instance = (TChe) getHibernateTemplate().get("com.model.TChe",
					id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TChe instance)
	{
		log.debug("finding TChe instance by example");
		try
		{
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TChe instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TChe as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll()
	{
		log.debug("finding all TChe instances");
		try
		{
			String queryString = "from TChe";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public TChe merge(TChe detachedInstance)
	{
		log.debug("merging TChe instance");
		try
		{
			TChe result = (TChe) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TChe instance)
	{
		log.debug("attaching dirty TChe instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TChe instance)
	{
		log.debug("attaching clean TChe instance");
		try
		{
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TCheDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TCheDAO) ctx.getBean("TCheDAO");
	}
}