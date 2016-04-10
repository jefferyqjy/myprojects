package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.Student;

	@Service
	public class StudentManager{
		@Resource private IBaseDAO studentDAOImpl;

		public void insert(Student pro) throws Exception {
			try {
				this.studentDAOImpl.add(pro);
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

		public void update(Student pro) {
			this.studentDAOImpl.update(pro);
		}

		public Page<Student> getRecords(DefaultQueryCondition condition) {
			return this.studentDAOImpl.getRecord(condition);
		}

	}