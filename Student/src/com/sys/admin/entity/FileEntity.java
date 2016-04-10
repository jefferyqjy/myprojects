package com.sys.admin.entity;

import java.util.Date;

import com.sys.common.BaseEntity;

public class FileEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**生成的唯一标识文件的文件名，UUID*/
	private String id;
	/**原始文件名*/
	private String name;
	/**文件类型*/
	private String type;
	/**文件大小*/
	private String size;
	/**所属领域，生物？物理？*/
	private String area;
	/**保存地址*/
	private String path;
	/**标题*/
	private String title;
	/**内容*/
	private String content;
	/**MD5码*/
	private String md5;
	/**上传时间*/
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
