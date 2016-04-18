package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Customerinfo;

	@Service
	public class CustomerinfoManager{
		@Resource private IBaseDAO customerinfoDAOImpl;

		public void add(Customerinfo entity) throws Exception {
			try {
				this.customerinfoDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.customerinfoDAOImpl.getViaHql("from Customerinfo where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Customerinfo> queryAll() {
			return this.customerinfoDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.customerinfoDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.customerinfoDAOImpl.getViaHql(hql);
		}

		public Customerinfo queryById(Integer id) {
			return (Customerinfo)this.customerinfoDAOImpl.getById(id);
		}

		public Customerinfo querySingleRecordViaKey(String key, String value) {
			return (Customerinfo)this.customerinfoDAOImpl.getViaHql("from Customerinfo where "+key+"='"+value+"'").get(0);
		}

		public void update(Customerinfo entity) {
			this.customerinfoDAOImpl.update(entity);
		}

		public Page<Customerinfo> getRecords(DefaultQueryCondition condition) {
			return this.customerinfoDAOImpl.getRecord(condition);
		}

	}