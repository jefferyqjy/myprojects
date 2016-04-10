package com.pro.pojo;

public class UniversityBean {

	private int id;
	private String name;
	private com.pro.pojo.CityBean city;
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

	public com.pro.pojo.CityBean getCity() {
		return city;
	}

	public void setCity(com.pro.pojo.CityBean city) {
		this.city = city;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
