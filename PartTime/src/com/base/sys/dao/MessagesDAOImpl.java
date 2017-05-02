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
import com.base.sys.entity.Messages;
@Service
@Transactional
public class MessagesDAOImpl extends BaseDAO<Messages, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Messages> getReferenceClass() {
		return Messages.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Messages> getRecord(DefaultQueryCondition condition) {
		Messages entity = (Messages)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getTitle())) {
			cr1 = Restrictions.like("title",entity.getTitle(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getContent())) {
			cr2 = Restrictions.like("content",entity.getContent(), MatchMode.ANYWHERE);
		}
		Criterion cr3 = null;
		if(CommonUtil.isNotEmpty(entity.getUsername())) {
			cr3 = Restrictions.like("username",entity.getUsername(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2,cr3);
	}

	
	public Page getRecord(String column, String order,
			DefaultQueryCondition condition) {
		// TODO Auto-generated method stub
		return null;
	}
}