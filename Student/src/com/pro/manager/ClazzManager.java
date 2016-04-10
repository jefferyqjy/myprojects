package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.Clazz;

	@Service
	public class ClazzManager{
		@Resource private IBaseDAO clazzDAOImpl;

		public void insert(Clazz pro) throws Exception {
			try {
				this.clazzDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.clazzDAOImpl.getViaHql("from Clazz where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Clazz> queryAll() {
			return this.clazzDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.clazzDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.clazzDAOImpl.getViaHql(hql);
		}

		public Clazz queryById(Integer id) {
			return (Clazz)this.clazzDAOImpl.getById(id);
		}

		public Clazz querySingleRecordViaKey(String key, String value) {
			return (Clazz)this.clazzDAOImpl.getViaHql("from Clazz where "+key+"='"+value+"'").get(0);
		}

		public void update(Clazz pro) {
			this.clazzDAOImpl.update(pro);
		}

		public Page<Clazz> getRecords(DefaultQueryCondition condition) {
			return this.clazzDAOImpl.getRecord(condition);
		}

	}