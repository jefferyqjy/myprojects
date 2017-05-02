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
import com.pro.entity.ApplyInfo;
@Service
@Transactional
public class ApplyInfoDAOImpl extends BaseDAO<ApplyInfo, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<ApplyInfo> getReferenceClass() {
		return ApplyInfo.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<ApplyInfo> getRecord(DefaultQueryCondition condition) {
		ApplyInfo entity = (ApplyInfo)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getStatus())) {
			cr1 = Restrictions.like("status",entity.getStatus(), MatchMode.ANYWHERE);
		}
		
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getStuid())) {
			cr1 = Restrictions.eq("stuid",entity.getStuid());
		}
		return this.getPagers(condition,cr1,cr2);
	}

	@Override
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}