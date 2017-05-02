package com.z.plugin.jfile;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Jfile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653521216044770650L;
	@EntityAnnotation(beanName = "文件", needShow = false, needUpdate = false)
	private int id;
	@EntityAnnotation(desc = "文件标识", needShow = false, needUpdate = false)
	private String uuid;
	@EntityAnnotation(desc = "名称", isDetailLink = true, isQueryField = true, needUpdate = false)
	private String oriname;
	@EntityAnnotation(desc = "现有名称", needShow = false, needUpdate = false)
	private String curname;
	@EntityAnnotation(desc = "类型", needUpdate = false)
	private String type;
	@EntityAnnotation(desc = "大小", needUpdate = false)
	private String size;
	@EntityAnnotation(desc = "上传时间", needUpdate = false)
	private String date;
	@EntityAnnotation(desc = "用户名", needShow = false, needUpdate = false)
	private String authorid;
	@EntityAnnotation(desc = "昵称", needShow = false, needUpdate = false)
	private String authorname;
	@EntityAnnotation(desc = "描述")
	private String remark;
	@EntityAnnotation(desc = "所在路径", needShow = false, needUpdate = false)
	private String path;
	@EntityAnnotation(desc = "校验码", needShow = false, needUpdate = false)
	private String md5;
	@EntityAnnotation(desc = "内容", needShow = false, needUpdate = false)
	private String content;
	@EntityAnnotation(desc = "文件分类", needShow = false, needUpdate = false)
	private String area;

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

	public static String getFileType(String fileType) {
		if ("application/msword".equals(fileType)
				|| "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(fileType)) {
			return "application/msword";
		}
		if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(fileType)
				|| "application/vnd.ms-excel".equals(fileType)) {
			return "application/vnd.xls";
		}
		return fileType;
	}

}
