package com.sys.web.fenye.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = -3278063729771011997L;

	private List<T> list = null;

	private int nextPage = 0;

	private int previousPage = 0;

	private boolean hasNext = false;

	private boolean hasPrevious = false;

	private int totalPage = 0;

	private int currentPage = 0;

	private int pageSize = 10;

	private int totalRecords = 0;

	private String navigation = null;

	public static void handle(HashMap<String, Integer> hs, int currentPage,
			int pageSize) {
		if (currentPage < 1) {
			currentPage = 1;
		}
		int start = (currentPage - 1) * pageSize;
		int offset = pageSize;

		hs.put("start", new Integer(start));
		hs.put("offset", new Integer(offset));
	}

	public Page() {
		list = new ArrayList<T>();
	}

	public Page(T t) {
		list = new ArrayList<T>();
		list.add(t);
		this.currentPage = 1;
		this.totalRecords = list.size();
		this.pageSize = 10;
		if (currentPage < 1)
			currentPage = 1;
		if ((totalRecords / pageSize) * pageSize < totalRecords) {
			setTotalPage(totalRecords / pageSize + 1);
		} else {
			setTotalPage(totalRecords / pageSize);
		}
		if (currentPage > this.getTotalPage()) {
			this.setCurrentPage(1);
		}

		if (currentPage == 1) {
			setHasPrevious(false);
		} else {
			setHasPrevious(true);
			setPreviousPage(currentPage - 1);
		}

		if (currentPage * pageSize < totalRecords) {
			setHasNext(true);
			setNextPage(currentPage + 1);
		} else {
			setHasNext(false);
		}
	}

	public Page(List<T> list) {
		this(list, list.size(), 1, 10);
	}

	public Page(List<T> list, int totalRecords, int currentPage) {
		this(list, totalRecords, currentPage, 10);
	}

	public Page(List<T> list, int totalRecords, int currentPage, int pageSize) {
		this.list = list;
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.pageSize = pageSize;
		if (currentPage < 1)
			currentPage = 1;
		if ((totalRecords / pageSize) * pageSize < totalRecords) {
			setTotalPage(totalRecords / pageSize + 1);
		} else {
			setTotalPage(totalRecords / pageSize);
		}
		if (currentPage > this.getTotalPage()) {
			this.setCurrentPage(1);
		}

		if (currentPage == 1) {
			setHasPrevious(false);
		} else {
			setHasPrevious(true);
			setPreviousPage(currentPage - 1);
		}

		if (currentPage * pageSize < totalRecords) {
			setHasNext(true);
			setNextPage(currentPage + 1);
		} else {
			setHasNext(false);
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		if ((totalRecords / pageSize) * pageSize < totalRecords) {
			setTotalPage(totalRecords / pageSize + 1);
		} else {
			setTotalPage(totalRecords / pageSize);
		}
		this.totalRecords = totalRecords;
	}

	public String getNavigation() {
		StringBuffer strBuff = new StringBuffer();
		if (getTotalPage() <= 0) {
			return strBuff.toString();
		}
		
		strBuff
				.append(
						"<table id='normalT' width='98%'>")
				.append("<tr class=\"trLight\"><td>&nbsp;").append(
					"共").append(getTotalRecords()).append("条记录").append("&nbsp;")
				.append("&nbsp;").append(
					"当前页").append(":")
				.append(getCurrentPage()).append("/").append(getTotalPage())
				.append("</td>");
		if (getTotalPage() == 1) {
			strBuff.append("</tr></table>");
			return strBuff.toString();
		}
		strBuff.append("<td align=\"right\">");
		if (getPreviousPage() > 0) {
			strBuff.append("[<a href=\"javascript:toPage(1);\">").append(
					"首页").append("</a>] ");
		} else {
			strBuff.append("[").append("首页")
					.append("] ");
		}
		if (getPreviousPage() > 0)
			strBuff.append(" [<a href=\"javascript:toPage(").append(
					getPreviousPage()).append(");\">").append(
					"前页").append("</a>] ");
		else
			strBuff.append(" [")
					.append("前页").append(
							"] ");
		if (getNextPage() > 0)
			strBuff.append(" [<a href=\"javascript:toPage(").append(
					getNextPage()).append(");\">").append(
					"下页").append("</a>] ");
		else
			strBuff.append(" [").append("下页")
					.append("] ");
		if (getNextPage() > 0) {
			strBuff.append(" [<a href=\"javascript:toPage(").append(
					getTotalPage()).append(");\">").append(
					"末页").append("</a>]").append("&nbsp;");
		} else {
			strBuff.append(" [").append("末页")
					.append("]").append("&nbsp;");
		}

		strBuff
				.append("前往").append("&nbsp;")
				.append(
						"<input style='width:25px;' id='gotoPage' /><input type='button' class='bt' value='")
				.append("GO")
				.append(
						"' onclick='var p = document.getElementById(\"gotoPage\");toPage(p.value)'/>");
		strBuff.append("</td></tr></table>");
		return strBuff.toString();
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
}
