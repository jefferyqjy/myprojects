package com.base.sys.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.base.common.util.SessionManager;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Shortcut;
import com.base.sys.manager.ShortcutManager;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
public class ShortcutAction {
	@Resource
	private ShortcutManager shortcutManager;
 private static final long serialVersionUID = 7447574369431412501L;
	@EntityAnnotation(beanName = "快捷方式", needShow = false)
	private int id;
	@EntityAnnotation(desc = "快捷键名称", isDetailLink = true, isQueryField = true, rule = "CHAR_M_200")
	private String name;
	@EntityAnnotation(desc = "快捷键地址", rule = "CHAR_M_200")
	private String url;
	@EntityAnnotation(needShow = false, needUpdate = false)
	private String username;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		Shortcut entity = new Shortcut();
		try {
			entity.setName(this.name);
			entity.setUrl(this.url);
			this.username = SessionManager.getAdminUsername();
			entity.setUsername(this.username);
			this.shortcutManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.shortcutManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.shortcutManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Shortcut entity = this.shortcutManager.queryById(this.id);
		entity.setName(this.name);
		entity.setUrl(this.url);
		this.shortcutManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Shortcut entity = this.shortcutManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Shortcut entity = this.shortcutManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Shortcut entity = new Shortcut();
		entity.setName(this.name);
		entity.setUsername(SessionManager.getAdminUsername());
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
		Page<Shortcut> page = this.shortcutManager.getRecords(condition);
		List<Shortcut> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.QUERY_JSP;
	}

	public String query2() {
		return this.query();
 }}