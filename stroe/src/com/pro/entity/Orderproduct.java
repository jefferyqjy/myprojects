package com.pro.entity;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Orderproduct extends BaseEntity {

	
	private int id;
	
	
	private String orderid;
	
	
	private String orderstatus;
	
	
	private String dateorder;
	
	
	private String datefinished;
	
	
	
	private String productid;
	
	
	private String productgenid;
	
	
	private String productname;
	
	
	private String suppliernaem;
	
	
	private String suppliergenid;
	
	
	private String supplierid;
	
	
	private String amount;
	
	
	private String datebirth;
	
	
	private String datequality;
	
	
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getDateorder() {
		return dateorder;
	}

	public void setDateorder(String dateorder) {
		this.dateorder = dateorder;
	}

	public String getDatefinished() {
		return datefinished;
	}

	public void setDatefinished(String datefinished) {
		this.datefinished = datefinished;
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

	public String getSuppliernaem() {
		return suppliernaem;
	}

	public void setSuppliernaem(String suppliernaem) {
		this.suppliernaem = suppliernaem;
	}

	public String getSuppliergenid() {
		return suppliergenid;
	}

	public void setSuppliergenid(String suppliergenid) {
		this.suppliergenid = suppliergenid;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(String datebirth) {
		this.datebirth = datebirth;
	}

	public String getDatequality() {
		return datequality;
	}

	public void setDatequality(String datequality) {
		this.datequality = datequality;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
