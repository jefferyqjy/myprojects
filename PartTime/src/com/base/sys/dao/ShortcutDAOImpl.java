package com.base.sys.dao;
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
import com.base.sys.entity.Shortcut;
@Service
@Transactional
public class ShortcutDAOImpl extends BaseDAO<Shortcut, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Shortcut> getReferenceClass() {
		return Shortcut.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Shortcut> getRecord(DefaultQueryCondition condition) {
		Shortcut entity = (Shortcut)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getName())) {
			cr1 = Restrictions.like("name",entity.getName(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = Restrictions.eq("username", entity.getUsername());
		return this.getPagers(condition,cr1,cr2);
	}

	
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}