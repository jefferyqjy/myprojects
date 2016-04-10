package com.sys.plugin.file;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;

	@Service
	public class JfileManager{
		@Resource private IBaseDAO jfileDAOImpl;

		public void insert(Jfile pro) throws Exception {
			try {
				this.jfileDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.jfileDAOImpl.getViaHql("from JFile where "+key+"='"+value+"'");
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
			return (Jfile)this.jfileDAOImpl.getViaHql("from JFile where "+key+"='"+value+"'").get(0);
		}

		public void update(Jfile pro) {
			this.jfileDAOImpl.update(pro);
		}

		public Page<Jfile> getRecords(DefaultQueryCondition condition) {
			return this.jfileDAOImpl.getRecord(condition);
		}

	}