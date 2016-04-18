package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.Employee;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import com.pro.manager.EmployeeManager;
@Controller
public class EmployeeAction {
	@Resource
	private EmployeeManager employeeManager;
 private int id;
	
	
	private String nameid;
	
	
	
	private String name;
	
	
	private String sex;
	
	
	private Date datebirth;
	
	
	private String entityid;
	
	
	
	private String address;
	
	
	private String tel;
	
	
	private Date datehire;
	
	
	private String type;
	
	
	
	private String remark;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNameid() {
		return nameid;
	}


	public void setNameid(String nameid) {
		this.nameid = nameid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Date getDatebirth() {
		return datebirth;
	}


	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}


	public String getEntityid() {
		return entityid;
	}


	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	


	public Date getDatehire() {
		return datehire;
	}


	public void setDatehire(Date datehire) {
		this.datehire = datehire;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	


	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		Employee entity = new Employee();
		try {
			entity.setNameid(this.nameid);
			entity.setSex(this.sex);
			entity.setTel(this.tel);
			entity.setRemark(this.remark);
			entity.setDatebirth(this.datebirth);
			entity.setEntityid(this.entityid);
			entity.setDatehire(this.datehire);
			entity.setName(this.name);
			entity.setType(this.type);
			entity.setAddress(this.address);
			this.employeeManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.employeeManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.employeeManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Employee entity = this.employeeManager.queryById(this.id);
		entity.setTel(this.tel);
		entity.setRemark(this.remark);
		entity.setType(this.type);
		entity.setAddress(this.address);
		this.employeeManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Employee entity = this.employeeManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Employee entity = this.employeeManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Employee entity = new Employee();
		entity.setNameid(this.nameid);
		entity.setEntityid(this.entityid);
		entity.setName(this.name);
		condition = new DefaultQueryCondition(entity);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter(Page.CURRENT_PAGE);
		String pageSize = request.getParameter(Page.PAGE_SIZE);
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Employee> page = this.employeeManager.getRecords(condition);
		List<Employee> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	public String query2() {
		return this.query();
 }	public String select() {
		return this.query();
 }}