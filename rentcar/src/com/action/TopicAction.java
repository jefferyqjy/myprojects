package com.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.business.TopicBusiness;
import com.entity.TopicEntity;
import com.opensymphony.xwork2.ActionSupport;

public class TopicAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private TopicEntity topic;
	private List<TopicEntity> list;
	private TopicBusiness topicBusiness;
	private int pageNumber;
	private int maxPage;
	private String html;
	private String number;
	private String id;
	private String name;
	private String cond;
	private Map<String, Object> map = new HashMap<String, Object>();

	public String createTopic() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// Map<String, Object> session =
		// ActionContext.getContext().getSession();
		return SUCCESS;
	}

	public String addTopic() {
		this.topic.setTopicid(UUID.randomUUID().toString());
		this.topicBusiness.save(this.topic);
		return SUCCESS;
	}

	public String deleteTopic() {
		try {
			this.topicBusiness.delete(topic);
		} catch (Exception e) {
			this.map.put("msg", "存在关联项 不能删除");
		}
		return SUCCESS;
	}

	public String updateTopic() {
		this.topicBusiness.update(topic);
		return SUCCESS;
	}

	public String getAllTopic() {
		this.list = new ArrayList<TopicEntity>();
		List<TopicEntity> tempList = new ArrayList<TopicEntity>();
		tempList = this.topicBusiness.show();
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
			TopicEntity u = (TopicEntity) tempList.get(i);
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
					.append("<a href=\"topic/getAllTopic.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"topic/getAllTopic.action?number="
					+ (Integer.parseInt(this.number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"topic/getAllTopic.action?number="
					+ (Integer.parseInt(this.number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"topic/getAllTopic.action?number="
					+ (this.maxPage - 1) + "\">尾页</a>");
		}
		this.html = buffer.toString();
		return SUCCESS;
	}

	public String getTopicById() {
		this.topic = this.topicBusiness.checkId(this.id);
		return SUCCESS;
	}

	public String queryTopicByCond() {
		this.list = new ArrayList<TopicEntity>();
		if ("usersid".equals(this.cond)) {
			list = this.topicBusiness.checkByLikeUsersid(this.name);
		}
		if ("carsid".equals(this.cond)) {
			list = this.topicBusiness.checkByLikeCarsid(this.name);
		}
		if ("num".equals(this.cond)) {
			list = this.topicBusiness.checkByLikeNum(this.name);
		}
		if ("contents".equals(this.cond)) {
			list = this.topicBusiness.checkByLikeContents(this.name);
		}
		if ("addtime".equals(this.cond)) {
			list = this.topicBusiness.checkByLikeAddtime(this.name);
		}
		return SUCCESS;
	}

	public TopicEntity getTopic() {
		return topic;
	}

	public void setTopic(TopicEntity topic) {
		this.topic = topic;
	}

	public List<TopicEntity> getList() {
		return list;
	}

	public void setList(List<TopicEntity> list) {
		this.list = list;
	}

	public TopicBusiness getTopicBusiness() {
		return topicBusiness;
	}

	public void setTopicBusiness(TopicBusiness topicBusiness) {
		this.topicBusiness = topicBusiness;
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
