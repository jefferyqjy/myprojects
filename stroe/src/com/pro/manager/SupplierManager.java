package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Supplier;

	@Service
	public class SupplierManager{
		@Resource private IBaseDAO supplierDAOImpl;

		public void add(Supplier entity) throws Exception {
			try {
				this.supplierDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.supplierDAOImpl.getViaHql("from Supplier where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Supplier> queryAll() {
			return this.supplierDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.supplierDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.supplierDAOImpl.getViaHql(hql);
		}

		public Supplier queryById(Integer id) {
			return (Supplier)this.supplierDAOImpl.getById(id);
		}

		public Supplier querySingleRecordViaKey(String key, String value) {
			return (Supplier)this.supplierDAOImpl.getViaHql("from Supplier where "+key+"='"+value+"'").get(0);
		}

		public void update(Supplier entity) {
			this.supplierDAOImpl.update(entity);
		}

		public Page<Supplier> getRecords(DefaultQueryCondition condition) {
			return this.supplierDAOImpl.getRecord(condition);
		}

	}