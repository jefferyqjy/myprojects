package com.pro.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
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
	private String names; // 课程列表
	
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

	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}

	private DefaultQueryCondition condition;
	private Page page;

	public String del() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
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

	public String query() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String) session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		
		CourseStudent pro = new CourseStudent();
		pro.setYear(this.year);
		pro.setType(this.type);
		pro.setStuno(userid);
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
		Page<CourseStudent> page = this.courseStudentManager.getRecords(condition);
		List<CourseStudent> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	@SuppressWarnings("unchecked")
	public String select() {
		if(StringUtils.isNotEmpty(names)) {
			String[] names = this.names.split(",");
			try{
				
				for(int i = 0; i< names.length; i++) {
					String name = names[i];
					String hql = "from CourseStudent where year = " + this.year + " and type = '" + this.type + "' and stuno='" + this.stuno + "' and name = '" + name + "'";
					List<CourseStudent> ls = this.courseStudentManager.queryByHql(hql);
					if(!CommonUtil.isListEmpty(ls)) {
						return CommonUtil.genActionError("课程【" + name + "】已选择！请重新选择！");
					}
					
					CourseStudent obj = new CourseStudent();
					obj.setName(name.trim());
					obj.setStuno(stuno);
					obj.setType(type);
					obj.setYear(year);
					this.courseStudentManager.insert(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
				ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
				return ActionSupport.ERROR;
			}
			
		}
		return ActionSupport.SUCCESS;
	}

}