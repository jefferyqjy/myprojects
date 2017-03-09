package com.dao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.OrdersEntity;
public class OrdersDAO extends HibernateDaoSupport {
public static OrdersDAO getFromApplicationContext(ApplicationContext ctx) {
return (OrdersDAO) ctx.getBean("ordersDAO");
}
public void save(OrdersEntity orders) {
try {
super.getHibernateTemplate().save(orders);
} catch (RuntimeException re) {
throw re;
}
}
public void update(OrdersEntity orders) {
try {
super.getHibernateTemplate().update(orders);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(OrdersEntity orders) {
try {
super.getHibernateTemplate().delete(orders);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(int id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.OrdersEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
public void delete(String id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.OrdersEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> show() {
try {
return super.getHibernateTemplate().find("From OrdersEntity");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByOrdercode(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where ordercode = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByUsersid(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where usersid = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByCarsid(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where carsid = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByThestart(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where thestart = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByTheend(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where theend = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByPlace(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where place = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByAddress(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where address = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByAddtime(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where addtime = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryByStatus(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where status = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByOrdercode(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where ordercode like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByUsersid(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where usersid like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByCarsid(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where carsid like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByThestart(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where thestart like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByTheend(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where theend like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByPlace(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where place like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByAddress(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where address like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByAddtime(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where addtime like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<OrdersEntity> queryLikeByStatus(String name) {
try {
return super.getHibernateTemplate().find("From OrdersEntity where status like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
public OrdersEntity queryById(int ordersid) {
try {
OrdersEntity orders = (OrdersEntity) super.getHibernateTemplate().get("com.entity.OrdersEntity", ordersid);
return orders;
} catch (RuntimeException re) {
throw re;
}
}
public OrdersEntity queryById(String ordersid) {
try {
OrdersEntity orders = (OrdersEntity) super.getHibernateTemplate().get("com.entity.OrdersEntity", ordersid);
return orders;
} catch (RuntimeException re) {
throw re;
}
}
}


