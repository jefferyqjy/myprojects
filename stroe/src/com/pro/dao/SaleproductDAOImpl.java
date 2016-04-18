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
import com.pro.entity.Saleproduct;
@Service
@Transactional
public class SaleproductDAOImpl extends BaseDAO<Saleproduct, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Saleproduct> getReferenceClass() {
		return Saleproduct.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Saleproduct> getRecord(DefaultQueryCondition condition) {
		Saleproduct entity = (Saleproduct)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getProductname())) {
			cr1 = Restrictions.like("productname",entity.getProductname(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getOrderstatus())) {
			cr2 = Restrictions.like("orderstatus",entity.getOrderstatus(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2);
	}
}