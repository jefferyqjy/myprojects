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
import com.pro.entity.Exam;
@Service
@Transactional
public class ExamDAOImpl extends BaseDAO<Exam, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Exam> getReferenceClass() {
		return Exam.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Exam> getRecord(DefaultQueryCondition condition) {
		Exam entity = (Exam)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getNumber())) {
			cr1 = Restrictions.like("number",entity.getNumber(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getSubject())) {
			cr2 = Restrictions.like("subject",entity.getSubject(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2);
	}
}