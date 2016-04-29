package com.base.sys.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.base.common.util.CommonUtil;
import com.base.common.util.Const;
import com.base.common.util.DateUtil;
import com.base.common.util.SessionManager;
import com.base.common.util.UUIDGenerator;
import com.opensymphony.xwork2.ActionContext;
import com.z.plugin.jfile.DownloadUtil;

@Controller
public class DatabaseAction {
	private static final String DB_TYPE_MYSQL = "mysql";
	private String system;
	private String type;
	private String name;
	private String username;
	private String password;
	private String encoding;
	private File attach;
	private String attachContentType;
	private String attachFileName;

	public String backup() {
		if (CommonUtil.isEmpty(this.name)) {
			return CommonUtil.returnErrorMsg("数据库名称不能为空");
		}
		if (CommonUtil.isEmpty(this.username)) {
			return CommonUtil.returnErrorMsg("数据库用户名不能为空");
		}
		if (CommonUtil.isEmpty(this.password)) {
			return CommonUtil.returnErrorMsg("数据库密码不能为空");
		}
		String folder = CommonUtil.getSystemTempFolder();
		String fileName = this.name + "_" + DateUtil.getCurrentDateString() + ".sql";
		String filePath = folder + File.separator + fileName;
		if (DB_TYPE_MYSQL.equalsIgnoreCase(this.type)) {
			backup4Mysql(filePath);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		DownloadUtil.getInstance().download(filePath, fileName, "text/plain", response);
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		return null;
	}

	public String restore() {
		if (CommonUtil.isEmpty(this.username)) {
			return CommonUtil.returnErrorMsg("数据库用户名不能为空");
		}
		if (CommonUtil.isEmpty(this.password)) {
			return CommonUtil.returnErrorMsg("数据库密码不能为空");
		}
		if (this.attach == null) {
			return CommonUtil.returnErrorMsg("SQL文件不能为空");
		}
		if (this.attachFileName.indexOf(".sql") == -1) {
			return CommonUtil.returnErrorMsg("请上传有效的SQL文件");
		}
		String fileName = attachFileName;
		String newFileName = UUIDGenerator.genFileName().concat(CommonUtil.getFileSuffix(fileName));
		String newFilePath = SessionManager.getAttachPath() + newFileName;
		File newFile = new File(newFilePath);
		try {
			FileUtils.copyFile(attach, newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (DB_TYPE_MYSQL.equalsIgnoreCase(this.type)) {
			restore4Mysql(newFilePath);
			if (newFile.exists() && newFile.isFile()) {
				newFile.delete();
			}
		}
		ActionContext.getContext().put(Const.Notification.SUCCESS, "还原成功");
		return Const.Pages.MAPPING_URL;
	}

	private void backup4Mysql(String filePath) {
		// String cmdpath =
		// SessionManager.getAttachFilePath("mysqldump").replaceAll("\\\\",
		// "/");
		StringBuilder command = new StringBuilder();
		command.append("cmd /c mysqldump -u").append(this.username).append(" -p");
		command.append(this.password).append(" --default-character-set=").append(this.encoding).append(
				" --add-drop-database -B ").append(this.name);
		command.append(" -r ").append(filePath.replaceAll("\\\\", "/"));
		try {
			Process process = Runtime.getRuntime().exec(command.toString());
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void restore4Mysql(String filePath) {
		StringBuilder command = new StringBuilder();
		command.append("cmd /c mysql -u").append(this.username).append(" -p");
		command.append(this.password);
		try {
			Process child = Runtime.getRuntime().exec(command.toString());
			OutputStream out = child.getOutputStream();
			String inStr = null;
			StringBuffer sb = new StringBuffer("");
			String outStr = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), this.encoding));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, this.encoding);
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public File getAttach() {
		return attach;
	}

	public void setAttach(File attach) {
		this.attach = attach;
	}

	public String getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}

	public String getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

}
