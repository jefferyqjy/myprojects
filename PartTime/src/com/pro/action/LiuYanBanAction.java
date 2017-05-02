package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.LiuYanBan;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import java.util.List;
import com.pro.manager.LiuYanBanManager;
@Controller
public class LiuYanBanAction {
	@Resource
	private LiuYanBanManager liuYanBanManager;
 private int id;
	
	@EntityAnnotation(desc="标题",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String title;
	
	@EntityAnnotation(desc="联系人",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String lianxiren;
	
	@EntityAnnotation(desc="联系电话",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String lianxidianhua;
	
	@EntityAnnotation(desc="电子邮箱",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String email;
	
	@EntityAnnotation(desc="留言内容",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String content;
	
	@EntityAnnotation(desc="发布日期",rule="DATE_M")
	private String date;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="状态",needUpdate=false,isQueryField=true,rule ="SELE_M;未回复;已回复")
	private String status;
	
	@EntityAnnotation(desc="留言回复",needUpdate=false, isQueryField = false,rule="CHAR_N_1024")
	private String remark;

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

	public String getLianxiren() {
		return lianxiren;
	}

	public void setLianxiren(String lianxiren) {
		this.lianxiren = lianxiren;
	}

	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		LiuYanBan entity = new LiuYanBan();
		try {
			entity.setStatus(this.status);
			entity.setContent(this.content);
			entity.setDate(this.date);
			entity.setRemark(this.remark);
			entity.setTitle(this.title);
			entity.setLianxiren(this.lianxiren);
			entity.setLianxidianhua(this.lianxidianhua);
			entity.setEmail(this.email);
			entity.setUserid(this.userid);
			entity.setUsername(this.username);
			this.liuYanBanManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String add4front() {
		LiuYanBan entity = new LiuYanBan();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("frontUsername") == null){
			ActionContext.getContext().put(Const.Notification.ERROR, "请重新登陆！");
			return Const.Pages.MAPPING_URL;
		}
		String loginid = (String) request.getSession().getAttribute("frontUsername");

		
		try {
			entity.setStatus("未回复");
			entity.setContent(this.content);
			entity.setDate(this.date);
			//entity.setRemark(this.remark);
			entity.setTitle(this.title);
			
			entity.setLianxiren(this.lianxiren);
			entity.setLianxidianhua(this.lianxidianhua);
			entity.setEmail(this.email);
			entity.setUserid(loginid);
			entity.setUsername(loginid);
			this.liuYanBanManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}
	
	public String del() {
		this.liuYanBanManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.liuYanBanManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		LiuYanBan entity = this.liuYanBanManager.queryById(this.id);
		//entity.setDate(this.date);
		//entity.setUserid(this.userid);
		entity.setStatus(status);
		entity.setRemark(remark);
		this.liuYanBanManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		LiuYanBan entity = this.liuYanBanManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		LiuYanBan entity = this.liuYanBanManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}
	
	public String detail4front() {
		LiuYanBan entity = this.liuYanBanManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		LiuYanBan entity = new LiuYanBan();
		entity.setStatus(this.status);
		entity.setUsername(this.username);
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
		Page<LiuYanBan> page = this.liuYanBanManager.getRecords(condition);
		List<LiuYanBan> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	public String query4front() {
		LiuYanBan entity = new LiuYanBan();
		//entity.setStatus(this.status);
		//entity.setUsername(this.username);
		entity.setTitle(title);
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
		Page<LiuYanBan> page = this.liuYanBanManager.getRecords(condition);
		List<LiuYanBan> resultList = page.getList();
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