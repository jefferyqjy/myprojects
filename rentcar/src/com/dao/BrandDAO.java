package com.dao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.BrandEntity;
public class BrandDAO extends HibernateDaoSupport {
public static BrandDAO getFromApplicationContext(ApplicationContext ctx) {
return (BrandDAO) ctx.getBean("brandDAO");
}
public void save(BrandEntity brand) {
try {
super.getHibernateTemplate().save(brand);
} catch (RuntimeException re) {
throw re;
}
}
public void update(BrandEntity brand) {
try {
super.getHibernateTemplate().update(brand);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(BrandEntity brand) {
try {
super.getHibernateTemplate().delete(brand);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(int id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.BrandEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
public void delete(String id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.BrandEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<BrandEntity> show() {
try {
return super.getHibernateTemplate().find("From BrandEntity");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<BrandEntity> queryByBrandname(String name) {
try {
return super.getHibernateTemplate().find("From BrandEntity where brandname = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<BrandEntity> queryByImage(String name) {
try {
return super.getHibernateTemplate().find("From BrandEntity where image = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<BrandEntity> queryLikeByBrandname(String name) {
try {
return super.getHibernateTemplate().find("From BrandEntity where brandname like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<BrandEntity> queryLikeByImage(String name) {
try {
return super.getHibernateTemplate().find("From BrandEntity where image like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
public BrandEntity queryById(int brandid) {
try {
BrandEntity brand = (BrandEntity) super.getHibernateTemplate().get("com.entity.BrandEntity", brandid);
return brand;
} catch (RuntimeException re) {
throw re;
}
}
public BrandEntity queryById(String brandid) {
try {
BrandEntity brand = (BrandEntity) super.getHibernateTemplate().get("com.entity.BrandEntity", brandid);
return brand;
} catch (RuntimeException re) {
throw re;
}
}
}


