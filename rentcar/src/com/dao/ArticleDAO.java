package com.dao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.ArticleEntity;
public class ArticleDAO extends HibernateDaoSupport {
public static ArticleDAO getFromApplicationContext(ApplicationContext ctx) {
return (ArticleDAO) ctx.getBean("articleDAO");
}
public void save(ArticleEntity article) {
try {
super.getHibernateTemplate().save(article);
} catch (RuntimeException re) {
throw re;
}
}
public void update(ArticleEntity article) {
try {
super.getHibernateTemplate().update(article);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(ArticleEntity article) {
try {
super.getHibernateTemplate().delete(article);
} catch (RuntimeException re) {
throw re;
}
}
public void delete(int id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.ArticleEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
public void delete(String id) {
try {
super.getHibernateTemplate().delete(getHibernateTemplate().get("com.entity.ArticleEntity", id));
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> show() {
try {
return super.getHibernateTemplate().find("From ArticleEntity");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> queryByTitle(String name) {
try {
return super.getHibernateTemplate().find("From ArticleEntity where title = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> queryByContents(String name) {
try {
return super.getHibernateTemplate().find("From ArticleEntity where contents = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> queryByAddtime(String name) {
try {
return super.getHibernateTemplate().find("From ArticleEntity where addtime = ?", name);
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> queryLikeByTitle(String name) {
try {
return super.getHibernateTemplate().find("From ArticleEntity where title like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> queryLikeByContents(String name) {
try {
return super.getHibernateTemplate().find("From ArticleEntity where contents like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
@SuppressWarnings("unchecked")
public List<ArticleEntity> queryLikeByAddtime(String name) {
try {
return super.getHibernateTemplate().find("From ArticleEntity where addtime like ?", "%" + name + "%");
} catch (RuntimeException re) {
throw re;
}
}
public ArticleEntity queryById(int articleid) {
try {
ArticleEntity article = (ArticleEntity) super.getHibernateTemplate().get("com.entity.ArticleEntity", articleid);
return article;
} catch (RuntimeException re) {
throw re;
}
}
public ArticleEntity queryById(String articleid) {
try {
ArticleEntity article = (ArticleEntity) super.getHibernateTemplate().get("com.entity.ArticleEntity", articleid);
return article;
} catch (RuntimeException re) {
throw re;
}
}
}


