package com.pro.db;

import java.util.List;

import com.pro.conn.IDaoHelperOutput;

public class DaoHelperOutput implements IDaoHelperOutput {

	private String id;
	private boolean bSuccess;
	private List<Object> results;
	private List<DaoHelperError> errors;

	public DaoHelperOutput() {
		this.id = java.util.UUID.randomUUID().toString();
		this.bSuccess = false;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isbSuccess() {
		return bSuccess;
	}
	public void setbSuccess(boolean bSuccess) {
		this.bSuccess = bSuccess;
	}
	public List<Object> getResults() {
		return results;
	}
	public void setResults(List<Object> results) {
		this.results = results;
	}
	public List<DaoHelperError> getErrors() {
		return errors;
	}
	public void setErrors(List<DaoHelperError> errors) {
		this.errors = errors;
	}

}
