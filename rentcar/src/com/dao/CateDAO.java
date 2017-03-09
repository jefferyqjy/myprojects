package com.dao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.CateEntity;
public class CateDAO extends HibernateDaoSupport {
public static CateDAO getFromApplicationContext(ApplicationContext ctx) {
return (CateDAO) ctx.getBean("cateDAO");
}
public void save(CateEntity cate) {
try {
super.getHibernateTemplate().save(cate);
} catch (RuntimeException re) {
throw re;
}
}
public void update(CateEntity cate) {
try {
super.getHibernateTemplate().update(cate);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(CateEntity cate) {
try {
super.getHibernateTemplate().delete(cate);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(int id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.CateEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
public void delete(String id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.CateEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<CateEntity> show() {
try {
return super.getHibernateTemplate().find("From CateEntity");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<CateEntity> queryByCatename(String name) {
try {
return super.getHibernateTemplate().find("From CateEntity where catename = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<CateEntity> queryLikeByCatename(String name) {
try {
return super.getHibernateTemplate().find("From CateEntity where catename like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
public CateEntity queryById(int cateid) {
try {
CateEntity cate = (CateEntity) super.getHibernateTemplate().get("com.entity.CateEntity", cateid);
return cate;
} catch (RuntimeException re) {
throw re;
}
}
public CateEntity queryById(String cateid) {
try {
CateEntity cate = (CateEntity) super.getHibernateTemplate().get("com.entity.CateEntity", cateid);
return cate;
} catch (RuntimeException re) {
throw re;
}
}
}


