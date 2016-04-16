package com.pro.enums;

public enum Gender {

	male("ÄÐ"), female("Å®");
	
	private String info;
	
	public String getInfo() {
		return info;
	}
	
	Gender(String info) {
		this.info = info;
	}
}
