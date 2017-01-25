package com.chebada.hardware.helloworld.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class BaseHibernateDao {
	
	private Session session;
	
	public abstract SessionFactory getSessionFactory();
	
	public Session getCurrentySession() {
		if(session != null) 
			return session;
		return this.getSessionFactory().getCurrentSession();
	}
	
	public Session getBeanSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public <T> Serializable save(T o) {
		return this.getCurrentySession().save(o);
	}
	
	public <T> void saveAll(Collection<T> collection) {
		for(T t : collection) {
			this.save(t);
		}
	}
	
	public <T> void update(T o) {
		this.getCurrentySession().update(o);
	}
	
}
