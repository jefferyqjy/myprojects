package com.sys.plugin.announce;

import com.sys.common.util.Const;
import com.sys.common.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@Controller
public class AnnounceAction {
	@Resource
	private AnnounceManager announceManager;
	//编号，自增长
	private int id;
	//公告标题
	private String title;
	//公告内容
	private String content;
	//公告发布时间
	private String createTime;
	//公告最后修改时间
	private String lastModifyTime;
	//公告被谁修改的
	private String modifyBy;
	//公告是谁发布的
	private String createdUser;
	//公告级别：紧急，中等，低级
	private int level;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}


	private DefaultQueryCondition condition;
	private Page page;

	public String add() {
		Announce note = new Announce();
		note.setTitle(this.title);
		note.setContent(this.content);
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userName = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		if(CommonUtil.isEmpty(userName)) {
			return CommonUtil.genActionError("Session Timeout,请重新登录!");
		}
		note.setCreatedUser(userName);
		note.setCreateTime(DateUtil.dateToStr(new Date()));
		note.setLevel(this.level);
		try {
			this.announceManager.insert(note);
		} catch (Exception e) {
			return CommonUtil.genActionError(e);
		}
		return ActionSupport.SUCCESS;
	}
	
	public String update() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userName = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		if(CommonUtil.isEmpty(userName)) {
			return CommonUtil.genActionError("Session Timeout,请重新登录!");
		}
		Announce note = this.announceManager.queryById(this.id);
		note.setLastModifyTime(DateUtil.dateToStr(new Date()));
		note.setModifyBy(userName);
		note.setTitle(this.title);
		note.setContent(this.content);
		note.setLevel(this.level);
		this.announceManager.update(note);
		return Const.ACTION_RETURN_SUCC_CLOSE;
		
	}

	public String modify() {
		Announce note = this.announceManager.queryById(this.id);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, note);
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String del() {
		this.announceManager.deleteViaId(this.id);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}
	
	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.announceManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}
	
	public String detail() {
		Announce note = (Announce)this.announceManager.queryByHQL("from Announce where id="+this.id).get(0);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, note);
		return Const.ACTION_RETURN_QUERY;
	}

	public String query() {
		Announce pro = new Announce();
		pro.setCreateTime(this.createTime);
		pro.setTitle(this.title);
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
		Page<Announce> page = this.announceManager.getRecords(condition);
		List<Announce> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,
				page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}

}