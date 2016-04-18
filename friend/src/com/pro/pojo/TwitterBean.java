package com.pro.pojo;

import java.util.List;

public class TwitterBean {

	private int id;

	private String content;
	
	private String createTime;
	
	private Integer userId;
	
	private String userName;
	
	private Integer like;
	
	private List<TwitterCommentBean> commentList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<TwitterCommentBean> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<TwitterCommentBean> commentList) {
		this.commentList = commentList;
	}
	
	
}
