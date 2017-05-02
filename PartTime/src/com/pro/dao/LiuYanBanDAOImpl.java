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
import com.pro.entity.LiuYanBan;
@Service
@Transactional
public class LiuYanBanDAOImpl extends BaseDAO<LiuYanBan, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<LiuYanBan> getReferenceClass() {
		return LiuYanBan.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<LiuYanBan> getRecord(DefaultQueryCondition condition) {
		LiuYanBan entity = (LiuYanBan)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getUsername())) {
			cr1 = Restrictions.like("username",entity.getUsername(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getStatus())) {
			cr2 = Restrictions.like("status",entity.getStatus(), MatchMode.ANYWHERE);
		}
		
		Criterion cr3 = null;
		if(CommonUtil.isNotEmpty(entity.getTitle())) {
			cr2 = Restrictions.like("title",entity.getTitle(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2,cr3);
	}

	@Override
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}