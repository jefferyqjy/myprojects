package com.sys.plugin.announce;

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

@Service
@Transactional
public class AnnounceDAOImpl extends BaseDAO<Announce, Integer> {
	@Resource
	private SessionFactory sessionFactory;

	protected Class<Announce> getReferenceClass() {
		return Announce.class;
	}

	protected SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public List search(DefaultQueryCondition condition) {
		return null;
	}

	public Page<Announce> getRecord(DefaultQueryCondition condition) {
		StringBuffer sb = new StringBuffer();
		QueryConditionSQL qcSQL = new QueryConditionSQL(sb);
		StringBuffer where = new StringBuffer();
		sb.append("select pro from Announce pro where 1=1 ");
		where.append("1=1 ");
		boolean temp = false;
		Announce pro = (Announce) condition.getCondition();
		String title = pro.getTitle();
		if (CommonUtil.isNotEmpty(title)) {
			sb.append(" and pro.title like :title ");
			qcSQL.setLikeParameter("title", title);
			where.append(" and title like '%").append(title).append("%'");
			temp = true;
		}
		String date = pro.getCreateTime();
		if (CommonUtil.isNotEmpty(date)) {
			sb.append(" and pro.createTime = :date ");
			qcSQL.setParameter("date", date);
			where.append(" and createTime='").append(date).append("'");
			temp = true;
		}
		sb.append(" order by id desc");
		Query query = this.sessionFactory.getCurrentSession().createQuery(
				qcSQL.getSQL().toString());
		query.setFirstResult(condition.getFirstResult());
		query.setMaxResults(condition.getPageSize());
		qcSQL.parameterToQuery(query);
		List<Announce> list = query.list();
		int curPage = condition.getPageIndex();
		Page<Announce> page = null;
		if (temp) {
			page = new Page(list, this.getRevoedsNum(where.toString()),
					curPage, condition.getPageSize());
		} else {
			page = new Page(list, this.getRecordsNum(), curPage, condition
					.getPageSize());
		}
		return page;
	}
}