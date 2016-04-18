package com.pro.dao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import com.base.common.util.CommonUtil;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.dao.BaseDAO;
import com.pro.entity.Orderproduct;
@Service
@Transactional
public class OrderproductDAOImpl extends BaseDAO<Orderproduct, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Orderproduct> getReferenceClass() {
		return Orderproduct.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Orderproduct> getRecord(DefaultQueryCondition condition) {
		Orderproduct entity = (Orderproduct)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getOrderid())) {
			cr1 = Restrictions.like("orderid",entity.getOrderid(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getOrderstatus())) {
			cr2 = Restrictions.like("orderstatus",entity.getOrderstatus(), MatchMode.ANYWHERE);
		}
		Criterion cr3 = null;
		if(CommonUtil.isNotEmpty(entity.getProductname())) {
			cr3 = Restrictions.like("productname",entity.getProductname(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2,cr3);
	}
}