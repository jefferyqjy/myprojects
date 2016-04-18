package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Orderproduct;

	@Service
	public class OrderproductManager{
		@Resource private IBaseDAO orderproductDAOImpl;

		public void add(Orderproduct entity) throws Exception {
			try {
				this.orderproductDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.orderproductDAOImpl.getViaHql("from Orderproduct where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Orderproduct> queryAll() {
			return this.orderproductDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.orderproductDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.orderproductDAOImpl.getViaHql(hql);
		}

		/*public List queryBySql(String sql) {
			return this.orderproductDAOImpl.getViaSql(sql);
		}*/
		
		public List queryBySql(String sql) {
			return this.orderproductDAOImpl.getViaSql(sql);
		}
		
		public Orderproduct queryById(Integer id) {
			return (Orderproduct)this.orderproductDAOImpl.getById(id);
		}

		public Orderproduct querySingleRecordViaKey(String key, String value) {
			return (Orderproduct)this.orderproductDAOImpl.getViaHql("from Orderproduct where "+key+"='"+value+"'").get(0);
		}

		public void update(Orderproduct entity) {
			this.orderproductDAOImpl.update(entity);
		}

		public Page<Orderproduct> getRecords(DefaultQueryCondition condition) {
			return this.orderproductDAOImpl.getRecord(condition);
		}

	}