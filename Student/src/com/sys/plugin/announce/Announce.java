package com.sys.plugin.announce;

import java.util.Date;

import com.sys.common.BaseEntity;

public class Announce extends BaseEntity<Announce> {
	//编号，自增长
	private int id;
	//公告标题
	private String title;
	//公告内容
	private String content;
	//公告发布时间
	private String createTime;
	//公告最后修改时间
	private String lastModifyTime;
	//公告被谁修改的
	private String modifyBy;
	//公告是谁发布的
	private String createdUser;
	//公告级别：紧急，中等，低级
	private int level;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
