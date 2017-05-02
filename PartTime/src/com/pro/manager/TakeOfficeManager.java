package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.TakeOffice;

	@Service
	public class TakeOfficeManager{
		@Resource private IBaseDAO takeOfficeDAOImpl;

		public void add(TakeOffice entity) throws Exception {
			try {
				this.takeOfficeDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.takeOfficeDAOImpl.getViaHql("from TakeOffice where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<TakeOffice> queryAll() {
			return this.takeOfficeDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.takeOfficeDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.takeOfficeDAOImpl.getViaHql(hql);
		}

		public TakeOffice queryById(Integer id) {
			return (TakeOffice)this.takeOfficeDAOImpl.getById(id);
		}

		public TakeOffice querySingleRecordViaKey(String key, String value) {
			List list = this.takeOfficeDAOImpl.getViaHql("from TakeOffice where "+key+"='"+value+"'");
		if(list != null && list.size() > 0){
			return (TakeOffice)this.takeOfficeDAOImpl.getViaHql("from TakeOffice where "+key+"='"+value+"'").get(0);
		}else{
		return null;
		}
		}
		public void update(TakeOffice entity) {
			this.takeOfficeDAOImpl.update(entity);
		}

		public Page<TakeOffice> getRecords(DefaultQueryCondition condition) {
			return this.takeOfficeDAOImpl.getRecord(condition);
		}

	}