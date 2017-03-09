package com.dao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.AdminEntity;
public class AdminDAO extends HibernateDaoSupport {
public static AdminDAO getFromApplicationContext(ApplicationContext ctx) {
return (AdminDAO) ctx.getBean("adminDAO");
}
public void save(AdminEntity admin) {
try {
super.getHibernateTemplate().save(admin);
} catch (RuntimeException re) {
throw re;
}
}
public void update(AdminEntity admin) {
try {
super.getHibernateTemplate().update(admin);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(AdminEntity admin) {
try {
super.getHibernateTemplate().delete(admin);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(int id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.AdminEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
public void delete(String id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.AdminEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> show() {
try {
return super.getHibernateTemplate().find("From AdminEntity");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryByUsername(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where username = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryByPassword(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where password = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryByRealname(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where realname = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryByContact(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where contact = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryLikeByUsername(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where username like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryLikeByPassword(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where password like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryLikeByRealname(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where realname like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<AdminEntity> queryLikeByContact(String name) {
try {
return super.getHibernateTemplate().find("From AdminEntity where contact like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
public AdminEntity queryById(int adminid) {
try {
AdminEntity admin = (AdminEntity) super.getHibernateTemplate().get("com.entity.AdminEntity", adminid);
return admin;
} catch (RuntimeException re) {
throw re;
}
}
public AdminEntity queryById(String adminid) {
try {
AdminEntity admin = (AdminEntity) super.getHibernateTemplate().get("com.entity.AdminEntity", adminid);
return admin;
} catch (RuntimeException re) {
throw re;
}
}
}


