package com.pro.enums;

public enum Gender {

	male("��"), female("Ů");
	
	private String info;
	
	public String getInfo() {
		return info;
	}
	
	Gender(String info) {
		this.info = info;
	}
}
