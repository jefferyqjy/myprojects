package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.entity.Course;

	@Service
	public class CourseManager{
		@Resource private IBaseDAO courseDAOImpl;

		public void insert(Course pro) throws Exception {
			try {
				this.courseDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.courseDAOImpl.getViaHql("from Course where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Course> queryAll() {
			return this.courseDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.courseDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.courseDAOImpl.getViaHql(hql);
		}

		public Course queryById(Integer id) {
			return (Course)this.courseDAOImpl.getById(id);
		}

		public Course querySingleRecordViaKey(String key, String value) {
			return (Course)this.courseDAOImpl.getViaHql("from Course where "+key+"='"+value+"'").get(0);
		}

		public void update(Course pro) {
			this.courseDAOImpl.update(pro);
		}

		public Page<Course> getRecords(DefaultQueryCondition condition) {
			return this.courseDAOImpl.getRecord(condition);
		}

	}