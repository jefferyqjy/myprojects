package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.Course;
import com.pro.entity.CourseStudent;

	@Service
	public class CourseStudentManager{
		@Resource private IBaseDAO courseStudentDAOImpl;

		public void insert(CourseStudent pro) throws Exception {
			try {
				this.courseStudentDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.courseStudentDAOImpl.getViaHql("from CourseStudent where " + key + "='" + value + "'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<CourseStudent> queryAll() {
			return this.courseStudentDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.courseStudentDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.courseStudentDAOImpl.getViaHql(hql);
		}

		public CourseStudent queryById(Integer id) {
			return (CourseStudent)this.courseStudentDAOImpl.getById(id);
		}

		public CourseStudent querySingleRecordViaKey(String key, String value) {
			return (CourseStudent)this.courseStudentDAOImpl.getViaHql("from CourseStudent where "+key+"='"+value+"'").get(0);
		}

		public void update(CourseStudent pro) {
			this.courseStudentDAOImpl.update(pro);
		}

		public Page<CourseStudent> getRecords(DefaultQueryCondition condition) {
			return this.courseStudentDAOImpl.getRecord(condition);
		}

	}