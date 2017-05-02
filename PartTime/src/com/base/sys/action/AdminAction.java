package com.base.sys.action;

import java.io.PrintWriter;
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
import com.base.common.util.DateUtil;
import com.base.common.util.PlaceUtil;
import com.base.common.util.SessionManager;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Admin;
import com.base.sys.manager.AdminManager;
import com.base.web.tag.BTAGI18N;
import com.manager.xml.util.XMLUtil;
import com.opensymphony.xwork2.ActionContext;
/*import com.sys.admin.entity.SysAdmin;
import com.sys.common.util.DateUtil;
import com.sys.log.LogFactory;*/
import com.opensymphony.xwork2.ActionSupport;
//import com.pro.entity.FrontStyle;
//import com.pro.manager.FrontStyleManager;


@Controller
public class AdminAction {
	@Resource
	private AdminManager adminManager;

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
	private String flag;
	
	private String college;
	private String remark;
	
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private DefaultQueryCondition<Admin> condition;

	public String loginCheck() {
		AjaxReturnInfo info = new AjaxReturnInfo();
		if ("admin".equalsIgnoreCase(this.username) && "admin".equalsIgnoreCase(this.password)
				&& "0".equals(this.usertype)) {
			info.setSuccess(true);
		} else {
			Admin admin = this.adminManager.getAdminByUsername(CommonUtil.genUTF8String(this.username));
			if(admin == null) {
				info.setErrMsg(BTAGI18N.getI18NValue("error.user.notexist", "admin"));
			} else {
				if(!admin.getUsertype().equals(this.usertype)) {
					//判断作家  Type不对， 但是flag= Y 且密码正确
					if(admin.getPassword().equals(this.password)&& "Y".equals(admin.getFlag())) {
						info.setSuccess(true);
						return AjaxResponseUtil.getInstance(info).respToClient();
					} 
					
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
		if(!"admin".equals(username)){
			Admin admin = this.adminManager.getAdminByUsername(CommonUtil.genUTF8String(this.username));
			if(admin.getFlag() != null){
				SessionManager.setAdminUserFlag(admin.getFlag());
			}
		}
		
		this.username = null;
		this.usertype = null;
		return "adminLoginSuccess";
	}

	
	public String login2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = this.getUsername();
		String password = this.getPassword();
		String callType = request.getParameter("callType");
		AjaxReturnInfo info = new AjaxReturnInfo();
		/*if ("admin".equalsIgnoreCase(username)
				&& "admin".equalsIgnoreCase(password)) {
			if(!"0".equals(this.usertype)) {
				info.setErrMsg("用户类型不匹配");
			}
			ActionContext.getContext().getSession().put(
					Const.ACTION_PUT_SESSION_USRE_TYPE, "0");
			ActionContext.getContext().getSession().put(
					Const.ACTION_PUT_SESSION_USRE_NAME, "admin");
			if("ajax".equalsIgnoreCase(callType)) {
				return AjaxResponseUtil.getInstance(info).respToClient();
			} 
			return Const.ACTION_ADMIN_LOGIN_SUCCESS;
		}*/
		boolean hasUser = this.adminManager.isUserExit(username);
		if (hasUser) {
			boolean isPwdCorrect = this.adminManager.checkPassword(username,password);
			if (isPwdCorrect) {
				Admin adm = this.adminManager.getSysAdminById(username);
				//adm.setLoginTime(DateUtil.getCurrentDateTime());
				
				/*try {
					this.sysAdminManager.updateUser(adm);
				} catch (Exception e) {
					ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG,
							e.getMessage() + "/更具体信息请查看日志");
					LogFactory.getLogger().error(
							"更新用户登录时间的时候出错了，可能原因是数据连接不存在，请检查数据库状态");
					return Const.ACTION_RETURN_ERROR;
				}*/
				String type = String.valueOf(adm.getUsertype());
				if(!type.equals(this.usertype) && !"3".equals(type)) {
					info.setErrMsg("账户与类型不匹配");
					if("ajax".equalsIgnoreCase(callType)) {
						try {
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setCharacterEncoding("UTF-8");
							response.setHeader("charset","UTF-8");
							response.setContentType("text/xml;charset=UTF-8");
							PrintWriter out = response.getWriter();
							String rtnXml = XMLUtil.convertToString(info.genReturnXMLInfo());
							out.print(rtnXml);
							out.close();
							return null;
						}catch(Exception e) {
							e.printStackTrace();
						}
					}else {
						return CommonUtil.genActionError("账户与类型不匹配");
					}
				}
				if ("2".equals(usertype)|| "3".equals(usertype)) {
					ActionContext.getContext().getSession().put(Const.ACTION_PUT_SESSION_USRE_TYPE, usertype);
					ActionContext.getContext().getSession().put(Const.ACTION_PUT_SESSION_FRONT_USER_NAME, this.username);
					info.setSuccess(true);
					if("ajax".equalsIgnoreCase(callType)) {
						try {
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setCharacterEncoding("UTF-8");
							response.setHeader("charset","UTF-8");
							response.setContentType("text/xml;charset=UTF-8");
							PrintWriter out = response.getWriter();
							String rtnXml = XMLUtil.convertToString(info.genReturnXMLInfo());
							out.print(rtnXml);
							out.close();
							return null;
						}catch(Exception e) {
							e.printStackTrace();
						} 
					} 
					
//					List<FrontStyle> ll = frontStyleManager.queryAll();
//					if(ll != null && ll.size() > 0){
//						FrontStyle fs = ll.get(0);
//						if("前台模板一".equalsIgnoreCase(fs.getChecktype())){
//							return "userLoginSuccess";
//						}else{
//							return "userLoginSuccess2";
//						}
//					}
					
					return Const.ACTION_USER_LOGIN_SUCCESS;
				} /*else {
					ActionContext.getContext().getSession().put(
							Const.ACTION_PUT_SESSION_USRE_TYPE, type);
					ActionContext.getContext().getSession().put(
							Const.ACTION_PUT_SESSION_USRE_NAME, this.username);
					info.setSuccess(true);
					if("ajax".equalsIgnoreCase(callType)) {
						try {
							HttpServletResponse response = ServletActionContext.getResponse();
							response.setCharacterEncoding("UTF-8");
							response.setHeader("charset","UTF-8");
							response.setContentType("text/xml;charset=UTF-8");
							PrintWriter out = response.getWriter();
							String rtnXml = XMLUtil.convertToString(info.genReturnXMLInfo());
							out.print(rtnXml);
							out.close();
							return null;
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					return Const.ACTION_ADMIN_LOGIN_SUCCESS;
				}*/
			} else {
				info.setErrMsg("密码错误");
				if("ajax".equalsIgnoreCase(callType)) {
					try {
						HttpServletResponse response = ServletActionContext.getResponse();
						response.setCharacterEncoding("UTF-8");
						response.setHeader("charset","UTF-8");
						response.setContentType("text/xml;charset=UTF-8");
						PrintWriter out = response.getWriter();
						String rtnXml = XMLUtil.convertToString(info.genReturnXMLInfo());
						out.print(rtnXml);
						out.close();
						return null;
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					return CommonUtil.genActionError("密码错误");
				}
			}
		} else {
			info.setErrMsg("用户不存在");
			if("ajax".equalsIgnoreCase(callType)) {
				try {
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.setHeader("charset","UTF-8");
					response.setContentType("text/xml;charset=UTF-8");
					PrintWriter out = response.getWriter();
					String rtnXml = XMLUtil.convertToString(info.genReturnXMLInfo());
					out.print(rtnXml);
					out.close();
					return null;
				}catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				return CommonUtil.genActionError("用户不存在");
			}
		}
		return null;
	}
	
	//@Resource
	//private FrontStyleManager frontStyleManager;
	
	public String logout() {
		SessionManager.getHttpSession().invalidate();
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		if ("2".equals(type)) {
			//ActionContext.getContext().getSession().clear();
			
			/*List<FrontStyle> ll = frontStyleManager.queryAll();
			if(ll != null && ll.size() > 0){
				FrontStyle fs = ll.get(0);
				if("前台模板一".equalsIgnoreCase(fs.getChecktype())){
					return "userLoginPage";
				}else{
					return "userLoginPage2";
				}
			}*/
			
			return "userLoginPage";
		} 
		
		String flag = (String) request.getSession().getAttribute(Const.Session.ADMIN_USER_FlAG);
		if("3".equals(type) ||"Y".equals(flag) ){
			return "authorLoginPage";
		}
		
		
		
		return "adminLoginPage";
	}

	/*public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		if (CommonUtil.isEmpty(type)) {
			ActionContext.getContext().getSession().clear();
			return Const.ACTION_RETURN_ADMIN_LOGIN;
		} else {
			if ("2".equals(type)) {
				ActionContext.getContext().getSession().clear();
				return Const.ACTION_RETURN_USER_LOGIN;
			} else {
				ActionContext.getContext().getSession().clear();
				return Const.ACTION_RETURN_ADMIN_LOGIN;
			}
		}
	}*/
	
	public String add() {
		if (CommonUtil.isEmpty(this.username)) {
			String errMsg = BTAGI18N.getI18NValue("error.cannot.empty", "common");
			errMsg = errMsg.replaceFirst("%s%", BTAGI18N.getI18NValue("username", "admin"));
			ActionContext.getContext().put(Const.Notification.ERROR, errMsg);
			if("2".equals(usertype)){
				ActionContext.getContext().put(Const.Notification.SUCCESS, errMsg);
				return "error22";
			}
			return Const.Pages.QUERY_JSP;
			
		}
		if (this.adminManager.isUserExit(this.username)) {
			String errMsg = BTAGI18N.getI18NValue("error.user.isexist", "admin");
			ActionContext.getContext().put(Const.Notification.ERROR, errMsg);
			if("2".equals(usertype)){
				ActionContext.getContext().put(Const.Notification.SUCCESS, errMsg);
				return "error22";
			}
			return Const.Pages.QUERY_JSP;
			
		}
		Admin admin = new Admin();
		admin.setUsername(this.username);
		admin.setPassword(this.password);
		admin.setUsertype(this.usertype);
		
		admin.setTelephone(this.telephone);
		admin.setEmail(this.email);
		admin.setCollege(this.college);
		admin.setRemark(this.remark);
		
		
		
		try {
			this.adminManager.addUser(admin);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			//return Const.Pages.QUERY_JSP;
			this.name = null;
			if("2".equals(usertype)){
				return "success";
			}
			return "queryDO";
		} catch (Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			if("2".equals(usertype)){
				ActionContext.getContext().put(Const.Notification.SUCCESS, null);
				return "error22";
			}
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
	
	/*public String changePassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		this.password = request.getParameter("newpassword");
		String key = CommonUtil.genUTF8String(this.username);
		Admin admin = this.adminManager.getAdminByUsername(key);
		admin.setPassword(this.password);
		this.adminManager.updateUser(admin);
		this.username = null;
		this.password = null;
		return Const.Pages.SUCCESS;
	}*/
	
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

	public String modify2() {
		String userId = CommonUtil.genUTF8String(this.username);
		Admin adm = this.adminManager.getSysAdminById(userId);
		//ActionContext.getContext().put(Const.ACTION_PUT_RESULT, adm);
		//return Const.ACTION_RETURN_QUERY;
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, adm);
		return Const.Pages.MAPPING_URL;
	}
	
	public String update2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//String callType = request.getParameter("callType");
		String userId = this.username;
		Admin adm = this.adminManager.getSysAdminById(userId);
		
		//adm.setPassword(this.password);
		adm.setAddress(this.address);
		adm.setEmail(this.email);
		adm.setGender(this.gender);
		//adm.setName(this.name);
		adm.setQq(this.qq);
		
		adm.setCollege(this.college);
		adm.setRemark(this.remark);
		
		//adm.setPhone(this.phone);
		this.adminManager.updateUser(adm);
		return Const.Pages.SUCCESS;
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
