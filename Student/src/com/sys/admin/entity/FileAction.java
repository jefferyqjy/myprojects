package com.sys.admin.entity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sys.common.util.Const;
import com.sys.common.util.UUIDGenerator;
import com.sys.web.download.DownloadUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;

@Controller
public class FileAction {
	private File attach;
	private String attachFileName;
	private String attachContentType;
	
	@Resource private FileManager fileManager;
	
	private String id;
	private String name;
	private DefaultQueryCondition condition;
	
	public String download() {
		String fileName = this.id;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(fileName, request, response);
		return null;
	}

	public String upload() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String filePath = request.getSession().getServletContext().getRealPath(
				"")
				+ "\\attachment\\";
		String newFileName = UUIDGenerator.genFileName().concat(this.getFileSuffix(this.attachFileName));
		File saveFile = new File(filePath+newFileName);
		try {
			FileUtils.copyFile(this.attach, saveFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

	private String getFileSuffix(String fileName) {
		String suffix = "";
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			suffix = fileName.substring(index, fileName.length());
		}
		return suffix;
	}
	
	public String query() {
		String userType = (String)ActionContext.getContext().getSession().get(Const.ACTION_PUT_SESSION_USRE_TYPE);
		FileEntity file = new FileEntity();
		file.setName(this.name);
		this.condition = new DefaultQueryCondition(file);
		List list = this.fileManager.query(condition);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, list);
		ActionContext.getContext().put("uType", userType);
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String download2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("fileName");
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(fileName, request, response);
		return null;
	}
	
	public String del() {
		try {
			this.fileManager.deleteFile(this.id);
			return Const.ACTION_RETURN_SUCCESS;
		}catch(Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
			return Const.ACTION_RETURN_ERROR;
		}
	}
	
	public File getAttach() {
		return attach;
	}

	public void setAttach(File attach) {
		this.attach = attach;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
	
	public String getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DefaultQueryCondition getCondition() {
		return condition;
	}

	public void setCondition(DefaultQueryCondition condition) {
		this.condition = condition;
	}



}
