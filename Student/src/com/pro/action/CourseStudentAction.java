package com.pro.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pro.entity.CourseStudent;
import com.pro.manager.CourseStudentManager;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.Const;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
@Controller
public class CourseStudentAction {
	@Resource
	private CourseStudentManager courseStudentManager;
	private int id;
	private Integer year; // 学年
	private String stuno; // 学生学号
	private String type; // 学期
	private String[] names; // 课程列表
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}

	private DefaultQueryCondition condition;
	private Page page;
	/*public String add() {
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
	}*/

	public String del() {
		this.courseStudentManager.deleteViaId(this.id);
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.courseStudentManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	/*public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		
		Course pro = this.courseManager.queryById(this.id);
		pro.setContent(this.content);
		pro.setNumber(this.number);
		pro.setPass(this.pass);
		this.courseManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}*/

	/*public String query() {
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
	}*/
	
	/*public String query2() {
		return this.query();
	}*/
	
	public String select() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("names");
		//String hql = "from CourseStudent where year = " + this.year + " and type = '" + this.type + "' and stuno='" + this.stuno + "' and name = '" + this.name + "'";
		/*List<CourseStudent> ls = this.courseStudentManager.queryByHql(hql);
		if(!CommonUtil.isListEmpty(ls)) {
			return CommonUtil.genActionError("课程已选择！请重新选择！");
		}*/
		/*CourseStudent obj = new CourseStudent();
		obj.setId(id);
		obj.setName(this.name);
		obj.setStuno(this.stuno);
		obj.setType(this.type);
		obj.setYear(this.year);*/
		/*try {
			this.courseStudentManager.insert(obj);
		} catch(Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
			return ActionSupport.ERROR;
		}*/
		return ActionSupport.SUCCESS;
	}

}