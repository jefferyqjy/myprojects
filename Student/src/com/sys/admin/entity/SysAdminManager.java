package com.sys.admin.entity;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.common.IBaseDAO;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;

@Service
public class SysAdminManager {
	@Resource private IBaseDAO sysAdminDAOImpl;
	
	public void addUser(SysAdmin user) throws Exception{
		String username = user.getUsername();
		if(isUserExit(username)) {
			throw new Exception("用户名已经被用，请换一个");
		}else {
			try {
				this.sysAdminDAOImpl.add(user);
			}catch(Exception e) {
				throw new Exception("添加用户失败，未知错误，可能是数据库连接有问题，请检查然后重试");
			}
		}
	}
	
	public boolean isUserExit(String username) {
		SysAdmin admin = (SysAdmin)this.sysAdminDAOImpl.getById(username);
		return admin != null ? true : false;
	}
	
	public Page<SysAdmin> query(DefaultQueryCondition condition) {
		List<SysAdmin> list =  sysAdminDAOImpl.search(condition);
		Page<SysAdmin> page = new Page();
		int curPage = condition.getPageIndex();
		int prePage = curPage - 1;
		int nextPage = curPage + 1;
		page.setCurrentPage(curPage);
		page.setPreviousPage(prePage);
		page.setNextPage(nextPage);
		page.setList(list);
		page.setTotalRecords(sysAdminDAOImpl.getRecordsNum());
		return page;
	}
	
	public Page<SysAdmin> getRecords(DefaultQueryCondition condition) {
		return this.sysAdminDAOImpl.getRecord(condition);
	}
	
	public SysAdmin getSysAdminById(String username) {
		return (SysAdmin)this.sysAdminDAOImpl.getById(username);
	}
	
	public boolean checkPassword(String username,String password) {
		SysAdmin adm = (SysAdmin)this.sysAdminDAOImpl.getById(username);
		String pwd = adm.getPassword();
		if(pwd.equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
	public List queryByHql(String hql) {
		return this.sysAdminDAOImpl.getViaHql(hql);
	}
	
	public void updateUser(SysAdmin adm) {
		this.sysAdminDAOImpl.update(adm);
	}
	
	public void deleteUser(String id){
		this.sysAdminDAOImpl.delete(id);
	}
	

}
