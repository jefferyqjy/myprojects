package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.ApplyInfo;
import com.pro.entity.PartTimeInfo;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Admin;
import com.base.sys.manager.AdminManager;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.pro.manager.ApplyInfoManager;
import com.pro.manager.PartTimeInfoManager;
@Controller
public class PartTimeInfoAction {
	@Resource
	private PartTimeInfoManager partTimeInfoManager;
 private int id;
	
	@EntityAnnotation(desc="职位类别",rule="CHAR_M_120",needUpdate=false,isQueryField=true)
	private String types;
	
	@EntityAnnotation(desc="名称",rule="CHAR_M_120",isDetailLink=true,needUpdate=false,isQueryField=true)
	private String title;
	
	@EntityAnnotation(desc="工作地点",rule="CHAR_M_120",needUpdate=true,isQueryField=true)
	private String workadd;
	
	@EntityAnnotation(desc="薪资标准",rule="CHAR_M_120",needUpdate=true,isQueryField=false)
	private String money;
	
	@EntityAnnotation(desc="联系人",rule="CHAR_M_120",needUpdate=true,isQueryField=false)
	private String linkname;
	
	@EntityAnnotation(desc="联系方式",rule="CHAR_M_120",needUpdate=true,isQueryField=false)
	private String phone;
	
	@EntityAnnotation(desc="岗位要求",needUpdate=true, isQueryField = false,rule="CHAR_M_1024")
	private String content;
	
	@EntityAnnotation(desc="发布日期",rule="DATE_M")
	private String date;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="状态",  needUpdate=true, isQueryField = true, rule ="SELE_M;正在招聘;招聘结束")
	private String status;
	
	@EntityAnnotation(desc="备注",  needUpdate=true, isQueryField = false, rule ="CHAR_N")
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkadd() {
		return workadd;
	}

	public void setWorkadd(String workadd) {
		this.workadd = workadd;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		PartTimeInfo entity = new PartTimeInfo();
		try {
			entity.setStatus(this.status);
			entity.setPhone(this.phone);
			entity.setContent(this.content);
			entity.setDate(this.date);
			entity.setRemark(this.remark);
			entity.setTitle(this.title);
			entity.setUserid(this.userid);
			entity.setUsername(this.username);
			entity.setTypes(this.types);
			entity.setWorkadd(this.workadd);
			entity.setMoney(this.money);
			entity.setLinkname(this.linkname);
			this.partTimeInfoManager.add(entity);
			
			this.types = null;
			title = null;
			status = null;
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}
	@Resource
	private ApplyInfoManager applyInfoManager;
	
	@Resource
	private AdminManager adminManager;
	//添加一条申请 职位
	public String add2() {
		PartTimeInfo pt = this.partTimeInfoManager.queryById(this.id);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getSession().getAttribute("frontUsername") == null){
			ActionContext.getContext().put(Const.Notification.ERROR, "请重新登陆！");
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, pt);
			return "gotoPartTimeDetails";
		}
		String loginid = (String) request.getSession().getAttribute("frontUsername");
		
		List ll =applyInfoManager.queryByHql("from ApplyInfo where parttimeid='"+id+"' and stuid = '"+loginid+"'");
		if(ll != null && ll.size() > 0){
			ActionContext.getContext().put(Const.Notification.ERROR, "你已经申请过改职位！");
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, pt);
			return "gotoPartTimeDetails";
		}
		
		Admin admin = this.adminManager.getAdminByUsername(loginid);
		ApplyInfo entity = new ApplyInfo();
		try {
			entity.setParttimeid(pt.getId()+"");
			entity.setParttimetitle(pt.getTitle());
			entity.setStuid(loginid);
			entity.setStuname(loginid);
			entity.setStatus("提交申请");
			//entity.setRelname(this.relname);
			entity.setPhone(pt.getPhone());
			entity.setContent(admin.getRemark());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			entity.setDate(sf.format(new Date()));
			entity.setRemark("");
			this.applyInfoManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, pt);
			return "gotoPartTimeDetails";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, pt);
			return "gotoPartTimeDetails";
		}
	}

	public String del() {
		this.partTimeInfoManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.partTimeInfoManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		PartTimeInfo entity = this.partTimeInfoManager.queryById(this.id);
		entity.setStatus(this.status);
		entity.setPhone(this.phone);
		entity.setContent(this.content);
		entity.setDate(this.date);
		entity.setRemark(this.remark);
		entity.setUserid(this.userid);
		entity.setWorkadd(this.workadd);
		entity.setMoney(this.money);
		entity.setLinkname(this.linkname);
		this.partTimeInfoManager.update(entity);
		

		this.types = null;
		title = null;
		status = null;
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		PartTimeInfo entity = this.partTimeInfoManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		PartTimeInfo entity = this.partTimeInfoManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}
	
	public String detail4front() {
		PartTimeInfo entity = this.partTimeInfoManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		PartTimeInfo entity = new PartTimeInfo();
		entity.setStatus(this.status);
		entity.setTitle(this.title);
		//entity.setUsername(this.username);
		entity.setTypes(this.types);
		//entity.setWorkadd(this.workadd);
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
		Page<PartTimeInfo> page = this.partTimeInfoManager.getRecords(condition);
		List<PartTimeInfo> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}
	public String query4front() {
		PartTimeInfo entity = new PartTimeInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		/*String userid = "----";
		if(request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME) != null){
			userid = (String) request.getSession().getAttribute(Const.Session.ADMIN_USER_NAME);
		}
		
		entity.setUsername(userid);*/
		//entity.setTypes(this.types);
		entity.setTitle(this.title);
		//entity.setStatus(this.status);
		condition = new DefaultQueryCondition(entity);
		//HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter(Page.CURRENT_PAGE);
		String pageSize = request.getParameter(Page.PAGE_SIZE);
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<PartTimeInfo> page = this.partTimeInfoManager.getRecords(condition);
		List<PartTimeInfo> resultList = page.getList();
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