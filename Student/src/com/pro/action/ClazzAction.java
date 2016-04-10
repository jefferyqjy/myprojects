package com.pro.action;
import com.sys.common.util.Const;
import com.opensymphony.xwork2.ActionContext;
import com.pro.entity.Clazz;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Date;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;
import com.pro.manager.ClazzManager;
@Controller
public class ClazzAction {
	@Resource
	private ClazzManager clazzManager;
 private int id;
	private String name;
	private String desc;
	private String school;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}


	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		List list = this.clazzManager.queryByHql("from Clazz where name='"+this.name+"' and school='"+this.school+"'");
		if(!CommonUtil.isListEmpty(list)) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, "该院系已经存在该班级了，不能重复添加");
			return ActionSupport.ERROR;
		}
		if(CommonUtil.isEmpty(this.username)) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, "请选择一个教师");
			return ActionSupport.ERROR;
		}
		list = this.clazzManager.queryByHql("from Clazz where username='"+this.username+"'");
		if(!CommonUtil.isListEmpty(list)) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, "该教师已经分配过班级了");
			return ActionSupport.ERROR;
		}
		Clazz obj = new Clazz();
		obj.setName(this.name);
		obj.setDesc(this.desc);
		obj.setUsername(this.username);
		obj.setSchool(this.school);
		try {
			this.clazzManager.insert(obj);
		} catch(Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
			return ActionSupport.ERROR;
		}
		return ActionSupport.SUCCESS;
	}

	public String del() {
//		ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, "班级关联了其他内容，不能进行删除");
//		return ActionSupport.ERROR;
		this.clazzManager.deleteViaId(this.id);
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.clazzManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String update() {
		Clazz pro = this.clazzManager.queryById(this.id);
		pro.setDesc(this.desc);
		pro.setSchool(this.school);
		this.clazzManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String modify() {
		Clazz pro = this.clazzManager.queryById(this.id);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, pro);
		return Const.ACTION_RETURN_QUERY;
	}

	public String query() {
		Clazz pro = new Clazz();
		pro.setName(this.name);
		pro.setSchool(this.school);
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
		Page<Clazz> page = this.clazzManager.getRecords(condition);
		List<Clazz> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query2() {
		return this.query();
	}

}