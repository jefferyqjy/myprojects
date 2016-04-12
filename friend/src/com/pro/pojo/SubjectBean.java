package com.pro.pojo;

public class SubjectBean {

	private int id;
	private String name;
	private Integer universityId;
	private UniversityBean university;
	private String checked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	public Integer getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Integer universityId) {
		this.universityId = universityId;
	}

	public UniversityBean getUniversity() {
		return university;
	}

	public void setUniversity(UniversityBean university) {
		this.university = university;
	}

}
