package com.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.business.AdminBusiness;
import com.entity.AdminEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private AdminEntity admin;
	private List<AdminEntity> list;
	private AdminBusiness adminBusiness;
	private int pageNumber;
	private int maxPage;
	private String html;
	private String number;
	private String id;
	private String name;
	private String cond;
	private String password;
	private String repassword;
	private Map<String, Object> map = new HashMap<String, Object>();

	public String login() {
		List<AdminEntity> adminList = adminBusiness.checkUsername(this.admin
				.getUsername());
		if (adminList.size() == 0) {
			this.map.put("msg", "用户名不存在");
			return "fail";
		} else {
			AdminEntity adminEntity = adminList.get(0);
			if (admin.getPassword().equals(adminEntity.getPassword())) {
				// 把用户信息保存到session去，并跳转到main.jsp
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				// 放数据进入session，成功时，跳转到showWebElment.jsp
				session.put("adminid", adminEntity.getAdminid());
				session.put("admininfo", adminEntity);
				session.put("adminname", adminEntity.getUsername());
			} else {
				this.map.put("msg", "密码错误");
				return "fail";
			}
		}
		return SUCCESS;
	}

	public String prePwd() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("adminid") == null) {
			return "adminerror";
		}
		return "success";
	}

	public String editpwd() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("adminid") == null) {
			return "adminerror";
		}
		String adminid = (String) session.get("adminid");
		AdminEntity adminEntity = adminBusiness.checkId(adminid);
		if (adminEntity.getPassword().equals(admin.getPassword())) {
			adminEntity.setPassword(this.repassword);
			adminBusiness.update(adminEntity);
			this.map.put("msg", "修改成功");
		} else {
			this.map.put("msg", "原始密码错误");
		}
		return "success";
	}

	public String createAdmin() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		return SUCCESS;
	}

	public String addAdmin() {
		this.admin.setAdminid(UUID.randomUUID().toString());
		this.adminBusiness.save(this.admin);
		return SUCCESS;
	}

	public String deleteAdmin() {
		try {
			this.adminBusiness.delete(admin);
		} catch (Exception e) {
			this.map.put("msg", "存在关联项 不能删除");
		}
		return SUCCESS;
	}

	public String updateAdmin() {
		this.adminBusiness.update(admin);
		return SUCCESS;
	}

	public String getAllAdmin() {
		this.list = new ArrayList<AdminEntity>();
		List<AdminEntity> tempList = new ArrayList<AdminEntity>();
		tempList = this.adminBusiness.show();
		this.pageNumber = tempList.size();
		this.maxPage = this.pageNumber;
		if (this.maxPage % 10 == 0) {
			this.maxPage = this.maxPage / 10;
		} else {
			this.maxPage = this.maxPage / 10 + 1;
		}
		if (this.number == null) {
			this.number = "0";
		}
		int start = Integer.parseInt(this.number) * 10;
		int over = (Integer.parseInt(this.number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			AdminEntity u = (AdminEntity) tempList.get(i);
			this.list.add(u);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(this.number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer
					.append("<a href=\"admin/getAllAdmin.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"admin/getAllAdmin.action?number="
					+ (Integer.parseInt(this.number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"admin/getAllAdmin.action?number="
					+ (Integer.parseInt(this.number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"admin/getAllAdmin.action?number="
					+ (this.maxPage - 1) + "\">尾页</a>");
		}
		this.html = buffer.toString();
		return SUCCESS;
	}

	public String getAdminById() {
		this.admin = this.adminBusiness.checkId(this.id);
		return SUCCESS;
	}

	public String queryAdminByCond() {
		this.list = new ArrayList<AdminEntity>();
		if ("username".equals(this.cond)) {
			list = this.adminBusiness.checkByLikeUsername(this.name);
		}
		if ("password".equals(this.cond)) {
			list = this.adminBusiness.checkByLikePassword(this.name);
		}
		if ("realname".equals(this.cond)) {
			list = this.adminBusiness.checkByLikeRealname(this.name);
		}
		if ("contact".equals(this.cond)) {
			list = this.adminBusiness.checkByLikeContact(this.name);
		}
		return SUCCESS;
	}

	public AdminEntity getAdmin() {
		return admin;
	}

	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}

	public List<AdminEntity> getList() {
		return list;
	}

	public void setList(List<AdminEntity> list) {
		this.list = list;
	}

	public AdminBusiness getAdminBusiness() {
		return adminBusiness;
	}

	public void setAdminBusiness(AdminBusiness adminBusiness) {
		this.adminBusiness = adminBusiness;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
