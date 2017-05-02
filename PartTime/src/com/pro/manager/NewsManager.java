package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.News;

	@Service
	public class NewsManager{
		@Resource private IBaseDAO newsDAOImpl;

		public void add(News entity) throws Exception {
			try {
				this.newsDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.newsDAOImpl.getViaHql("from News where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<News> queryAll() {
			return this.newsDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.newsDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.newsDAOImpl.getViaHql(hql);
		}

		public News queryById(Integer id) {
			return (News)this.newsDAOImpl.getById(id);
		}

		public News querySingleRecordViaKey(String key, String value) {
			List list = this.newsDAOImpl.getViaHql("from News where "+key+"='"+value+"'");
		if(list != null && list.size() > 0){
			return (News)this.newsDAOImpl.getViaHql("from News where "+key+"='"+value+"'").get(0);
		}else{
		return null;
		}
		}
		public void update(News entity) {
			this.newsDAOImpl.update(entity);
		}

		public Page<News> getRecords(DefaultQueryCondition condition) {
			return this.newsDAOImpl.getRecord(condition);
		}

	}