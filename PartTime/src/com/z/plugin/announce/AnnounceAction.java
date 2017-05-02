package com.z.plugin.announce;
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

import java.util.ArrayList;
import java.util.List;
@Controller
public class AnnounceAction {
	@Resource
	private AnnounceManager announceManager;
 private int id;
	@EntityAnnotation(desc="公告标题",rule="CHAR_M_120",isDetailLink=true,needUpdate=false,isQueryField=true)
	private String title;
	@EntityAnnotation(desc="公告内容",rule="CHAR_M_1024")
	private String content;
	@EntityAnnotation(desc="发布日期",rule="DATE_M")
	private String date;
	@EntityAnnotation(desc="公告级别",rule="RADI_M_高级;中级;低级")
	private String level;
	@EntityAnnotation(desc="测试复选框",rule="CHEC_M_是;否")
	private String sync;
	public String getSync() {
		return sync;
	}
	public void setSync(String sync) {
		this.sync = sync;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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


	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		Announce entity = new Announce();
		try {
			entity.setSync(this.sync);
			entity.setLevel(this.level);
			entity.setDate(this.date);
			entity.setTitle(this.title);
			entity.setContent(this.content);
			this.announceManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.announceManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.announceManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Announce entity = this.announceManager.queryById(this.id);
		entity.setSync(this.sync);
		entity.setLevel(this.level);
		entity.setDate(this.date);
		entity.setContent(this.content);
		this.announceManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Announce entity = this.announceManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Announce entity = this.announceManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail2() {
		Announce entity = this.announceManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}
	
	public String query() {
		Announce entity = new Announce();
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
		Page<Announce> page = this.announceManager.getRecords(condition);
		List<Announce> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.QUERY_JSP;
	}

	public String query2() {
		Announce entity = new Announce();
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
		Page<Announce> page = this.announceManager.getRecords(condition);
		List<Announce> resultList  = new ArrayList<Announce>();
		if(page != null){
			resultList = page.getList();
		
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		}
		return "queryJSP2";
 }}