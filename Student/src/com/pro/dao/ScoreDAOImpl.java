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
import com.pro.entity.Score;
@Service
@Transactional
public class ScoreDAOImpl extends BaseDAO<Score, Integer> {
@Resource private SessionFactory sessionFactory;
protected Class<Score> getReferenceClass() {
	return Score.class;
}

protected SessionFactory getSessionFactory() {
	return this.sessionFactory;
}

public List search(DefaultQueryCondition condition) {
	return null;
}

public Page<Score> getRecord(DefaultQueryCondition condition) {
	StringBuffer sb = new StringBuffer();
	QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
	StringBuffer where = new StringBuffer();
	sb.append("select pro from Score pro where 1=1 ");
	where.append("1=1 ");
	boolean temp = false;
	Score pro = (Score)condition.getCondition();
	String name = pro.getName();
	if(CommonUtil.isNotEmpty(name)) {
	sb.append(" and pro.name = :name ");
	qcSQL.setParameter("name", name);
	where.append(" and name = '").append(name).append("'");
	temp = true;
	}
	
	String stuid = pro.getStuno();
	if(CommonUtil.isNotEmpty(stuid)) {
		sb.append(" and pro.stuno = :stuid ");
		qcSQL.setParameter("stuid", stuid);
		where.append(" and stuno = '").append(stuid).append("'");
		temp = true;
	}
	
	String clazzname = pro.getClazzname();
	if(CommonUtil.isNotEmpty(clazzname)) {
		sb.append(" and pro.clazzname = :clazzname ");
		qcSQL.setParameter("clazzname",clazzname);
		where.append(" and clazzname = '").append(clazzname).append("'");
		temp = true;
	}
	
	sb.append(" and pro.year = :year ");
	qcSQL.setParameter("year", pro.getYear());
	where.append(" and year = ").append(pro.getYear());
	temp = true;
	
	String type = pro.getType();
	if(CommonUtil.isNotEmpty(type)) {
		sb.append(" and pro.type = :type ");
		qcSQL.setParameter("type", type);
		where.append(" and type = '").append(type).append("'");
		temp = true;
	}
	
	String sch = pro.getSchool();
	if(CommonUtil.isNotEmpty(sch)) {
		sb.append(" and pro.school like :sch ");
		qcSQL.setLikeParameter("sch", sch);
		where.append(" and school like '%").append(sch).append("%'");
		temp = true;
	}
	
	String status = pro.getStatus();
	if(CommonUtil.isNotEmpty(status)) {
		sb.append(" and pro.status = :status ");
		qcSQL.setParameter("status", status);
		where.append(" and status = '").append(status).append("'");
		temp = true;
	}

	Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
	query.setFirstResult(condition.getFirstResult());
	query.setMaxResults(condition.getPageSize());
	qcSQL.parameterToQuery(query);
	List<Score> list = query.list();
	int curPage = condition.getPageIndex();
	Page<Score> page = null;
	if(temp) {
		page = new Page(list,this.getRevoedsNum(where.toString()),curPage,condition.getPageSize());
	}else {
		page = new Page(list,this.getRecordsNum(),curPage,condition.getPageSize());
	}
	return page;
	}
}