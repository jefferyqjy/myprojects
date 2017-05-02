package com.z.plugin.jfile;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

	@Service
	public class JfileManager{
		@Resource private IBaseDAO jfileDAOImpl;

		public void add(Jfile entity) throws Exception {
			try {
				this.jfileDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.jfileDAOImpl.getViaHql("from Jfile where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Jfile> queryAll() {
			return this.jfileDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.jfileDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.jfileDAOImpl.getViaHql(hql);
		}

		public Jfile queryById(Integer id) {
			return (Jfile)this.jfileDAOImpl.getById(id);
		}

		public Jfile querySingleRecordViaKey(String key, String value) {
			return (Jfile)this.jfileDAOImpl.getViaHql("from Jfile where "+key+"='"+value+"'").get(0);
		}

		public void update(Jfile entity) {
			this.jfileDAOImpl.update(entity);
		}

		public Page<Jfile> getRecords(DefaultQueryCondition condition) {
			return this.jfileDAOImpl.getRecord(condition);
		}

	}