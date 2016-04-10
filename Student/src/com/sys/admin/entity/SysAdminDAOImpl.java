package com.sys.admin.entity;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.common.BaseDAO;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;
@Service @Transactional
public class SysAdminDAOImpl extends BaseDAO<SysAdmin, String> {
	@Resource private SessionFactory sessionFactory;

	@Override
	protected Class<SysAdmin> getReferenceClass() {
		return SysAdmin.class;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public List search(DefaultQueryCondition condition) {
		StringBuffer sb = new StringBuffer();
		QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
		sb.append("select adm from SysAdmin adm where 1=1 ");
		SysAdmin con = (SysAdmin)condition.getCondition();
		if(CommonUtil.isNotEmpty(con.getUsername())) {
			sb.append("and adm.username like :username ");
			qcSQL.setLikeParameter("username", con.getUsername());
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
		query.setFirstResult(condition.getFirstResult());
		query.setMaxResults(condition.getPageSize());
		qcSQL.parameterToQuery(query);
		return query.list();
	}
	
	public Page<SysAdmin> getRecord(DefaultQueryCondition condition) {
		StringBuffer sb = new StringBuffer();
		StringBuffer where = new StringBuffer();
		QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
		sb.append("SELECT adm FROM SysAdmin adm WHERE 1=1 ");
		where.append("1=1 ");
		SysAdmin con = (SysAdmin)condition.getCondition();
		boolean temp = false;
		if(CommonUtil.isNotEmpty(con.getUsername())) {
			sb.append("AND adm.username LIKE :username ");
			qcSQL.setLikeParameter("username", con.getUsername());
			where.append("and username like '%").append(con.getUsername()).append("%' ");
			temp = true;
		}
		if(con.getUserType() == 2) {
			sb.append(" AND adm.userType = :userType ");
			qcSQL.setParameter("userType", 2);
			where.append("and userType=2");
			temp = true;
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
		query.setFirstResult(condition.getFirstResult());
		query.setMaxResults(condition.getPageSize());
		qcSQL.parameterToQuery(query);
		List<SysAdmin> list = query.list();
		int curPage = condition.getPageIndex();
		Page<SysAdmin> page = null;
		if(temp){
			page = new Page<SysAdmin>(list,this.getRevoedsNum(where.toString()),curPage,condition.getPageSize());
		}else {
			page = new Page<SysAdmin>(list,this.getRecordsNum(),curPage,condition.getPageSize());
		}
		return page;
	}

}
