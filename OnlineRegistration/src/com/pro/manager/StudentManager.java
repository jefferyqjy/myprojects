package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Exam;
import com.pro.entity.Student;

	@Service
	public class StudentManager{
		@Resource private IBaseDAO studentDAOImpl;

		public void add(Student entity) throws Exception {
			try {
				this.studentDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.studentDAOImpl.getViaHql("from Student where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Student> queryAll() {
			return this.studentDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.studentDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.studentDAOImpl.getViaHql(hql);
		}

		public Student queryById(Integer id) {
			return (Student)this.studentDAOImpl.getById(id);
		}

		public Student querySingleRecordViaKey(String key, String value) {
			return (Student)this.studentDAOImpl.getViaHql("from Student where "+key+"='"+value+"'").get(0);
		}

		public void update(Student entity) {
			this.studentDAOImpl.update(entity);
		}

		public Page<Student> getRecords(DefaultQueryCondition condition) {
			return this.studentDAOImpl.getRecord(condition);
		}
		
		public Student querySingleRecordViaKeys(String key, String value) {
			//return (Employee)this.employeeDAOImpl.getViaHql("from Employee where "+key+"='"+value+"'").get(0);
			List list = this.studentDAOImpl.getViaHql("from Student where "+key+"='"+value+"'");
			if(list != null && list.size() > 0){
				return (Student) list.get(0);
			}else{
				return null;
			}
		}

	}