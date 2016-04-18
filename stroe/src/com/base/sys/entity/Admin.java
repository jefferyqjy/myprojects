package com.base.sys.entity;

import java.util.Date;


public class Admin extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -107543431166818656L;
	private String username;
	private String password;
	private String usertype;
	private String telephone;
	private String email;
	private String address;
	private String qq;
	private String name;
	private String gender;
	private int age;
	private String isLocked;
	private String isLogon;
	private Date lastLoginTime;
	private Date accountCreateTime;
	private int passErrorTimes;
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAccountCreateTime() {
		return accountCreateTime;
	}
	public void setAccountCreateTime(Date accountCreateTime) {
		this.accountCreateTime = accountCreateTime;
	}
	public String getIsLogon() {
		return isLogon;
	}
	public void setIsLogon(String isLogon) {
		this.isLogon = isLogon;
	}
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getPassErrorTimes() {
		return passErrorTimes;
	}
	public void setPassErrorTimes(int passErrorTimes) {
		this.passErrorTimes = passErrorTimes;
	}
	
	
}
