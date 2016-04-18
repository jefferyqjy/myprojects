package com.pro.entity;

import java.util.Date;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Employee extends BaseEntity {

	
	private int id;
	
	
	private String nameid;
	
	
	
	private String name;
	
	
	private String sex;
	
	
	private Date datebirth;
	
	
	private String entityid;
	
	
	
	private String address;
	
	
	private String tel;
	
	
	private Date datehire;
	
	
	private String type;
	
	
	
	private String remark;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNameid() {
		return nameid;
	}


	public void setNameid(String nameid) {
		this.nameid = nameid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public Date getDatebirth() {
		return datebirth;
	}


	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}


	public String getEntityid() {
		return entityid;
	}


	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	


	public Date getDatehire() {
		return datehire;
	}


	public void setDatehire(Date datehire) {
		this.datehire = datehire;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	

}
