package com.base.sys.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;

import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Admin;
import com.base.sys.entity.Messages;
import com.base.sys.manager.AdminManager;
import com.base.sys.manager.MessagesManager;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
//import com.pro.entity.Studentinfo;
//import com.pro.manager.StudentinfoManager;
//import com.pro.manager.TeacherManager;
//import com.pro.manager.UserfilesManager;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MessagesAction {
	@Resource
	private MessagesManager messagesManager;
	
	@Resource
	private AdminManager adminManager;
	//@Resource
	//private UserfilesManager userfilesManager;
	
	//@Resource
	//private TeacherManager teacherManager;
	
	//@Resource
	//private StudentinfoManager studentinfoManager;
	
 private int id;
	
	
	@EntityAnnotation(desc="标题",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String title;
	@EntityAnnotation(desc="内容",  needUpdate=true, isQueryField = true, rule ="CHAR_M")
	private String content;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者genid",  needShow = false)
	private String usergenid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="时间", needUpdate=false, rule ="DATE_N")
	private Date datetime;
	
	@EntityAnnotation(desc="回复", needUpdate=true)
	private String reply;

	private String replier;
	
	public String getReplier() {
		return replier;
	}

	public void setReplier(String replier) {
		this.replier = replier;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsergenid() {
		return usergenid;
	}

	public void setUsergenid(String usergenid) {
		this.usergenid = usergenid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		Messages entity = new Messages();
		try {
			entity.setContent(this.content);
			entity.setUserid(this.userid);
			entity.setUsergenid(this.usergenid);
			entity.setUsername(this.username);
			entity.setDatetime(this.datetime);
			entity.setReply(this.reply);
			entity.setTitle(this.title);
			this.messagesManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			content ="";
			username = "";
			title = "";
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String add2() {
		Messages entity = new Messages();
		try {
			entity.setContent(this.content);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String stuid = (String) request.getSession().getAttribute("frontUsername");
			Admin admin = this.adminManager.getAdminByUsername(CommonUtil.genUTF8String(stuid));
			//List<Studentinfo> list = studentinfoManager.queryByHql("from Studentinfo where nameid ='"+stuid+"'");
			//Studentinfo tea = new Studentinfo();
			//if(list != null){
			//	tea = list.get(0);
			//}
			
			entity.setUserid(stuid);
			entity.setUsergenid(stuid);
			
			//entity.setUsername(tea.getRealname());
			entity.setDatetime(this.datetime);
			entity.setReply("");
			entity.setTitle(this.title);
			this.messagesManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			content ="";
			username = "";
			title = "";
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}
	
	public String del() {
		this.messagesManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.messagesManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Messages entity = this.messagesManager.queryById(this.id);
		entity.setContent(this.content);
		entity.setUserid(this.userid);
		entity.setUsergenid(this.usergenid);
		entity.setReply(this.reply);
		this.messagesManager.update(entity);

		content ="";
		username = "";
		title = "";
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Messages entity = this.messagesManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Messages entity = this.messagesManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Messages entity = new Messages();
		entity.setContent(this.content);
		entity.setUsername(this.username);
		entity.setTitle(this.title);
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
		Page<Messages> page = this.messagesManager.getRecords(condition);
		List<Messages> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	public String querytaolun() {
		/*Messages entity = new Messages();
		entity.setContent(this.content);
		entity.setUsername(this.username);
		entity.setTitle(this.title);
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
		Page<Messages> page = this.messagesManager.getRecords(condition);
		List<Messages> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());*/
		List list = messagesManager.queryByHql(" from Messages where reply ='' or reply =null");
		
		List<Messages> resultList = messagesManager.queryByHql(" from Messages where reply ='' or reply =null order by datetime DESC");
		/*List<Messages> resultList = new ArrayList<Messages>();
		
		for(int i =0; i <list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Messages msg = new Messages();
			msg.setId(i)
			
		}*/
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}
	
	public String taolun() {
		Messages entity = this.messagesManager.queryById(this.id);
		List<Messages> resultList = messagesManager.queryByHql("from Messages where title ='"+entity.getTitle()+"' and (reply !='' and reply !=null)");
		entity.setReturnlist(resultList);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}
	
	public String addtaolun() {
		Messages entity = this.messagesManager.queryById(this.id);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String stuid = (String) request.getSession().getAttribute("frontUsername");
		Admin admin = this.adminManager.getAdminByUsername(CommonUtil.genUTF8String(stuid));
		//List<Studentinfo> list = studentinfoManager.queryByHql("from Studentinfo where nameid ='"+stuid+"'");
	//	Studentinfo tea = new Studentinfo();
	//	if(list != null){
	//		tea = list.get(0);
	//	}
		
		Messages entity2 = new Messages();
		entity2.setContent(entity.getContent());
		entity2.setUserid(entity.getUserid());
		entity2.setUsergenid(entity.getUsergenid());
		entity2.setUsername(entity.getUsername());
		entity2.setDatetime(entity.getDatetime());
		entity2.setReply(this.reply);
		entity2.setTitle(entity.getTitle());
		//entity2.setReplier(tea.getRealname());
		try {
			this.messagesManager.add(entity2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "querytaolun";
	}
	
	public String query2() {
		return this.query();
 }	public String select() {
		return this.query();
 }}