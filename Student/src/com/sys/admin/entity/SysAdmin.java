package com.sys.admin.entity;

import java.util.Date;

import com.sys.common.BaseEntity;


public class SysAdmin extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**是否锁住，0否，1是*/
	private int isLock;
	/**是否登录，0否，1是*/
	private int isLogon;
	/**登录时间*/
	private Date loginTime;
	/**用户类型，2是普通用户，1是管理员，0是超级管理员*/
	private int userType;
	/**创建时间*/
	private Date createTime;
	/**登录出错次数*/
	private int errorTimes;
	/**用户权限*/
	private String power;
	/**用户类型*/
	private int type;
	/**联系地址*/
	private String address;
	/**联系QQ*/
	private String qq;
	/**联系电话*/
	private String phone;
	/**性别*/
	private String gender;
	/**联系邮箱*/
	private String email;
	/**联系姓名*/
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getIsLock() {
		return isLock;
	}
	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}
	public int getIsLogon() {
		return isLogon;
	}
	public void setIsLogon(int isLogon) {
		this.isLogon = isLogon;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getErrorTimes() {
		return errorTimes;
	}
	public void setErrorTimes(int errorTimes) {
		this.errorTimes = errorTimes;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}