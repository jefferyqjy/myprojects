package com.base.pagination.util;

public class DefaultQueryCondition<T> {
	
	private T condition;
	
	public final static int DEFAULT_INT_VALUE = 0;

	public DefaultQueryCondition(T condition)
	{
		super();
		this.condition = condition;
	}

	public int getFirstResultOfPage(int pageIndex, int pageSize) {
		return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
	}

	private boolean resultPaged = true;

	private int pageIndex = 1;

	private int pageSize = 5;

	private String sortByType = "ASC";

	private String sortByValue;

	public int getFirstResult() {
		return getFirstResultOfPage(pageIndex, pageSize);
	}

	public String getSortByType() {
		return sortByType;
	}

	public void setSortByType(String sortByType) {
		this.sortByType = sortByType;
	}

	public String getSortByValue() {
		return sortByValue;
	}

	public void setSortByValue(String sortByValue) {
		this.sortByValue = sortByValue;
	}

	public int getPageIndex() {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isResultPaged() {
		return resultPaged;
	}

	public void setResultPaged(boolean resultPaged) {
		this.resultPaged = resultPaged;
		if (!resultPaged) {
			setPageIndex(1);
			setPageSize(Short.MAX_VALUE);
		} else {
			if (getPageSize() == Short.MAX_VALUE)
				setPageSize(10);
		}
	}

	public T getCondition()
	{
		return condition;
	}

	public void setCondition(T condition)
	{
		this.condition = condition;
	}

}
