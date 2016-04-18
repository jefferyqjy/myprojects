package com.pro.entity;

import java.util.Date;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Saleproduct extends BaseEntity {

	
	private int id;
	
	
	private String productid;
	
	
	private String productgenid;
	
	
	private String productname;
	
	
	private String orderstatus;
	
	
	private String amount;
	
	
	private String totalmoney;
	
	
	private Date datesale;
	
	
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductgenid() {
		return productgenid;
	}

	public void setProductgenid(String productgenid) {
		this.productgenid = productgenid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public Date getDatesale() {
		return datesale;
	}

	public void setDatesale(Date datesale) {
		this.datesale = datesale;
	}
	
	
}
