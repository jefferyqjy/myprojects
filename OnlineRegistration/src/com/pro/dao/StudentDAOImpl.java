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
import com.pro.entity.Student;
@Service
@Transactional
public class StudentDAOImpl extends BaseDAO<Student, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Student> getReferenceClass() {
		return Student.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Student> getRecord(DefaultQueryCondition condition) {
		Student entity = (Student)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getNumber())) {
			cr1 = Restrictions.like("number",entity.getNumber(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getName())) {
			cr2 = Restrictions.like("name",entity.getName(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2);
	}
}