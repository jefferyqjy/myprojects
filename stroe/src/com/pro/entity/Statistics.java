package com.pro.entity;

import com.base.sys.entity.BaseEntity;

public class Statistics extends BaseEntity {
	
	private String name;
	private String type;
	
	private String amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
