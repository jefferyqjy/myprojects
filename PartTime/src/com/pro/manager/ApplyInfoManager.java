package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.ApplyInfo;

	@Service
	public class ApplyInfoManager{
		@Resource private IBaseDAO applyInfoDAOImpl;

		public void add(ApplyInfo entity) throws Exception {
			try {
				this.applyInfoDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.applyInfoDAOImpl.getViaHql("from ApplyInfo where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<ApplyInfo> queryAll() {
			return this.applyInfoDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.applyInfoDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.applyInfoDAOImpl.getViaHql(hql);
		}

		public ApplyInfo queryById(Integer id) {
			return (ApplyInfo)this.applyInfoDAOImpl.getById(id);
		}

		public ApplyInfo querySingleRecordViaKey(String key, String value) {
			List list = this.applyInfoDAOImpl.getViaHql("from ApplyInfo where "+key+"='"+value+"'");
		if(list != null && list.size() > 0){
			return (ApplyInfo)this.applyInfoDAOImpl.getViaHql("from ApplyInfo where "+key+"='"+value+"'").get(0);
		}else{
		return null;
		}
		}
		public void update(ApplyInfo entity) {
			this.applyInfoDAOImpl.update(entity);
		}

		public Page<ApplyInfo> getRecords(DefaultQueryCondition condition) {
			return this.applyInfoDAOImpl.getRecord(condition);
		}

	}