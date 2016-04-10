package com.sys.plugin.file;

import com.sys.common.util.Const;
import com.sys.common.util.DateUtil;
import com.sys.common.util.UUIDGenerator;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Date;
import com.sys.common.util.CommonUtil;
import com.sys.web.download.DownloadUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;

@Controller
public class JfileAction {
	@Resource
	private JfileManager jfileManager;

	private File[] attach;
	private String[] attachFileName;
	private String[] attachContentType;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	private DefaultQueryCondition condition;
	private Page page;
	
	public String download() {
		String fileName = this.jfileManager.queryById(this.id).getNewname();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(fileName, request, response);
		return null;
	}

	public String add() {
		try {
			executeUpload();
		} catch (Exception e) {
			return CommonUtil.genActionError(e);
		}
		return ActionSupport.SUCCESS;
	}

	public void executeUpload() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String filePath = request.getSession().getServletContext().getRealPath(
		"")
		+ "\\attachment\\";
		File[] files = getAttach();
		Jfile file = null;
		String fileName = null;
		String newFileName = null;
		File uploadFile = null;
		String newFilePath = null;
		File newFile = null;
		for (int i = 0; i < files.length; i++) {
			fileName = this.attachFileName[i];
			newFileName = UUIDGenerator.genFileName().concat(
					CommonUtil.getFileSuffix(fileName));
			uploadFile = this.attach[i];
			newFilePath = filePath + newFileName;
			newFile = new File(newFilePath);
			FileUtils.copyFile(uploadFile, newFile);
			
			file = new Jfile();
			file.setOriname(fileName);
			file.setNewname(newFileName);
			file.setPath(newFilePath);
			file.setSize(newFile.length());
			file.setDate(DateUtil.convDate2String(new Date()));
			file.setType(this.attachContentType[i]);
			this.jfileManager.insert(file);
		}
	}


	public String del() {
		Jfile file = this.jfileManager.queryById(this.id);
		String filePath = file.getPath();
		this.jfileManager.deleteViaId(this.id);
		File f = new File(filePath);
		if(f.exists()) {
			f.delete();
		}
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}
	
	public String imageView() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ActionContext.getContext().put("fileName", request.getParameter("fileName"));
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String musicPlay(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ActionContext.getContext().put("fileName", request.getParameter("fileName"));
		ActionContext.getContext().put("type", request.getParameter("type"));
		ActionContext.getContext().put("fileType", request.getParameter("fileType"));
		return Const.ACTION_RETURN_QUERY;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for (int i = 0; i < len; i++) {
			Jfile file = this.jfileManager.queryById(Integer.parseInt(idList[i]));
			String filePath = file.getPath();
			this.jfileManager.deleteViaId(Integer.parseInt(idList[i]));
			File f = new File(filePath);
			if(f.exists()) {
				f.delete();
			}
		}
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String update() {
		Jfile pro = new Jfile();
		this.jfileManager.update(pro);
		return ActionSupport.SUCCESS;
	}

	public String modify() {
//		Jfile pro = this.jfileManager.queryById(this.id);
//		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, pro);
		return Const.ACTION_RETURN_QUERY;
	}

	public String query() {
		Jfile pro = new Jfile();
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
		Page<Jfile> page = this.jfileManager.getRecords(condition);
		List<Jfile> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,
				page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}

}