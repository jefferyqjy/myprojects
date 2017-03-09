package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.CarsEntity;

public class CarsDAO extends HibernateDaoSupport {
	public static CarsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CarsDAO) ctx.getBean("carsDAO");
	}

	public void save(CarsEntity cars) {
		try {
			super.getHibernateTemplate().save(cars);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void update(CarsEntity cars) {
		try {
			super.getHibernateTemplate().update(cars);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(CarsEntity cars) {
		try {
			super.getHibernateTemplate().delete(cars);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(int id) {
		try {
			super.getHibernateTemplate().delete(
					getHibernateTemplate().get("com.entity.CarsEntity", id));
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(String id) {
		try {
			super.getHibernateTemplate().delete(
					getHibernateTemplate().get("com.entity.CarsEntity", id));
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> show() {
		try {
			return super.getHibernateTemplate().find("From CarsEntity");
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CarsEntity> hot() {
		List<CarsEntity> list = new ArrayList<CarsEntity>();
		Session session = this.getSessionFactory().openSession();
		String hql = "From CarsEntity where recommend = '是'";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(5);
		list = query.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarsEntity> recommend() {
		List<CarsEntity> list = new ArrayList<CarsEntity>();
		Session session = this.getSessionFactory().openSession();
		String hql = "From CarsEntity where recommend = '是'";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		list = query.list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CarsEntity> special() {
		List<CarsEntity> list = new ArrayList<CarsEntity>();
		Session session = this.getSessionFactory().openSession();
		String hql = "From CarsEntity where special = '是'";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		list = query.list();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByCarno(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where carno = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByImage(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where image = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByCateid(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where cateid = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByBrandid(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where brandid = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByPrice(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where price = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByColour(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where colour = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByDegree(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where degree = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByDisplacement(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where displacement = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryBySpecial(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where special = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByRecommend(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where recommend = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryByContents(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where contents = ?", name);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByCarno(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where carno like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByImage(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where image like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByCateid(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where cateid like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByBrandid(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where brandid like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByPrice(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where price like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByColour(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where colour like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByDegree(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where degree like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByDisplacement(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where displacement like ?",
					"%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeBySpecial(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where special like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByRecommend(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where recommend like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CarsEntity> queryLikeByContents(String name) {
		try {
			return super.getHibernateTemplate().find(
					"From CarsEntity where contents like ?", "%" + name + "%");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public CarsEntity queryById(int carsid) {
		try {
			CarsEntity cars = (CarsEntity) super.getHibernateTemplate().get(
					"com.entity.CarsEntity", carsid);
			return cars;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public CarsEntity queryById(String carsid) {
		try {
			CarsEntity cars = (CarsEntity) super.getHibernateTemplate().get(
					"com.entity.CarsEntity", carsid);
			return cars;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
