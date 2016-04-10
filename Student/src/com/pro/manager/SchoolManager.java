package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.School;

	@Service
	public class SchoolManager{
		@Resource private IBaseDAO schoolDAOImpl;

		public void insert(School pro) throws Exception {
			try {
				this.schoolDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.schoolDAOImpl.getViaHql("from School where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<School> queryAll() {
			return this.schoolDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.schoolDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.schoolDAOImpl.getViaHql(hql);
		}

		public School queryById(Integer id) {
			return (School)this.schoolDAOImpl.getById(id);
		}

		public School querySingleRecordViaKey(String key, String value) {
			return (School)this.schoolDAOImpl.getViaHql("from School where "+key+"='"+value+"'").get(0);
		}

		public void update(School pro) {
			this.schoolDAOImpl.update(pro);
		}

		public Page<School> getRecords(DefaultQueryCondition condition) {
			return this.schoolDAOImpl.getRecord(condition);
		}

	}