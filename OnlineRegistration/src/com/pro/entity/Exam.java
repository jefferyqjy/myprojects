package com.pro.entity;

import java.util.Date;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Exam extends BaseEntity{
	
	@EntityAnnotation(beanName="考试信息", needShow=false, needUpdate=false)
	private int id;
	
	@EntityAnnotation(desc="编号", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String number;
	
	@EntityAnnotation(desc="科目", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String subject;
	
	@EntityAnnotation(desc="时间", needUpdate=true, rule ="DATE_M")
	private Date date;
	
	@EntityAnnotation(desc="地点", isQueryField=false, length=20, needUpdate=true, rule="CHAR_M_20")
	private String place;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
