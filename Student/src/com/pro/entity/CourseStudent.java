package com.pro.entity;

import com.sys.common.BaseEntity;

public class CourseStudent extends BaseEntity<CourseStudent> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8987388220858527721L;
	private int id;
	private String name;
	private String stuno;
	private Integer year;
	private String type;
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
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
