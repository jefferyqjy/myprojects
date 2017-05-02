package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.PartTimeInfo;

	@Service
	public class PartTimeInfoManager{
		@Resource private IBaseDAO partTimeInfoDAOImpl;

		public void add(PartTimeInfo entity) throws Exception {
			try {
				this.partTimeInfoDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.partTimeInfoDAOImpl.getViaHql("from PartTimeInfo where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<PartTimeInfo> queryAll() {
			return this.partTimeInfoDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.partTimeInfoDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.partTimeInfoDAOImpl.getViaHql(hql);
		}

		public PartTimeInfo queryById(Integer id) {
			return (PartTimeInfo)this.partTimeInfoDAOImpl.getById(id);
		}

		public PartTimeInfo querySingleRecordViaKey(String key, String value) {
			List list = this.partTimeInfoDAOImpl.getViaHql("from PartTimeInfo where "+key+"='"+value+"'");
		if(list != null && list.size() > 0){
			return (PartTimeInfo)this.partTimeInfoDAOImpl.getViaHql("from PartTimeInfo where "+key+"='"+value+"'").get(0);
		}else{
		return null;
		}
		}
		public void update(PartTimeInfo entity) {
			this.partTimeInfoDAOImpl.update(entity);
		}

		public Page<PartTimeInfo> getRecords(DefaultQueryCondition condition) {
			return this.partTimeInfoDAOImpl.getRecord(condition);
		}

	}