package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Product;

	@Service
	public class ProductManager{
		@Resource private IBaseDAO productDAOImpl;

		public void add(Product entity) throws Exception {
			try {
				this.productDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.productDAOImpl.getViaHql("from Product where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Product> queryAll() {
			return this.productDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.productDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.productDAOImpl.getViaHql(hql);
		}

		public Product queryById(Integer id) {
			return (Product)this.productDAOImpl.getById(id);
		}

		public Product querySingleRecordViaKey(String key, String value) {
			return (Product)this.productDAOImpl.getViaHql("from Product where "+key+"='"+value+"'").get(0);
		}

		public void update(Product entity) {
			this.productDAOImpl.update(entity);
		}

		public Page<Product> getRecords(DefaultQueryCondition condition) {
			return this.productDAOImpl.getRecord(condition);
		}

	}