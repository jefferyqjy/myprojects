package com.pro.entity;

import java.util.Date;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Finance extends BaseEntity {
	
	
	private int id;
	
	
	private String financetype;
	
	
	private String appgenid;
	
	
	private String appnameid;
	
	
	
	private String supname;
	
	
	private int custgenid;
	
	
	private String custnameid;
	
	
	
	private String custname;
	
	
	private Date datefin;
	
	
	private String totalmoney;
	
	
	private String remark;

	private Date datefin1;
	private Date datefin2;
	
	
	public Date getDatefin1() {
		return datefin1;
	}

	public void setDatefin1(Date datefin1) {
		this.datefin1 = datefin1;
	}

	public Date getDatefin2() {
		return datefin2;
	}

	public void setDatefin2(Date datefin2) {
		this.datefin2 = datefin2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFinancetype() {
		return financetype;
	}

	public void setFinancetype(String financetype) {
		this.financetype = financetype;
	}

	public String getAppgenid() {
		return appgenid;
	}

	public void setAppgenid(String appgenid) {
		this.appgenid = appgenid;
	}

	public String getAppnameid() {
		return appnameid;
	}

	public void setAppnameid(String appnameid) {
		this.appnameid = appnameid;
	}

	public String getSupname() {
		return supname;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}

	public int getCustgenid() {
		return custgenid;
	}

	public void setCustgenid(int custgenid) {
		this.custgenid = custgenid;
	}

	public String getCustnameid() {
		return custnameid;
	}

	public void setCustnameid(String custnameid) {
		this.custnameid = custnameid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
