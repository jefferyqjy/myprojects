package com.dao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.UsersEntity;
public class UsersDAO extends HibernateDaoSupport {
public static UsersDAO getFromApplicationContext(ApplicationContext ctx) {
return (UsersDAO) ctx.getBean("usersDAO");
}
public void save(UsersEntity users) {
try {
super.getHibernateTemplate().save(users);
} catch (RuntimeException re) {
throw re;
}
}
public void update(UsersEntity users) {
try {
super.getHibernateTemplate().update(users);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(UsersEntity users) {
try {
super.getHibernateTemplate().delete(users);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(int id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.UsersEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
public void delete(String id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.UsersEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> show() {
try {
return super.getHibernateTemplate().find("From UsersEntity");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByUsername(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where username = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByPassword(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where password = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByRealname(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where realname = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryBySex(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where sex = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByBirthday(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where birthday = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByIdcard(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where idcard = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByContact(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where contact = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryByAddress(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where address = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByUsername(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where username like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByPassword(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where password like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByRealname(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where realname like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeBySex(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where sex like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByBirthday(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where birthday like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByIdcard(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where idcard like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByContact(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where contact like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<UsersEntity> queryLikeByAddress(String name) {
try {
return super.getHibernateTemplate().find("From UsersEntity where address like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
public UsersEntity queryById(int usersid) {
try {
UsersEntity users = (UsersEntity) super.getHibernateTemplate().get("com.entity.UsersEntity", usersid);
return users;
} catch (RuntimeException re) {
throw re;
}
}
public UsersEntity queryById(String usersid) {
try {
UsersEntity users = (UsersEntity) super.getHibernateTemplate().get("com.entity.UsersEntity", usersid);
return users;
} catch (RuntimeException re) {
throw re;
}
}
}


