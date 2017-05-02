package com.z.plugin.announce;
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
@Service
@Transactional
public class AnnounceDAOImpl extends BaseDAO<Announce, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Announce> getReferenceClass() {
		return Announce.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Announce> getRecord(DefaultQueryCondition condition) {
		Announce entity = (Announce)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getTitle())) {
			cr1 = Restrictions.like("title", entity.getTitle(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition, cr1);
	}

	
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}