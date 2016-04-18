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
import com.pro.entity.Employee;
@Service
@Transactional
public class EmployeeDAOImpl extends BaseDAO<Employee, Integer> {
	@Resource private SessionFactory sessionFactory;
	protected Class<Employee> getReferenceClass() {
		return Employee.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Page<Employee> getRecord(DefaultQueryCondition condition) {
		Employee entity = (Employee)condition.getCondition();
		Criterion cr1 = null;
		if(CommonUtil.isNotEmpty(entity.getNameid())) {
			cr1 = Restrictions.like("nameid",entity.getNameid(), MatchMode.ANYWHERE);
		}
		Criterion cr2 = null;
		if(CommonUtil.isNotEmpty(entity.getName())) {
			cr2 = Restrictions.like("name",entity.getName(), MatchMode.ANYWHERE);
		}
		Criterion cr3 = null;
		if(CommonUtil.isNotEmpty(entity.getEntityid())) {
			cr3 = Restrictions.like("entityid",entity.getEntityid(), MatchMode.ANYWHERE);
		}
		return this.getPagers(condition,cr1,cr2,cr3);
	}
}