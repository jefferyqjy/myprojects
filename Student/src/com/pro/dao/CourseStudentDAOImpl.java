package com.pro.dao;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.entity.CourseStudent;
import com.sys.common.BaseDAO;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;
@Service
@Transactional
public class CourseStudentDAOImpl extends BaseDAO<CourseStudent, Integer> {
	@Resource private SessionFactory sessionFactory;
	
	protected Class<CourseStudent> getReferenceClass() {
		return CourseStudent.class;
	}
	
	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public List search(DefaultQueryCondition condition) {
		return null;
	}
	
	public Page<CourseStudent> getRecord(DefaultQueryCondition condition) {
		StringBuffer sb = new StringBuffer();
		QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
		StringBuffer where = new StringBuffer();
		sb.append("select pro from CourseStudent pro where 1=1 ");
		where.append("1=1 ");
		boolean temp = false;
		CourseStudent pro = (CourseStudent)condition.getCondition();
		String name = pro.getName();
		if(CommonUtil.isNotEmpty(name)) {
			sb.append(" and pro.name like :name ");
			qcSQL.setLikeParameter("name", name);
			where.append(" and name like '%").append(name).append("%'");
			temp = true;
		}
	
		Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
		query.setFirstResult(condition.getFirstResult());
		query.setMaxResults(condition.getPageSize());
		qcSQL.parameterToQuery(query);
		List<CourseStudent> list = query.list();
		int curPage = condition.getPageIndex();
		Page<CourseStudent> page = null;
		if(temp) {
			page = new Page(list,this.getRevoedsNum(where.toString()),curPage,condition.getPageSize());
		}else {
			page = new Page(list,this.getRecordsNum(),curPage,condition.getPageSize());
		}
		return page;
	}
}