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
import com.sys.web.fenye.util.QueryConditionSQL;
@Service
@Transactional
public class FileDAOImpl extends BaseDAO<FileEntity, String> {
@Resource private SessionFactory sessionFactory;
	@Override
	protected Class<FileEntity> getReferenceClass() {
		return FileEntity.class;
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public List search(DefaultQueryCondition condition) {
		StringBuffer sb = new StringBuffer();
		QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
		sb.append("select file from FileEntity file where 1=1 ");
		FileEntity con = (FileEntity)condition.getCondition();
		if(CommonUtil.isNotEmpty(con.getName())) {
			sb.append("and file.name like :name ");
			qcSQL.setLikeParameter("name", con.getName());
		}
		Query query = this.sessionFactory.getCurrentSession().createQuery(qcSQL.getSQL().toString());
		query.setFirstResult(condition.getFirstResult());
		query.setMaxResults(condition.getPageSize());
		qcSQL.parameterToQuery(query);
		return query.list();
	}

}
