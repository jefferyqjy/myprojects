package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Exam;

	@Service
	public class ExamManager{
		@Resource private IBaseDAO examDAOImpl;

		public void add(Exam entity) throws Exception {
			try {
				this.examDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.examDAOImpl.getViaHql("from Exam where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Exam> queryAll() {
			return this.examDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.examDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.examDAOImpl.getViaHql(hql);
		}

		public Exam queryById(Integer id) {
			return (Exam)this.examDAOImpl.getById(id);
		}

		public Exam querySingleRecordViaKey(String key, String value) {
			return (Exam)this.examDAOImpl.getViaHql("from Exam where "+key+"='"+value+"'").get(0);
		}

		public void update(Exam entity) {
			this.examDAOImpl.update(entity);
		}

		public Page<Exam> getRecords(DefaultQueryCondition condition) {
			return this.examDAOImpl.getRecord(condition);
		}
		
		public Exam querySingleRecordViaKeys(String key, String value) {
			//return (Employee)this.employeeDAOImpl.getViaHql("from Employee where "+key+"='"+value+"'").get(0);
			List list = this.examDAOImpl.getViaHql("from Exam where "+key+"='"+value+"'");
			if(list != null && list.size() > 0){
				return (Exam) list.get(0);
			}else{
				return null;
			}
		}

	}