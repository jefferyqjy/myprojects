package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.Score;

	@Service
	public class ScoreManager{
		@Resource private IBaseDAO scoreDAOImpl;

		public void insert(Score pro) throws Exception {
			try {
				this.scoreDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.scoreDAOImpl.getViaHql("from Score where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Score> queryAll() {
			return this.scoreDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.scoreDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.scoreDAOImpl.getViaHql(hql);
		}

		public Score queryById(Integer id) {
			return (Score)this.scoreDAOImpl.getById(id);
		}

		public Score querySingleRecordViaKey(String key, String value) {
			return (Score)this.scoreDAOImpl.getViaHql("from Score where "+key+"='"+value+"'").get(0);
		}

		public void update(Score pro) {
			this.scoreDAOImpl.update(pro);
		}

		public Page<Score> getRecords(DefaultQueryCondition condition) {
			return this.scoreDAOImpl.getRecord(condition);
		}

	}