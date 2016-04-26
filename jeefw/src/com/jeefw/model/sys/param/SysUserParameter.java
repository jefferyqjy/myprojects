package com.jeefw.model.sys.param;

import core.support.ExtJSBaseParameter;

/**
 * 用户的参数类
 * administrator
 */
public class SysUserParameter extends ExtJSBaseParameter {

	private static final long serialVersionUID = 7656443663108619135L;
	private String sexCn;
	private String roleCn;
	private String role;
	private String statusCn;
	private String $eq_email;
	private String $like_userName;
	
	private String filePath;
	private String departmentValue; // 部门名称
	private boolean rememberMe; // 下次自动登录
	
	private String $eq_name;
	private Integer $eq_age;

	public String getSexCn() {
		return sexCn;
	}

	public void setSexCn(String sexCn) {
		this.sexCn = sexCn;
	}

	public String getRoleCn() {
		return roleCn;
	}

	public void setRoleCn(String roleCn) {
		this.roleCn = roleCn;
	}

	public String getStatusCn() {
		return statusCn;
	}

	public void setStatusCn(String statusCn) {
		this.statusCn = statusCn;
	}

	public String get$eq_email() {
		return $eq_email;
	}

	public void set$eq_email(String $eq_email) {
		this.$eq_email = $eq_email;
	}

	public String get$like_userName() {
		return $like_userName;
	}

	public void set$like_userName(String $like_userName) {
		this.$like_userName = $like_userName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDepartmentValue() {
		return departmentValue;
	}

	public void setDepartmentValue(String departmentValue) {
		this.departmentValue = departmentValue;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String get$eq_name() {
		return $eq_name;
	}

	public void set$eq_name(String $eq_name) {
		this.$eq_name = $eq_name;
	}

	public Integer get$eq_age() {
		return $eq_age;
	}

	public void set$eq_age(Integer $eq_age) {
		this.$eq_age = $eq_age;
	}

}
