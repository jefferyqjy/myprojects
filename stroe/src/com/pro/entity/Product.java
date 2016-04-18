package com.pro.entity;

import java.util.List;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Product extends BaseEntity {

	
	private int id;
	
	
	private String nameid;
	
	
	
	private String name;
	
	
	
	private String suppliernaem;
	
	
	private String suppliergenid;
	
	
	private String supplierid;
	
	
	
	
	private String remark;

	private List suplist;
	
	
	public List getSuplist() {
		return suplist;
	}

	public void setSuplist(List suplist) {
		this.suplist = suplist;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
