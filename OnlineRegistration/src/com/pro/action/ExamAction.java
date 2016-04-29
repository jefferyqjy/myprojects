package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.Exam;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import com.pro.manager.ExamManager;
@Controller
public class ExamAction {
	@Resource
	private ExamManager examManager;
 private int id;
	
	@EntityAnnotation(desc="编号", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String number;
	
	@EntityAnnotation(desc="科目", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String subject;
	
	@EntityAnnotation(desc="时间", needUpdate=true, rule ="DATE_M")
	private Date date;
	
	@EntityAnnotation(desc="地点", isQueryField=false, length=20, needUpdate=true, rule="CHAR_M_20")
	private String place;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		Exam entity = new Exam();
		Exam temp = this.examManager.querySingleRecordViaKeys("number",number);
		if(temp != null ){
			ActionContext.getContext().put(Const.Notification.ERROR, "考试编号:"+number+" 已存在！");
			number="";
			subject="";
			date=null;
			place="";
			return Const.Pages.MAPPING_URL;
		}
		try {
			entity.setNumber(this.number);
			entity.setSubject(this.subject);
			entity.setDate(this.date);
			entity.setPlace(this.place);
			this.examManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.examManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.examManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Exam entity = this.examManager.queryById(this.id);
		entity.setDate(this.date);
		entity.setPlace(this.place);
		this.examManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Exam entity = this.examManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		Exam entity = new Exam();
		if ("student".equalsIgnoreCase(type)) {
			String temp = URLDecoder.decode(request.getParameter("examId")); 
			String number = "";
			try {
				number = new String(temp.getBytes("ISO-8859-1"), "utf8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			entity = this.examManager.querySingleRecordViaKey("number",number);
			request.setAttribute("type", type);
		} else {
			entity = this.examManager.queryById(this.id);
		}
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		Exam entity = new Exam();
		if ("student".equalsIgnoreCase(type)) {
			String temp = URLDecoder.decode(request.getParameter("examId")); 
			String number = "";
			try {
				number = new String(temp.getBytes("ISO-8859-1"), "utf8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			entity.setNumber(number);
		} else {
			entity.setNumber(this.number);
			entity.setSubject(this.subject);
		}
		condition = new DefaultQueryCondition(entity);
		String curPage = request.getParameter(Page.CURRENT_PAGE);
		String pageSize = request.getParameter(Page.PAGE_SIZE);
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Exam> page = this.examManager.getRecords(condition);
		List<Exam> resultList = page.getList();
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