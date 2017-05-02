package com.base.sys.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.sys.entity.Messages;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;

	@Service
	public class MessagesManager{
		@Resource private IBaseDAO messagesDAOImpl;

		public void add(Messages entity) throws Exception {
			try {
				this.messagesDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.messagesDAOImpl.getViaHql("from Messages where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Messages> queryAll() {
			return this.messagesDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.messagesDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.messagesDAOImpl.getViaHql(hql);
		}

		public Messages queryById(Integer id) {
			return (Messages)this.messagesDAOImpl.getById(id);
		}

		public Messages querySingleRecordViaKey(String key, String value) {
			return (Messages)this.messagesDAOImpl.getViaHql("from Messages where "+key+"='"+value+"'").get(0);
		}

		public void update(Messages entity) {
			this.messagesDAOImpl.update(entity);
		}

		public Page<Messages> getRecords(DefaultQueryCondition condition) {
			return this.messagesDAOImpl.getRecord(condition);
		}

	}