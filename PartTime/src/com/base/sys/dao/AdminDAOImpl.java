package com.base.sys.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.common.util.CommonUtil;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Admin;

@Service
@Transactional
public class AdminDAOImpl extends BaseDAO<Admin, String> {
	@Resource
	private SessionFactory sessionFactory;

	@Override
	protected Class<Admin> getReferenceClass() {
		return Admin.class;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Admin> getRecord(DefaultQueryCondition condition) {
		Admin adm = (Admin) condition.getCondition();
		Criterion cr1 = null;
		Criterion cr2 = null;
		if (CommonUtil.isNotEmpty(adm.getUsername())) {
			cr1 = Restrictions.like("username", adm.getUsername(), MatchMode.ANYWHERE);
		}
		if (CommonUtil.isNotEmpty(adm.getUsertype())) {
			cr2 = Restrictions.eq("usertype", adm.getUsertype());
		}
		return this.getPagers(condition, cr1, cr2);
	}

	
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
