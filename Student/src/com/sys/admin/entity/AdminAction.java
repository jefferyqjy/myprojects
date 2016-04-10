package com.sys.admin.entity;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.manager.xml.util.XMLUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.Const;
import com.sys.common.util.DateUtil;
import com.sys.log.LogFactory;
import com.sys.web.ajax.AjaxResponseUtil;
import com.sys.web.ajax.AjaxReturnInfo;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;

@Controller
public class AdminAction {
	@Resource
	private SysAdminManager sysAdminManager;
	private String username;
	private String password;
	private String userType;
	private String isLock;
	private String address;
	private String qq;
	private String phone;
	private String gender;
	private String email;
	private String name;
	
	private String loginType;

	private DefaultQueryCondition condition;
	private Page page;

	private static Logger logger = LogFactory.getLogger();

	/**
	 * 添加管理员或者用户
	 * 
	 * @return
	 */
	public String add() {
		if (CommonUtil.isEmpty(this.username)) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG,
					"用户名不能为空");
			return Const.ACTION_RETURN_ERROR;
		}
		if (CommonUtil.isEmpty(this.password)) {
			ActionContext.getContext()
					.put(Const.ACTION_PUT_ERROR_MSG, "密码不能为空");
			return Const.ACTION_RETURN_ERROR;

		}
		SysAdmin user = new SysAdmin();
		user.setCreateTime(DateUtil.getCurrentDateTime());
		user.setUsername(this.username);
		user.setPassword(this.password);
		user.setAddress(this.address);
		user.setEmail(this.email);
		user.setGender(this.gender);
		user.setName(this.name);
		user.setQq(this.qq);
		user.setPhone(this.phone);
		user.setUserType(Integer.parseInt(this.userType));
		try {
			sysAdminManager.addUser(user);
		} catch (Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG,
					e.getMessage());
			return Const.ACTION_RETURN_ERROR;
		}
		return Const.ACTION_RETURN_SUCCESS;
	}
	
	public String query() {
		SysAdmin adm = new SysAdmin();
		adm.setUsername(this.username);
		condition = new DefaultQueryCondition(adm);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<SysAdmin> page = sysAdminManager.getRecords(condition);
		List<SysAdmin> user = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, user);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,
				page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}

	public String query2() {
		SysAdmin adm = new SysAdmin();
		adm.setUsername(username);
		adm.setUserType(2);
		condition = new DefaultQueryCondition(adm);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<SysAdmin> page = sysAdminManager.getRecords(condition);
		List<SysAdmin> user = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, user);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,
				page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		ActionContext.getContext().put("username", username);
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query3() {
		SysAdmin adm = new SysAdmin();
		adm.setUserType(2);
		condition = new DefaultQueryCondition(adm);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<SysAdmin> page = sysAdminManager.getRecords(condition);
		List<SysAdmin> user = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, user);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,
				page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		ActionContext.getContext().put("username", username);
		return Const.ACTION_RETURN_QUERY;
	}

	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = this.getUsername();
		String password = this.getPassword();
		String callType = request.getParameter("callType");
		AjaxReturnInfo info = new AjaxReturnInfo();
		if ("admin".equalsIgnoreCase(username)
				&& "admin".equalsIgnoreCase(password)) {
			if(!"0".equals(this.loginType)) {
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
		}
		boolean hasUser = this.sysAdminManager.isUserExit(username);
		if (hasUser) {
			boolean isPwdCorrect = this.sysAdminManager.checkPassword(username,
					password);
			SysAdmin adm = this.sysAdminManager.getSysAdminById(username);
			int errTimes = adm.getErrorTimes();
			if(errTimes == 4 && !timeNotout(adm)) {
				return CommonUtil.genActionError("密码错误三次，半小时后再尝试登录");
			}
			adm.setLoginTime(DateUtil.getCurrentDateTime());
			this.sysAdminManager.updateUser(adm);
			if (isPwdCorrect) {
				adm.setErrorTimes(0);
				try {
					this.sysAdminManager.updateUser(adm);
				} catch (Exception e) {
					ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG,
							e.getMessage() + "/更具体信息请查看日志");
					LogFactory.getLogger().error(
							"更新用户登录时间的时候出错了，可能原因是数据连接不存在，请检查数据库状态");
					return Const.ACTION_RETURN_ERROR;
				}
				String type = String.valueOf(adm.getUserType());
				if(!type.equals(this.loginType)) {
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
				if ("3".equals(type)) {
					ActionContext.getContext().getSession().put(
							Const.ACTION_PUT_SESSION_USRE_TYPE, type);
					ActionContext.getContext().getSession().put(
							Const.ACTION_PUT_SESSION_FRONT_USER_NAME, this.username);
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
					return Const.ACTION_USER_LOGIN_SUCCESS;
				} else {
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
//					if("3".equals(type)) {
//						Date date = new Date();
//						int nowYear = date.getYear();
//						int month = date.getMonth()+1;
//						String type1 = null;
//						if(month <8 && month >1) {
//							type1 = "上";
//						}else {
//							type1 = "下";
//						}
//						Date dd = null;
//						List<Register> lsreg = this.registerManager.queryByHql("from Register where stuid='"+this.username+"' and type='"+type1+"'");
//						if(!CommonUtil.isListEmpty(lsreg)) {
//							boolean isReg = false;
//							for(Register reg : lsreg) {
//								dd = DateUtil.convString2Date(reg.getYear());
//								if(dd.getYear() == nowYear) {
//									isReg = true;
//								}
//							}
//							if(!isReg) {
//								return CommonUtil.genActionError("该学号在"+nowYear+"年未注册"+type1+"学期还未注册");
//							}
//						}
//					}
					return Const.ACTION_ADMIN_LOGIN_SUCCESS;
				}
			} else {
				adm.setErrorTimes(adm.getErrorTimes()+1);
				this.sysAdminManager.updateUser(adm);
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
			}else {
				return CommonUtil.genActionError("用户不存在");
			}
		}
		return null;
	}

	public String logout() {
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
	}

	public String del() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			this.sysAdminManager.deleteUser(CommonUtil
					.genUTF8String(this.username));
			request.setAttribute(Const.PAGE_SUCCESS_FORWARD, "admin/admin_query.do?username=");
			return Const.ACTION_RETURN_SUCCESS;
		} catch (Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG,
					e.getMessage());
			return Const.ACTION_RETURN_ERROR;
		}
	}

	public String modify() {
		String userId = CommonUtil.genUTF8String(this.username);
		SysAdmin adm = this.sysAdminManager.getSysAdminById(userId);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, adm);
		return Const.ACTION_RETURN_QUERY;
	}

	public String changePwd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		if("前台用户类型的type值".equals(request.getParameter("loginType"))){
			String username = (String)session.getAttribute("frontUsername");
			SysAdmin user = this.sysAdminManager.getSysAdminById(username);
			ActionContext.getContext().put(Const.ACTION_PUT_RESULT, user);
			return Const.ACTION_RETURN_QUERY;
		}
		String username = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		if(CommonUtil.isEmpty(username)) {
			request.setAttribute(Const.ACTION_PUT_ERROR_MSG, "Session Timeout,请重新登录");
			return Const.ACTION_RETURN_ERROR;
		}
		SysAdmin user = this.sysAdminManager.getSysAdminById(username);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, user);
		return Const.ACTION_RETURN_QUERY;
	}

	public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String callType = request.getParameter("callType");
		String userId = this.username;
		SysAdmin adm = this.sysAdminManager.getSysAdminById(userId);
		if ("changePwd".equals(request.getParameter("type"))) {
			String pwd = adm.getPassword();
			if (pwd.equals(request.getParameter("oldPwd"))) {
				if("ajax".equalsIgnoreCase(callType)) {
					AjaxReturnInfo info = new AjaxReturnInfo();
					return AjaxResponseUtil.getInstance(info).respToClient();
				}
				adm.setPassword(request.getParameter("newPwd"));
				this.sysAdminManager.updateUser(adm);
				return ActionSupport.SUCCESS;
			} else {
				AjaxReturnInfo info = new AjaxReturnInfo();
				info.setErrMsg("原始密码错误");
				if("ajax".equalsIgnoreCase(callType)) {
					return AjaxResponseUtil.getInstance(info).respToClient();
				} else {
					ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG,
							"原始密码错误");
					return Const.ACTION_RETURN_ERROR;
				}
			}
		} else {
			adm.setPassword(this.password);
			adm.setAddress(this.address);
			adm.setEmail(this.email);
			adm.setGender(this.gender);
			adm.setName(this.name);
			adm.setQq(this.qq);
			adm.setPhone(this.phone);
			this.sysAdminManager.updateUser(adm);
			return Const.ACTION_RETURN_CLOSE;
		}
	}
	
	private boolean timeNotout(SysAdmin adm) {
		boolean isOut = true;
		Date now = new Date();
		Date err = adm.getLoginTime();
		long l = now.getTime() - err.getTime();
		if(l/1000/60 < 30) {
			isOut = false;
		}
		return isOut;
	}

	// later
	public String viewDetail() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		String userId = (String) ActionContext.getContext().getSession().get(
				Const.ACTION_PUT_SESSION_USRE_NAME);
		SysAdmin adm = this.sysAdminManager.getSysAdminById(userId);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, adm);
		return Const.ACTION_RETURN_QUERY;
	}

	/************** getters and setters **************************/
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DefaultQueryCondition getCondition() {
		return condition;
	}

	public void setCondition(DefaultQueryCondition condition) {
		this.condition = condition;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	

}
