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
import com.pro.entity.Customerinfo;
@Service
@Transactional
public class CustomerinfoDAOImpl extends BaseDAO<Customerinfo, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Customerinfo> getReferenceClass() {
		return Customerinfo.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Customerinfo> getRecord(DefaultQueryCondition condition) {
		Customerinfo entity = (Customerinfo)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getNameid())) {
			cr1 = Restrictions.like("nameid",entity.getNameid(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getName())) {
			cr2 = Restrictions.like("name",entity.getName(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2);
	}
}