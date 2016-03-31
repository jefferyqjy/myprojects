package com.pro.db;

import java.util.List;

public class Sql {

	public final static int TYPE_INQUIRE = 1;
	public final static int TYPE_INSERT = 2;
	public final static int TYPE_UPDATE = 3;
	public final static int TYPE_DELETE = 4;

	private String id;
	private int type;
	private String content;
	private boolean simpleSql = true;
	private List<Field> conditionFields;
	private List<Field> outFields;
	private String clause;
	private String dateSource;
	private InquireConvert convert;
	private int curPage;
	private int perPage;
	private boolean paging = false;
	public Sql() {
		this.id = java.util.UUID.randomUUID().toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isSimpleSql() {
		return simpleSql;
	}
	public void setSimpleSql(boolean simpleSql) {
		this.simpleSql = simpleSql;
	}
	public List<Field> getConditionFields() {
		return conditionFields;
	}
	public void setConditionFields(List<Field> conditionFields) {
		this.conditionFields = conditionFields;
	}
	public List<Field> getOutFields() {
		return outFields;
	}
	public void setOutFields(List<Field> outFields) {
		this.outFields = outFields;
	}
	public String getClause() {
		return clause;
	}
	public void setClause(String clause) {
		this.clause = clause;
	}
	public String getDateSource() {
		return dateSource;
	}
	public void setDateSource(String dateSource) {
		this.dateSource = dateSource;
	}
	public InquireConvert getConvert() {
		return convert;
	}
	public void setConvert(InquireConvert convert) {
		this.convert = convert;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public boolean isPaging() {
		return paging;
	}
	public void setPaging(boolean paging) {
		this.paging = paging;
	}

}
