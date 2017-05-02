package com.pro.entity;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class PartTimeInfo extends BaseEntity {

	@EntityAnnotation(needShow=false,beanName="岗位信息")
	private int id;
	
	@EntityAnnotation(desc="职位类别",rule="CHAR_M_120",needUpdate=false,isQueryField=true)
	private String types;
	
	@EntityAnnotation(desc="名称",rule="CHAR_M_120",isDetailLink=true,needUpdate=false,isQueryField=true)
	private String title;
	
	@EntityAnnotation(desc="工作地点",rule="CHAR_M_120",needUpdate=true,isQueryField=true)
	private String workadd;
	
	@EntityAnnotation(desc="薪资标准",rule="CHAR_M_120",needUpdate=true,isQueryField=false)
	private String money;
	
	@EntityAnnotation(desc="联系人",rule="CHAR_M_120",needUpdate=true,isQueryField=false)
	private String linkname;
	
	@EntityAnnotation(desc="联系方式",rule="CHAR_M_120",needUpdate=true,isQueryField=false)
	private String phone;
	
	@EntityAnnotation(desc="岗位要求",needUpdate=true, isQueryField = false,rule="CHAR_M_1024")
	private String content;
	
	@EntityAnnotation(desc="发布日期",rule="DATE_M")
	private String date;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="状态",  needUpdate=true, isQueryField = true, rule ="SELE_M;正在招聘;招聘结束")
	private String status;
	
	@EntityAnnotation(desc="备注",  needUpdate=true, isQueryField = false, rule ="CHAR_N")
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWorkadd() {
		return workadd;
	}

	public void setWorkadd(String workadd) {
		this.workadd = workadd;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
