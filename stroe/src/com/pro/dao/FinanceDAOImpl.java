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
import com.pro.entity.Finance;
@Service
@Transactional
public class FinanceDAOImpl extends BaseDAO<Finance, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Finance> getReferenceClass() {
		return Finance.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Finance> getRecord(DefaultQueryCondition condition) {
		Finance entity = (Finance)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getFinancetype())) {
			cr1 = Restrictions.like("financetype",entity.getFinancetype(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getSupname())) {
			cr2 = Restrictions.like("supname",entity.getSupname(), MatchMode.ANYWHERE);
		}
		Criterion cr3 = null;
		if(CommonUtil.isNotEmpty(entity.getCustname())) {
			cr3 = Restrictions.like("custname",entity.getCustname(), MatchMode.ANYWHERE);
		}
		Criterion cr4 = null;
		/*if(CommonUtil.isNotEmpty(entity.getDatefin())) {
			cr4 = Restrictions.like("datefin",entity.getDatefin(), MatchMode.ANYWHERE);
		}*/
		if(entity.getDatefin() != null) {
			cr4 = Restrictions.eq("datefin",entity.getDatefin());
		}
		return this.getPagers(condition,cr1,cr2,cr3,cr4);
	}
}