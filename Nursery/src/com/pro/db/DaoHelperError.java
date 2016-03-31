package com.pro.db;

public class DaoHelperError {

	public DaoHelperError() {

	}

	public DaoHelperError(String code, String desc, String suggest) {
		this.code = code;
		this.desc = desc;
		this.suggest = suggest;
	}

	private String code;
	private String desc;
	private String suggest;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

}
