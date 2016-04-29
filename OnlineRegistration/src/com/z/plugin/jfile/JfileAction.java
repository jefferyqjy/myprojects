package com.z.plugin.jfile;

import com.base.common.util.Const;
import com.base.common.util.DateUtil;
import com.base.common.util.SessionManager;
import com.base.common.util.UUIDGenerator;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
public class JfileAction {
	@Resource
	private JfileManager jfileManager;
	private int id;
	private String uuid;
	private String oriname;
	private String curname;
	private String type;
	private String size;
	private String date;
	private String authorid;
	private String authorname;
	private String remark;
	private String path;
	private String md5;
	private String content;
	private String area;
	private File[] attach;
	private String[] attachFileName;
	private String[] attachContentType;
	private DefaultQueryCondition condition;
	private Page page;

	public String add() {
		File[] files = this.getAttach();
		if (files == null || files.length == 0) {
			ActionContext.getContext().put(Const.Notification.ERROR, "没有选择任何文件");
			return Const.Pages.MAPPING_URL;
		}
		String result = validation();
		if (CommonUtil.isNotEmpty(result)) {
			ActionContext.getContext().put(Const.Notification.ERROR, result);
			return Const.Pages.MAPPING_URL;
		}
		try {
			return executeUpload();
		} catch (Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	private String validation() {
		return "";
	}
	
	public String template4excel() {
		String fileName = ServletActionContext.getRequest().getParameter("tempFileName");
		String filePath = SessionManager.getAttachFilePath(fileName);
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(filePath, "模板", Const.FileContentType.EXCEL, response);
		return null;
	}
	
	public String download() {
		Jfile file = this.jfileManager.queryById(this.id);
		String newFileName = file.getCurname();
		String oriFileName = file.getOriname();
		HttpServletRequest request = ServletActionContext.getRequest();
		String filePath = request.getSession().getServletContext().getRealPath("") + "\\attachment\\";
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(filePath + newFileName, oriFileName, file.getType(), response);
		return null;
	}
	
	public String download2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Jfile file = this.jfileManager.querySingleRecordViaKey("curname", request.getParameter("fileName"));
		String newFileName = file.getCurname();
		String oriFileName = file.getOriname();
		String filePath = request.getSession().getServletContext().getRealPath("") + "\\attachment\\";
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(filePath + newFileName, oriFileName, file.getType(), response);
		return null;
	}

	public String executeUpload() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String filePath = request.getSession().getServletContext().getRealPath("") + "\\attachment\\";
		File[] files = getAttach();
		Jfile file = null;
		String fileName = null;
		String newFileName = null;
		File uploadFile = null;
		String newFilePath = null;
		File newFile = null;
		for (int i = 0; i < files.length; i++) {
			fileName = this.attachFileName[i];
			newFileName = UUIDGenerator.genFileName().concat(CommonUtil.getFileSuffix(fileName));
			uploadFile = this.attach[i];
			newFilePath = filePath + newFileName;
			newFile = new File(newFilePath);
			FileUtils.copyFile(uploadFile, newFile);
			file = new Jfile();
			file.setOriname(fileName);
			file.setCurname(newFileName);
			file.setPath(newFilePath);
			file.setSize(String.valueOf(newFile.length()));
			file.setDate(DateUtil.convDate2String(new Date()));
			file.setType(Jfile.getFileType(this.attachContentType[i]));
			file.setArea(this.area);
			file.setAuthorid(SessionManager.getAdminUsername());
			file.setRemark(this.remark);
			this.jfileManager.add(file);
		}
		String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
		ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
		return Const.Pages.MAPPING_URL;
	}

	public String del() {
		Jfile jfile = this.jfileManager.queryById(this.id);
		String filePath = jfile.getPath();
		this.jfileManager.deleteViaId(this.id);
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for (int i = 0; i < len; i++) {
			this.jfileManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Jfile entity = this.jfileManager.queryById(this.id);
		entity.setRemark(this.remark);
		this.jfileManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Jfile entity = this.jfileManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Jfile entity = this.jfileManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Jfile entity = new Jfile();
		entity.setOriname(this.oriname);
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
		Page<Jfile> page = this.jfileManager.getRecords(condition);
		List<Jfile> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO, page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.QUERY_JSP;
	}

	public String query2() {
		return this.query();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriname() {
		return oriname;
	}

	public void setOriname(String oriname) {
		this.oriname = oriname;
	}

	public String getCurname() {
		return curname;
	}

	public void setCurname(String curname) {
		this.curname = curname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthorid() {
		return authorid;
	}

	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public File[] getAttach() {
		return attach;
	}

	public void setAttach(File[] attach) {
		this.attach = attach;
	}

	public String[] getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String[] attachFileName) {
		this.attachFileName = attachFileName;
	}

	public String[] getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String[] attachContentType) {
		this.attachContentType = attachContentType;
	}

}