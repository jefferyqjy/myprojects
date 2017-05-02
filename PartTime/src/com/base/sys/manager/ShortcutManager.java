package com.base.sys.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.sys.entity.Shortcut;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

	@Service
	public class ShortcutManager{
		@Resource private IBaseDAO shortcutDAOImpl;

		public void add(Shortcut entity) throws Exception {
			try {
				this.shortcutDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			//List list = this.shortcutDAOImpl.getViaHql("from Shortcut where "+key+"='"+value+"'");
			//return (list != null && list.size() > 0) ? true : false;
			return false;
		}

		public List<Shortcut> queryAll() {
			return this.shortcutDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.shortcutDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.shortcutDAOImpl.getViaHql(hql);
		}

		public Shortcut queryById(Integer id) {
			return (Shortcut)this.shortcutDAOImpl.getById(id);
		}

		public Shortcut querySingleRecordViaKey(String key, String value) {
			//return (Shortcut)this.shortcutDAOImpl.getViaHql("from Shortcut where "+key+"='"+value+"'").get(0);
			return new Shortcut();
		}

		public void update(Shortcut entity) {
			this.shortcutDAOImpl.update(entity);
		}

		public Page<Shortcut> getRecords(DefaultQueryCondition condition) {
			return this.shortcutDAOImpl.getRecord(condition);
		}

	}