package com.pro.pojo;

public class MemberBean {

	private int id;
	private String userName;
	private String userPassword;
	private String gender;
	private int age;
	private String address;
	private com.pro.pojo.UniversityBean university;
	private java.util.List<com.pro.pojo.InterestBean> interest = new java.util.ArrayList<com.pro.pojo.InterestBean>();
	private String checked;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public com.pro.pojo.UniversityBean getUniversity() {
		return university;
	}

	public void setUniversity(com.pro.pojo.UniversityBean university) {
		this.university = university;
	}

	public java.util.List<com.pro.pojo.InterestBean> getInterest() {
		return interest;
	}

	public void setInterest(java.util.List<com.pro.pojo.InterestBean> interest) {
		this.interest = interest;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
