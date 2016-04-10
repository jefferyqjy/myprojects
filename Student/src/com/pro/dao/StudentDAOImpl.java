package com.pro.dao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import java.util.List;
import com.sys.common.BaseDAO;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;
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

public List search(DefaultQueryCondition condition) {
	return null;
}

public Page<Student> getRecord(DefaultQueryCondition condition) {
	StringBuffer sb = new StringBuffer();
	QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
	StringBuffer where = new StringBuffer();
	sb.append("select pro from Student pro where 1=1 ");
	where.append("1=1 ");
	boolean temp = false;
	Student pro = (Student)condition.getCondition();
	String name = pro.getName();
	if(CommonUtil.isNotEmpty(name)) {
		sb.append(" and pro.name like :name ");
		qcSQL.setLikeParameter("name", name);
		where.append(" and name like '%").append(name).append("%'");
		temp = true;
	}
	String stuid = pro.getStuid();
	if(CommonUtil.isNotEmpty(stuid)) {
		sb.append(" and pro.stuid like :stuid ");
		qcSQL.setLikeParameter("stuid", stuid);
		where.append(" and stuid like '%").append(stuid).append("%'");
		temp = true;
	}

	String idno = pro.getIdno();
	if(CommonUtil.isNotEmpty(idno)) {
		sb.append(" and pro.idno like :idno ");
		qcSQL.setLikeParameter("idno", idno);
		where.append(" and idno like '%").append(idno).append("%'");
		temp = true;
	}
	String clazzname = pro.getClazzname();
	if(CommonUtil.isNotEmpty(clazzname)) {
		sb.append(" and pro.clazzname = :clazzname ");
		qcSQL.setParameter("clazzname", clazzname);
		where.append(" and clazzname ='").append(clazzname).append("'");
		temp = true;
	}
	Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
	query.setFirstResult(condition.getFirstResult());
	query.setMaxResults(condition.getPageSize());
	qcSQL.parameterToQuery(query);
	List<Student> list = query.list();
	int curPage = condition.getPageIndex();
	Page<Student> page = null;
	if(temp) {
		page = new Page(list,this.getRevoedsNum(where.toString()),curPage,condition.getPageSize());
	}else {
		page = new Page(list,this.getRecordsNum(),curPage,condition.getPageSize());
	}
	return page;
	}
}