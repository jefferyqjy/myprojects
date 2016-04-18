package com.base.pagination.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.base.web.tag.BTAGI18N;

public class Page<T> implements Serializable {
	public static final String CURRENT_PAGE = "curPage";
	public static final String PAGE_SIZE = "pageSize";
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
		String strTotal = BTAGI18N.getI18NValue("page.total", "common");
		String strRecord = BTAGI18N.getI18NValue("page.record", "common");
		String strCurrent = BTAGI18N.getI18NValue("page.current", "common");
		String strFirst = BTAGI18N.getI18NValue("page.first", "common");
		String strNext = BTAGI18N.getI18NValue("page.next", "common");
		String strLast = BTAGI18N.getI18NValue("page.last", "common");
		String strPrevious = BTAGI18N.getI18NValue("page.previous", "common");
		String strGoto = BTAGI18N.getI18NValue("page.goto", "common");
		String strGo = BTAGI18N.getI18NValue("button.go", "common");
		StringBuilder strBuff = new StringBuilder();
		if (getTotalPage() <= 0) {
			return strBuff.toString();
		}
		
		strBuff
				.append("<span>").append(strTotal).append(getTotalRecords()).append(strRecord)
				.append("&nbsp;").append(strCurrent).append(":").append(getCurrentPage())
				.append("/").append(getTotalPage()).append("</span>&nbsp;");
		if (getPreviousPage() > 0) {
			strBuff.append("<a href=\"javascript:toPage(1);\" title=\"First Page\">").append(strFirst).append("</a>");
		} else {
			strBuff.append(strFirst);
		}
		if (getPreviousPage() > 0) {
			strBuff.append("<a href=\"javascript:toPage(").append(getPreviousPage()).append(");\" title=\"Previous Page\">").append(strPrevious).append("</a>");
		} else {
			strBuff.append(strPrevious);
		}
		if (getNextPage() > 0) {
			strBuff.append("<a href=\"javascript:toPage(").append(getNextPage()).append(");\" title=\"Next Page\">").append(strNext).append("</a>");
		} else {
			strBuff.append(strNext);
		}
		if (getNextPage() > 0) {
			strBuff.append("<a href=\"javascript:toPage(").append(getTotalPage()).append(");\" title=\"Last Page\">").append(strLast).append("</a>");
		} else {
			strBuff.append(strLast);
		}

		strBuff	.append("<span>&nbsp;")
				.append(strGoto).append("&nbsp;")
				.append(
						"<input style='width:25px;' id='gotoPage' /><input type='button' class='button' value='")
				.append(strGo)
				.append(
						"' onclick='var p = document.getElementById(\"gotoPage\");toPage(p.value)'/>");
		strBuff.append("</span>");
		return strBuff.toString();
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
}
