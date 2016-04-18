package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Saleproduct;

	@Service
	public class SaleproductManager{
		@Resource private IBaseDAO saleproductDAOImpl;

		public void add(Saleproduct entity) throws Exception {
			try {
				this.saleproductDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.saleproductDAOImpl.getViaHql("from Saleproduct where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Saleproduct> queryAll() {
			return this.saleproductDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.saleproductDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.saleproductDAOImpl.getViaHql(hql);
		}

		public List queryBySql(String sql) {
			return this.saleproductDAOImpl.getViaSql(sql);
		}
		
		public Saleproduct queryById(Integer id) {
			return (Saleproduct)this.saleproductDAOImpl.getById(id);
		}

		public Saleproduct querySingleRecordViaKey(String key, String value) {
			return (Saleproduct)this.saleproductDAOImpl.getViaHql("from Saleproduct where "+key+"='"+value+"'").get(0);
		}

		public void update(Saleproduct entity) {
			this.saleproductDAOImpl.update(entity);
		}

		public Page<Saleproduct> getRecords(DefaultQueryCondition condition) {
			return this.saleproductDAOImpl.getRecord(condition);
		}

	}