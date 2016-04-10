package com.pro.action;
import com.sys.common.util.Const;
import com.sys.common.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.pro.entity.Clazz;
import com.pro.entity.Course;
import com.pro.entity.CourseStudent;
import com.pro.entity.Score;
import com.pro.entity.Student;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.pro.manager.CourseManager;
import com.pro.manager.CourseStudentManager;
@Controller
public class CourseAction {
	@Resource
	private CourseManager courseManager;
	
	private int id;
	private String name; // 课程名称
	private String content;
	private int number;
	private int pass;
	private String mandatory;
	
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		if(this.courseManager.isExist("name", this.name)) {
			return CommonUtil.genActionError("该课程已经存在，不能重复添加");
		}
		Course obj = new Course();
		obj.setContent(this.content);
		obj.setName(this.name);
		obj.setNumber(this.number);
		obj.setMandatory(this.mandatory);
		obj.setPass(this.pass);
		try {
			this.courseManager.insert(obj);
		} catch(Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
			return ActionSupport.ERROR;
		}
		return ActionSupport.SUCCESS;
	}

	public String del() {
		this.courseManager.deleteViaId(this.id);
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.courseManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String update() {
		Course pro = this.courseManager.queryById(this.id);
		pro.setContent(this.content);
		pro.setNumber(this.number);
		pro.setPass(this.pass);
		this.courseManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String modify() {
		Course pro = this.courseManager.queryById(this.id);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, pro);
		return Const.ACTION_RETURN_QUERY;
	}

	public String query() {
		Course pro = new Course();
		pro.setName(this.name);
		condition = new DefaultQueryCondition(pro);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Course> page = this.courseManager.getRecords(condition);
		List<Course> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query2() {
		return this.query();
	}
	
	public String query3() {
		return this.query();
	}
	
}