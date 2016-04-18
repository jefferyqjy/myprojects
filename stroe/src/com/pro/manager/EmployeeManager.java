package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.Employee;

	@Service
	public class EmployeeManager{
		@Resource private IBaseDAO employeeDAOImpl;

		public void add(Employee entity) throws Exception {
			try {
				this.employeeDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.employeeDAOImpl.getViaHql("from Employee where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<Employee> queryAll() {
			return this.employeeDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.employeeDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.employeeDAOImpl.getViaHql(hql);
		}

		public Employee queryById(Integer id) {
			return (Employee)this.employeeDAOImpl.getById(id);
		}

		public Employee querySingleRecordViaKey(String key, String value) {
			return (Employee)this.employeeDAOImpl.getViaHql("from Employee where "+key+"='"+value+"'").get(0);
		}

		public void update(Employee entity) {
			this.employeeDAOImpl.update(entity);
		}

		public Page<Employee> getRecords(DefaultQueryCondition condition) {
			return this.employeeDAOImpl.getRecord(condition);
		}

	}