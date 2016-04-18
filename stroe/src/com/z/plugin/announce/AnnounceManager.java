package com.z.plugin.announce;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

	@Service
	public class AnnounceManager{
		@Resource private IBaseDAO announceDAOImpl;

		public void add(Announce entity) throws Exception {
			try {
				this.announceDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.announceDAOImpl.getViaHql("from Announce where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Announce> queryAll() {
			return this.announceDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.announceDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.announceDAOImpl.getViaHql(hql);
		}

		public Announce queryById(Integer id) {
			return (Announce)this.announceDAOImpl.getById(id);
		}

		public Announce querySingleRecordViaKey(String key, String value) {
			return (Announce)this.announceDAOImpl.getViaHql("from Announce where "+key+"='"+value+"'").get(0);
		}

		public void update(Announce entity) {
			this.announceDAOImpl.update(entity);
		}

		public Page<Announce> getRecords(DefaultQueryCondition condition) {
			return this.announceDAOImpl.getRecord(condition);
		}

	}