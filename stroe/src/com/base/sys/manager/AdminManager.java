package com.base.sys.manager;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.dao.IBaseDAO;
import com.base.sys.entity.Admin;

@Service
public class AdminManager {

	@Resource
	private IBaseDAO adminDAOImpl;

	public void addUser(Admin user) throws Exception {
		try {
			this.adminDAOImpl.add(user);
		} catch (Exception e) {
			throw new Exception("添加失败");
		}
	}

	public boolean isUserExit(String username) {
		Admin admin = (Admin) this.adminDAOImpl.getById(username);
		return admin != null ? true : false;
	}

	public Admin getAdminByUsername(String username) {
		return (Admin) this.adminDAOImpl.getById(username);
	}

	public boolean checkPassword(String username, String password) {
		Admin adm = (Admin) this.adminDAOImpl.getById(username);
		String pwd = adm.getPassword();
		if (pwd.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public void updateUser(Admin adm) {
		this.adminDAOImpl.update(adm);
	}

	public void deleteUser(String id) {
		this.adminDAOImpl.delete(id);
	}
	
	public List queryByHql(String hql) {
		return this.adminDAOImpl.getViaHql(hql);
	}

	public Page<Admin> getAdminList(DefaultQueryCondition<Admin> condition) {
		return this.adminDAOImpl.getRecord(condition);
	}
}
