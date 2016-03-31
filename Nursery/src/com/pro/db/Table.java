package com.pro.db;

public class Table {

	private String name;
	private String schema;

	public Table(String name, String schema) {
		this.name = name;
		this.schema = schema;
	}

	public Table() {

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}

}
