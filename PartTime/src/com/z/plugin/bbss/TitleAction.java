package com.z.plugin.bbss;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
public class TitleAction {
	@Resource
	private TitleManager titleManager;
 private int id;
	
	
	@EntityAnnotation(desc="标题",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String title;
	@EntityAnnotation(desc="内容",  needUpdate=true, isQueryField = true, rule ="CHAR_M")
	private String content;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="时间", needUpdate=false, rule ="DATE_N")
	private Date datetime;

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
	
	

	private DefaultQueryCondition condition;
	private Page page;
	
	@Resource
	private MessageManager messageManager;
	public String tofatie() {
		System.out.println("tofatie*****************************************************************");
		return "fatie";
	}
	public String add() {
		Title entity = new Title();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userid =  "admin";
			if(request.getSession().getAttribute("frontUsername") != null){
				 userid = (String) request.getSession().getAttribute("frontUsername");
			}
			
			entity.setContent(this.content);
			entity.setUserid(userid);
			//entity.setUsername(this.username);
			entity.setUsername(userid);
			entity.setDatetime(this.datetime);
			entity.setTitle(this.title);
			this.titleManager.add(entity);
			content="";
			userid ="";
			username = "";
			datetime =null;
			title = null;
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			content="";
			userid ="";
			username = "";
			datetime =null;
			title = null;
			return Const.Pages.MAPPING_URL;
		}
	}

	public String add4front() {
		Title entity = new Title();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userid =  "admin";
			if(request.getSession().getAttribute("frontUsername") != null){
				 userid = (String) request.getSession().getAttribute("frontUsername");
			}
			
			entity.setContent(this.content);
			entity.setUserid(userid);
			//entity.setUsername(this.username);
			entity.setUsername(userid);
			entity.setDatetime(this.datetime);
			entity.setTitle(this.title);
			this.titleManager.add(entity);
			content="";
			userid ="";
			username = "";
			datetime =null;
			title = null;
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return "queryDO4front";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			content="";
			userid ="";
			username = "";
			datetime =null;
			title = null;
			return Const.Pages.MAPPING_URL;
		}
	}
	
	public String del() {
		this.titleManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.titleManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}
	public String del4message() {
		this.messageManager.deleteViaId(this.id);
		//ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		//return Const.Pages.QUERY_DO;
		
		Message entity = new Message();
		//entity.setContent(this.content);
		//entity.setUsername(this.username);
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
		Page<Message> page = this.messageManager.getRecords(condition);
		List<Message> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return "toallmessage";
	}
	
	
	public String update() {
		Title entity = this.titleManager.queryById(this.id);
		entity.setContent(this.content);
		entity.setUserid(this.userid);
		this.titleManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Title entity = this.titleManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String edittomessage() {
		/*Title entity = this.titleManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;*/
		
		Message entity = new Message();
		//entity.setContent(this.content);
		//entity.setUsername(this.username);
		entity.setTitle(this.id+"");
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
		Page<Message> page = this.messageManager.getRecords(condition);
		List<Message> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return "toallmessage";
	}
	
	public String detail() {
		Title entity = this.titleManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail4gentie() throws ParseException {
		Title entity2 = this.titleManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity2);
		
		/*Message entity = new Message();
		//entity.setContent(this.content);
		//entity.setUsername(this.username);
		entity.setTitle(this.id+"");
		condition = new DefaultQueryCondition(entity);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter(Page.CURRENT_PAGE);
		String pageSize = request.getParameter(Page.PAGE_SIZE);
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}*/
		List resultList = this.messageManager.queryByHql("from Message where title='"+id+"'");
		for(int i=0;i<resultList.size(); i++){
			Message mm = (Message) resultList.get(i);
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				mm.setDatetimestr(sfd.format(mm.getDatetime()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//List<Message> resultList = page.getList();
		ActionContext.getContext().put("result2", resultList);
		//ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		//ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
		
		//return Const.Pages.MAPPING_URL;
	}
	
	public String huifu(){
		Message entity = new Message();
		this.id = Integer.parseInt(title);
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			entity.setContent(this.content);
			String userid =  "otheruser";
			/*if(request.getSession().getAttribute("frontUsername") != null){
				 userid = (String) request.getSession().getAttribute("frontUsername");
			}*/
			
			if(request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME) != null){
				userid = (String) request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME);
				
			}
			entity.setUserid(userid);
			entity.setUsername(userid);
			//entity.setUserid(this.userid);
			//entity.setUsername(this.username);
			Date dd = new Date();
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setDatetime(sfd.parse(sfd.format(dd)));
			entity.setTitle(this.title);
			this.messageManager.add(entity);
			userid ="";
			username ="";
			content ="";
			//String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			//ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			//return Const.Pages.MAPPING_URL;
			
			Title entity2 = this.titleManager.queryById(this.id);
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity2);
			List resultList = this.messageManager.queryByHql("from Message where title='"+id+"'");
			for(int i=0;i<resultList.size(); i++){
				Message mm = (Message) resultList.get(i);
				//SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				mm.setDatetimestr(sfd.format(mm.getDatetime()));
				
			}
			ActionContext.getContext().put("result2", resultList);
			
			return "detail4gentie";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			//return Const.Pages.MAPPING_URL;
			return "detail4gentie";
		}
	}
	
	
	public String query() {
		Title entity = new Title();
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
		Page<Title> page = this.titleManager.getRecords(condition);
		List<Title> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	public String query4front() {
		Title entity = new Title();
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
		Page<Title> page = this.titleManager.getRecords(condition);
		List<Title> resultList = page.getList();
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