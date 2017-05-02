package com.base.sys.entity;

import java.util.Date;
import java.util.List;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Messages extends BaseEntity {

	@EntityAnnotation(beanName="留言信息", needShow = false)
	private int id;
	
	
	@EntityAnnotation(desc="标题",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String title;
	@EntityAnnotation(desc="内容",  needUpdate=true, isQueryField = true, rule ="CHAR_M")
	private String content;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者genid",  needShow = false)
	private String usergenid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="时间", needUpdate=false, rule ="DATE_N")
	private Date datetime;
	
	@EntityAnnotation(desc="回复", needUpdate=true)
	private String reply;

	
	private String replier;
	
	public String getReplier() {
		return replier;
	}

	public void setReplier(String replier) {
		this.replier = replier;
	}

	private List returnlist;
	
	
	public List getReturnlist() {
		return returnlist;
	}

	public void setReturnlist(List returnlist) {
		this.returnlist = returnlist;
	}

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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsergenid() {
		return usergenid;
	}

	public void setUsergenid(String usergenid) {
		this.usergenid = usergenid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}
