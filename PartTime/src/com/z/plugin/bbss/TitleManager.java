package com.z.plugin.bbss;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

	@Service
	public class TitleManager{
		@Resource private IBaseDAO titleDAOImpl;

		public void add(Title entity) throws Exception {
			try {
				this.titleDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.titleDAOImpl.getViaHql("from Title where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Title> queryAll() {
			return this.titleDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.titleDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.titleDAOImpl.getViaHql(hql);
		}

		public Title queryById(Integer id) {
			return (Title)this.titleDAOImpl.getById(id);
		}

		public Title querySingleRecordViaKey(String key, String value) {
			return (Title)this.titleDAOImpl.getViaHql("from Title where "+key+"='"+value+"'").get(0);
		}

		public void update(Title entity) {
			this.titleDAOImpl.update(entity);
		}

		public Page<Title> getRecords(DefaultQueryCondition condition) {
			return this.titleDAOImpl.getRecord(condition);
		}

	}