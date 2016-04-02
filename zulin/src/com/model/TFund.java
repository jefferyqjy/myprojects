package com.model;

import java.math.BigDecimal;

/**
 * fund management
 */
public class TFund implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7410454642148952180L;

	private Integer id;
	
	private Integer contractId;
	
	private BigDecimal money;
	
	private Integer type;
	
	private Integer status; 
	
	private Integer payType; // is return ? : 0:no, 1:yes
	
	private String receiptNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
}
