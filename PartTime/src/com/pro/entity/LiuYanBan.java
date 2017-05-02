package com.pro.entity;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class LiuYanBan extends BaseEntity {

	//有的客户也可以发表自己的消费体验，提供一个交流平台的作用）：用户留言—联系人、联系电话、电子邮箱、意向描述等讯息。
	@EntityAnnotation(needShow=false,beanName="留言板")
	private int id;
	
	@EntityAnnotation(desc="标题",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String title;
	
	@EntityAnnotation(desc="联系人",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String lianxiren;
	
	@EntityAnnotation(desc="联系电话",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String lianxidianhua;
	
	@EntityAnnotation(desc="电子邮箱",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String email;
	
	@EntityAnnotation(desc="留言内容",needUpdate=false, isQueryField = false,rule="CHAR_M_1024")
	private String content;
	
	@EntityAnnotation(desc="发布日期",rule="DATE_M")
	private String date;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="状态",needUpdate=false,isQueryField=true,rule ="SELE_M;未回复;已回复")
	private String status;
	
	@EntityAnnotation(desc="留言回复",needUpdate=false, isQueryField = false,rule="CHAR_N_1024")
	private String remark;

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

	public String getLianxiren() {
		return lianxiren;
	}

	public void setLianxiren(String lianxiren) {
		this.lianxiren = lianxiren;
	}

	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
