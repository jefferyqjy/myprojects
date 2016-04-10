package com.sys.plugin.file;

import com.sys.common.BaseEntity;

public class Jfile extends BaseEntity<Jfile> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//数据库主key,自增长
	private int id;
	//文件唯一标识uuid
	private String uuid;
	//文件大小
	private long size;
	private String name;
	//文件路径
	private String path;
	//新文件名
	private String newname;
	//原始文件名
	private String oriname;
	//被谁上传
	private String uploadby;
	//上传时间
	private String date;
	//文件格式
	private String type;
	//外链id
	private String linkid;
	//md5校验
	private String md5;
	//标题
	private String title;
	//内容,只有txt格式有
	private String content;
	//所属领域
	private String area;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getUploadby() {
		return uploadby;
	}
	public void setUploadby(String uploadby) {
		this.uploadby = uploadby;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

}
