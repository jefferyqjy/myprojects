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
import com.pro.entity.Award;
@Service
@Transactional
public class AwardDAOImpl extends BaseDAO<Award, Integer> {
@Resource private SessionFactory sessionFactory;
protected Class<Award> getReferenceClass() {
	return Award.class;
}

protected SessionFactory getSessionFactory() {
	return this.sessionFactory;
}

public List search(DefaultQueryCondition condition) {
	return null;
}

public Page<Award> getRecord(DefaultQueryCondition condition) {
	StringBuffer sb = new StringBuffer();
	QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
	StringBuffer where = new StringBuffer();
	sb.append("select pro from Award pro where 1=1 ");
	where.append("1=1 ");
	boolean temp = false;
	Award pro = (Award)condition.getCondition();

	String stuid = pro.getStuid();
	if(CommonUtil.isNotEmpty(stuid)) {
		sb.append(" and pro.stuid = :stuid ");
		qcSQL.setParameter("stuid", stuid);
		where.append(" and stuid = '").append(stuid).append("'");
		temp = true;
	}
	
	String type = pro.getType();
	if(CommonUtil.isNotEmpty(type)) {
		sb.append(" and pro.type = :type ");
		qcSQL.setParameter("type", type);
		where.append(" and type = '").append(type).append("'");
		temp = true;
	}
	
	String name = pro.getStuname();
	if(CommonUtil.isNotEmpty(name)) {
		sb.append(" and pro.stuname like :name ");
		qcSQL.setLikeParameter("name", name);
		where.append(" and stuname like '%").append(name).append("%'");
		temp = true;
	}
	
	String title = pro.getTitle();
	if(CommonUtil.isNotEmpty(title)) {
		sb.append(" and pro.title like :title ");
		qcSQL.setLikeParameter("title", title);
		where.append(" and title like '%").append(title).append("%'");
		temp = true;
	}

	Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
	query.setFirstResult(condition.getFirstResult());
	query.setMaxResults(condition.getPageSize());
	qcSQL.parameterToQuery(query);
	List<Award> list = query.list();
	int curPage = condition.getPageIndex();
	Page<Award> page = null;
	if(temp) {
		page = new Page(list,this.getRevoedsNum(where.toString()),curPage,condition.getPageSize());
	}else {
		page = new Page(list,this.getRecordsNum(),curPage,condition.getPageSize());
	}
	return page;
	}
}