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
import com.pro.entity.Clazz;
@Service
@Transactional
public class ClazzDAOImpl extends BaseDAO<Clazz, Integer> {
@Resource private SessionFactory sessionFactory;
protected Class<Clazz> getReferenceClass() {
	return Clazz.class;
}

protected SessionFactory getSessionFactory() {
	return this.sessionFactory;
}

public List search(DefaultQueryCondition condition) {
	return null;
}

public Page<Clazz> getRecord(DefaultQueryCondition condition) {
	StringBuffer sb = new StringBuffer();
	QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
	StringBuffer where = new StringBuffer();
	sb.append("select pro from Clazz pro where 1=1 ");
	where.append("1=1 ");
	boolean temp = false;
	Clazz pro = (Clazz)condition.getCondition();
		String name = pro.getName();
		if (CommonUtil.isNotEmpty(name)) {
			sb.append(" and pro.name like :name ");
			qcSQL.setLikeParameter("name", name);
			where.append(" and name like '%").append(name).append("%'");
			temp = true;
		}
		
	String sch = pro.getSchool();
	if(CommonUtil.isNotEmpty(sch)) {
		sb.append(" and pro.school like :sch ");
		qcSQL.setLikeParameter("sch", sch);
		where.append(" and school like '%").append(sch).append("%'");
		temp = true;
	}

	Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
	query.setFirstResult(condition.getFirstResult());
	query.setMaxResults(condition.getPageSize());
	qcSQL.parameterToQuery(query);
	List<Clazz> list = query.list();
	int curPage = condition.getPageIndex();
	Page<Clazz> page = null;
	if(temp) {
		page = new Page(list,this.getRevoedsNum(where.toString()),curPage,condition.getPageSize());
	}else {
		page = new Page(list,this.getRecordsNum(),curPage,condition.getPageSize());
	}
	return page;
	}
}