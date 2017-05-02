package com.z.plugin.bbss;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

	@Service
	public class MessageManager{
		@Resource private IBaseDAO messageDAOImpl;

		public void add(Message entity) throws Exception {
			try {
				this.messageDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.messageDAOImpl.getViaHql("from Message where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Message> queryAll() {
			return this.messageDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.messageDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.messageDAOImpl.getViaHql(hql);
		}

		public Message queryById(Integer id) {
			return (Message)this.messageDAOImpl.getById(id);
		}

		public Message querySingleRecordViaKey(String key, String value) {
			return (Message)this.messageDAOImpl.getViaHql("from Message where "+key+"='"+value+"'").get(0);
		}

		public void update(Message entity) {
			this.messageDAOImpl.update(entity);
		}

		public Page<Message> getRecords(DefaultQueryCondition condition) {
			return this.messageDAOImpl.getRecord(condition);
		}

	}