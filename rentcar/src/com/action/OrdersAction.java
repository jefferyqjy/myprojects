package com.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.business.OrdersBusiness;
import com.entity.OrdersEntity;
import com.opensymphony.xwork2.ActionSupport;

public class OrdersAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private OrdersEntity orders;
	private List<OrdersEntity> list;
	private OrdersBusiness ordersBusiness;
	private int pageNumber;
	private int maxPage;
	private String html;
	private String number;
	private String id;
	private String name;
	private String cond;
	private Map<String, Object> map = new HashMap<String, Object>();

	public String createOrders() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		return SUCCESS;
	}

	public String addOrders() {
		this.orders.setOrdersid(UUID.randomUUID().toString());
		this.ordersBusiness.save(this.orders);
		return SUCCESS;
	}

	public String deleteOrders() {
		try {
			this.ordersBusiness.delete(orders);
		} catch (Exception e) {
			this.map.put("msg", "存在关联项 不能删除");
		}
		return SUCCESS;
	}

	public String updateOrders() {
		this.ordersBusiness.update(orders);
		return SUCCESS;
	}

	public String getAllOrders() {
		this.list = new ArrayList<OrdersEntity>();
		List<OrdersEntity> tempList = new ArrayList<OrdersEntity>();
		tempList = this.ordersBusiness.show();
		this.pageNumber = tempList.size();
		this.maxPage = this.pageNumber;
		if (this.maxPage % 10 == 0) {
			this.maxPage = this.maxPage / 10;
		} else {
			this.maxPage = this.maxPage / 10 + 1;
		}
		if (this.number == null) {
			this.number = "0";
		}
		int start = Integer.parseInt(this.number) * 10;
		int over = (Integer.parseInt(this.number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			OrdersEntity u = (OrdersEntity) tempList.get(i);
			this.list.add(u);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(this.number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer
					.append("<a href=\"orders/getAllOrders.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"orders/getAllOrders.action?number="
					+ (Integer.parseInt(this.number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"orders/getAllOrders.action?number="
					+ (Integer.parseInt(this.number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"orders/getAllOrders.action?number="
					+ (this.maxPage - 1) + "\">尾页</a>");
		}
		this.html = buffer.toString();
		return SUCCESS;
	}

	public String getOrdersById() {
		this.orders = this.ordersBusiness.checkId(this.id);
		return SUCCESS;
	}

	public String queryOrdersByCond() {
		this.list = new ArrayList<OrdersEntity>();
		if ("ordercode".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeOrdercode(this.name);
		}
		if ("usersid".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeUsersid(this.name);
		}
		if ("carsid".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeCarsid(this.name);
		}
		if ("thestart".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeThestart(this.name);
		}
		if ("theend".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeTheend(this.name);
		}
		if ("place".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikePlace(this.name);
		}
		if ("address".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeAddress(this.name);
		}
		if ("addtime".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeAddtime(this.name);
		}
		if ("status".equals(this.cond)) {
			list = this.ordersBusiness.checkByLikeStatus(this.name);
		}
		return SUCCESS;
	}
	
	/**
	 * 管理员确认还车
	 * @return
	 */
	public String confirmReturn() {
		this.orders = this.ordersBusiness.checkId(this.id);
		orders.setStatus("已还车");
		this.ordersBusiness.update(orders);
		
		getAllOrders();
		return SUCCESS;
	}

	public OrdersEntity getOrders() {
		return orders;
	}

	public void setOrders(OrdersEntity orders) {
		this.orders = orders;
	}

	public List<OrdersEntity> getList() {
		return list;
	}

	public void setList(List<OrdersEntity> list) {
		this.list = list;
	}

	public OrdersBusiness getOrdersBusiness() {
		return ordersBusiness;
	}

	public void setOrdersBusiness(OrdersBusiness ordersBusiness) {
		this.ordersBusiness = ordersBusiness;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
