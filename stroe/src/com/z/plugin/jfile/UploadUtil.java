package com.z.plugin.jfile;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.base.common.util.CommonUtil;
import com.base.common.util.UUIDGenerator;

public class UploadUtil {
		
	public static String getAttachPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession().getServletContext().getRealPath("") + "\\attachment\\";
	}
	
	public static String getAttachFilePath(String fileName) {
		return getAttachPath() + fileName;
	}
	
	public static String uploadFile(JfileManager jfileManager,File attach, String attachFileName, String attachContentType) {
		String newFileName = "";
		if(attach != null) {
			String fileName = attachFileName;
			newFileName = UUIDGenerator.genFileName().concat(CommonUtil.getFileSuffix(fileName));
			String newFilePath = getAttachPath() + newFileName;
			File newFile = new File(newFilePath);
			try {
				FileUtils.copyFile(attach, newFile);
				Jfile file = new Jfile();
				file.setOriname(fileName);
				file.setCurname(newFileName);
				file.setPath(newFilePath);
				file.setType(attachContentType);
				jfileManager.add(file);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return newFileName;
	}
}
