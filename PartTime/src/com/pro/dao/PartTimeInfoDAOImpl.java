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
import com.pro.entity.PartTimeInfo;
@Service
@Transactional
public class PartTimeInfoDAOImpl extends BaseDAO<PartTimeInfo, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<PartTimeInfo> getReferenceClass() {
		return PartTimeInfo.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<PartTimeInfo> getRecord(DefaultQueryCondition condition) {
		PartTimeInfo entity = (PartTimeInfo)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getTypes())) {
			cr1 = Restrictions.like("types",entity.getTypes(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getTitle())) {
			cr2 = Restrictions.like("title",entity.getTitle(), MatchMode.ANYWHERE);
		}
		Criterion cr3 = null;
		if(CommonUtil.isNotEmpty(entity.getWorkadd())) {
			cr3 = Restrictions.like("workadd",entity.getWorkadd(), MatchMode.ANYWHERE);
		}
		Criterion cr4 = null;
		if(CommonUtil.isNotEmpty(entity.getUsername())) {
			cr4 = Restrictions.like("username",entity.getUsername(), MatchMode.ANYWHERE);
		}
		Criterion cr5 = null;
		if(CommonUtil.isNotEmpty(entity.getStatus())) {
			cr5 = Restrictions.like("status",entity.getStatus(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2,cr3,cr4,cr5);
	}

	@Override
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}