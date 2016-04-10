package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.Award;

	@Service
	public class AwardManager{
		@Resource private IBaseDAO awardDAOImpl;

		public void insert(Award pro) throws Exception {
			try {
				this.awardDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.awardDAOImpl.getViaHql("from Award where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Award> queryAll() {
			return this.awardDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.awardDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.awardDAOImpl.getViaHql(hql);
		}

		public Award queryById(Integer id) {
			return (Award)this.awardDAOImpl.getById(id);
		}

		public Award querySingleRecordViaKey(String key, String value) {
			return (Award)this.awardDAOImpl.getViaHql("from Award where "+key+"='"+value+"'").get(0);
		}

		public void update(Award pro) {
			this.awardDAOImpl.update(pro);
		}

		public Page<Award> getRecords(DefaultQueryCondition condition) {
			return this.awardDAOImpl.getRecord(condition);
		}

	}