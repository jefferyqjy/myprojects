package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Finance;

	@Service
	public class FinanceManager{
		@Resource private IBaseDAO financeDAOImpl;

		public void add(Finance entity) throws Exception {
			try {
				this.financeDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.financeDAOImpl.getViaHql("from Finance where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Finance> queryAll() {
			return this.financeDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.financeDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.financeDAOImpl.getViaHql(hql);
		}

		public List queryBySql(String sql) {
			return this.financeDAOImpl.getViaSql(sql);
		}
		
		public Finance queryById(Integer id) {
			return (Finance)this.financeDAOImpl.getById(id);
		}

		public Finance querySingleRecordViaKey(String key, String value) {
			return (Finance)this.financeDAOImpl.getViaHql("from Finance where "+key+"='"+value+"'").get(0);
		}

		public void update(Finance entity) {
			this.financeDAOImpl.update(entity);
		}

		public Page<Finance> getRecords(DefaultQueryCondition condition) {
			return this.financeDAOImpl.getRecord(condition);
		}

	}