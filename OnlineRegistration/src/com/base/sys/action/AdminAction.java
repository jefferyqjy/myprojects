package com.base.sys.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.base.common.util.AjaxResponseUtil;
import com.base.common.util.AjaxReturnInfo;
import com.base.common.util.CommonUtil;
import com.base.common.util.Const;
import com.base.common.util.PlaceUtil;
import com.base.common.util.SessionManager;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Admin;
import com.base.sys.manager.AdminManager;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import com.pro.entity.Student;
import com.pro.manager.StudentManager;

@Controller
public class AdminAction {
	@Resource
	private AdminManager adminManager;
	
	@Resource
	private StudentManager studentManager;

	private String username;
	private String password;
	private String usertype;
	private String telephone;
	private String email;
	private String address;
	private String qq;
	private String name;
	private String gender;
	private int age;
	private String isLocked;
	private String isLogon;
	private Date lastLoginTime;
	private Date accountCreateTime;
	private int passErrorTimes;
	
	private DefaultQueryCondition<Admin> condition;

	public String loginCheck() {
		AjaxReturnInfo info = new AjaxReturnInfo();
		if ("admin".equalsIgnoreCase(this.username) && "admin".equalsIgnoreCase(this.password)
				&& "0".equals(this.usertype)) {
			info.setSuccess(true);
		} else {
			Admin admin = this.adminManager.getAdminByUsername(this.username);
			if(admin == null) {
				info.setErrMsg(BTAGI18N.getI18NValue("error.user.notexist", "admin"));
			} else {
				if(!admin.getUsertype().equals(this.usertype)) {
					info.setErrMsg(BTAGI18N.getI18NValue("error.usertype.notmatch", "admin"));
				} else {
					if(!admin.getPassword().equals(this.password)) {
						info.setErrMsg(BTAGI18N.getI18NValue("error.password.error", "admin"));
					} else {
						info.setSuccess(true);
					}
				}
			}
		}
		return AjaxResponseUtil.getInstance(info).respToClient();
	}

	public String login() {
		SessionManager.setAdminUsername(this.username);
		SessionManager.setAdminUserType(this.usertype);
		String name="";
		Student temp = this.studentManager.querySingleRecordViaKeys("number",this.username);
		if (temp != null) {
			name = temp.getName();
		}
		SessionManager.setName(name);
		name = null;
		this.username = null;
		this.usertype = null;
		return "adminLoginSuccess";
	}

	public String logout() {
		SessionManager.getHttpSession().invalidate();
		return "adminLoginPage";
	}

	public String add() {
		if (CommonUtil.isEmpty(this.username)) {
			String errMsg = BTAGI18N.getI18NValue("error.cannot.empty", "common");
			errMsg = errMsg.replaceFirst("%s%", BTAGI18N.getI18NValue("username", "admin"));
			ActionContext.getContext().put(Const.Notification.ERROR, errMsg);
			return Const.Pages.QUERY_JSP;
		}
		if (this.adminManager.isUserExit(this.username)) {
			String errMsg = BTAGI18N.getI18NValue("error.user.isexist", "admin");
			ActionContext.getContext().put(Const.Notification.ERROR, errMsg);
			return Const.Pages.QUERY_JSP;
		}
		Admin admin = new Admin();
		admin.setUsername(this.username);
		admin.setPassword(this.password);
		admin.setUsertype(this.usertype);
		try {
			this.adminManager.addUser(admin);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			//return Const.Pages.QUERY_JSP;
			this.name = null;
			return "queryDO";
		} catch (Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.QUERY_JSP;
		}
	}
	
	public String detail() {
		String userName = CommonUtil.genUTF8String(this.username);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, this.adminManager.getAdminByUsername(userName));
		this.username = null;
		return Const.Pages.MAPPING_URL;
	}
	
	public String checkOldPassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String oldPassword = request.getParameter("oldpassword");
		Admin admin = this.adminManager.getAdminByUsername(CommonUtil.genUTF8String(this.username));
		AjaxReturnInfo info = new AjaxReturnInfo();
		if(admin != null && oldPassword.equals(admin.getPassword())) {
			info.setSuccess(true);
		} else {
			info.setErrMsg(BTAGI18N.getI18NValue("error.oldpassword.error", "admin"));
		}
		return AjaxResponseUtil.getInstance(info).respToClient();
	}
	
	
	public String query() {
		Admin adm = new Admin();
		adm.setUsername(this.username);
		condition = new DefaultQueryCondition(adm);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter(Page.CURRENT_PAGE);
		String pageSize = request.getParameter(Page.PAGE_SIZE);
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Admin> page = this.adminManager.getAdminList(condition);
		List<Admin> user = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, user);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,
				page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.QUERY_JSP;
	}
	
	public String del() {
		this.adminManager.deleteUser(CommonUtil.genUTF8String(this.username));
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		this.username = null;
		return Const.Pages.QUERY_DO;
	}
	
	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = CommonUtil.genUTF8String(request.getParameter("ids"));
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.adminManager.deleteUser(idList[i]);
		}
		return Const.Pages.QUERY_DO;
	}
	
	public String edit() {
		String key = CommonUtil.genUTF8String(this.username);
		Admin admin = this.adminManager.getAdminByUsername(key);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, admin);
		return Const.Pages.MAPPING_URL;
	}
	
	public String update() {
		String key = CommonUtil.genUTF8String(this.username);
		Admin admin = this.adminManager.getAdminByUsername(key);
		admin.setUsertype(this.usertype);
		this.adminManager.updateUser(admin);
		this.username = null;
		return Const.Pages.QUERY_DO;
	}
	
	public String changePassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		this.password = request.getParameter("newpassword");
		String key = CommonUtil.genUTF8String(this.username);
		Admin admin = this.adminManager.getAdminByUsername(key);
		admin.setPassword(this.password);
		this.adminManager.updateUser(admin);
		this.username = null;
		this.password = null;
		return Const.Pages.SUCCESS;
	}
	
	public String getCities() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String province = request.getParameter("pid");
		String cities = PlaceUtil.genCitiesSelectString(province);
		try {
			response.getWriter().print(cities);
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AdminManager getAdminManager() {
		return adminManager;
	}

	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public String getIsLogon() {
		return isLogon;
	}

	public void setIsLogon(String isLogon) {
		this.isLogon = isLogon;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getAccountCreateTime() {
		return accountCreateTime;
	}

	public void setAccountCreateTime(Date accountCreateTime) {
		this.accountCreateTime = accountCreateTime;
	}

	public int getPassErrorTimes() {
		return passErrorTimes;
	}

	public void setPassErrorTimes(int passErrorTimes) {
		this.passErrorTimes = passErrorTimes;
	}
}
