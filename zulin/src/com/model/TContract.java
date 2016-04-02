package com.model;

import java.util.Date;


/**
 * contract management
 */
public class TContract implements java.io.Serializable{

	/**
	 */
	private static final long serialVersionUID = 6884509998114036277L;
	
	private Integer contractId;
	
	private Integer adminId;
	
	private String adminName;
	
	private Integer userId;
	
	private String userName;
	
	private String content;
	
	private Integer status;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer expired;
	
	private Integer zulinId;
	
	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getExpired() {
		return expired;
	}

	public void setExpired(Integer expired) {
		this.expired = expired;
	}

	public Integer getZulinId() {
		return zulinId;
	}

	public void setZulinId(Integer zulinId) {
		this.zulinId = zulinId;
	}

}
