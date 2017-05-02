package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.TakeOffice;
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
import com.pro.manager.TakeOfficeManager;
@Controller
public class TakeOfficeAction {
	@Resource
	private TakeOfficeManager takeOfficeManager;
 private int id;
	@EntityAnnotation(desc="岗位ID",needShow=false, isQueryField=false)
	private String parttimeid;
	
	@EntityAnnotation(desc="岗位标题",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String parttimetitle;
	
	@EntityAnnotation(desc="学生ID",needShow=false,isQueryField=false)
	private String stuid;
	
	@EntityAnnotation(desc="学生用户名",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String stuname;
	
	@EntityAnnotation(desc="状态",  needUpdate=true, isQueryField = true, rule ="SELE_M;兼职中;兼职结束")
	private String status;
	
	@EntityAnnotation(desc="姓名",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String relname;
	
	@EntityAnnotation(desc="联系方式",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String phone;
	
	@EntityAnnotation(desc="开始日期",rule="DATE_N",needUpdate=true,isQueryField=false)
	private String datefrom;
	
	@EntityAnnotation(desc="结束日期",rule="DATE_N",needUpdate=true,isQueryField=false)
	private String dateto;
	
	@EntityAnnotation(desc="备注",  needUpdate=true, isQueryField = false, rule ="CHAR_N")
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParttimeid() {
		return parttimeid;
	}

	public void setParttimeid(String parttimeid) {
		this.parttimeid = parttimeid;
	}

	public String getParttimetitle() {
		return parttimetitle;
	}

	public void setParttimetitle(String parttimetitle) {
		this.parttimetitle = parttimetitle;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateto() {
		return dateto;
	}

	public void setDateto(String dateto) {
		this.dateto = dateto;
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
		TakeOffice entity = new TakeOffice();
		try {
			entity.setParttimeid(this.parttimeid);
			entity.setParttimetitle(this.parttimetitle);
			entity.setStuid(this.stuid);
			entity.setStuname(this.stuname);
			entity.setStatus(this.status);
			entity.setRelname(this.relname);
			entity.setPhone(this.phone);
			entity.setRemark(this.remark);
			entity.setDatefrom(this.datefrom);
			entity.setDateto(this.dateto);
			this.takeOfficeManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.takeOfficeManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.takeOfficeManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		TakeOffice entity = this.takeOfficeManager.queryById(this.id);
		entity.setParttimeid(this.parttimeid);
		entity.setStuid(this.stuid);
		entity.setStatus(this.status);
		entity.setRemark(this.remark);
		entity.setDatefrom(this.datefrom);
		entity.setDateto(this.dateto);
		this.takeOfficeManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		TakeOffice entity = this.takeOfficeManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		TakeOffice entity = this.takeOfficeManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		TakeOffice entity = new TakeOffice();
		entity.setStatus(this.status);
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
		Page<TakeOffice> page = this.takeOfficeManager.getRecords(condition);
		List<TakeOffice> resultList = page.getList();
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