package com.pro.entity;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Student extends BaseEntity{
	
	@EntityAnnotation(beanName="学生信息", needShow=false, needUpdate=false)
	private int id;
	
	@EntityAnnotation(desc="学号", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String number;
	
	@EntityAnnotation(desc="姓名", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String name;
	
	@EntityAnnotation(desc="年龄", isQueryField=false, length=20, needUpdate=false, rule="CHAR_M_20")
	private String age;
	
	@EntityAnnotation(desc="性别", isQueryField=false, needUpdate=false,  rule="SELE_M_男;女")
	private String sex;
	
	@EntityAnnotation(desc="学院", isQueryField=false, length=20, needUpdate=true, rule="CHAR_M_20")
	private String collage;
	
	@EntityAnnotation(desc="班级", isQueryField=false, length=20, needUpdate=true, rule="CHAR_M_20")
	private String className;
	
	@EntityAnnotation(desc="编号", isQueryField=false)
	private String examId;
	
	@EntityAnnotation(desc="考试语言", isQueryField=false, needUpdate=true,  rule="SELE_M_Java;C")
	private String language;
	
	@EntityAnnotation(desc="缴费", isQueryField=false, needUpdate=false,  rule="SELE_M_未缴;已缴")
	private String money;
	
	
	
	private String flag = "N";
	
	private String shenhe;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCollage() {
		return collage;
	}

	public void setCollage(String collage) {
		this.collage = collage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
