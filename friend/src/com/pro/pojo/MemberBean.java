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
	
	private String universityId;
	
	private String province;
	
	private String city;
	
	private String subject;
	
	private Integer year;
	
	private String stuNo;
	
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

}
