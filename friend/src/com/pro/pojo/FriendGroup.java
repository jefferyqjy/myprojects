package com.pro.pojo;

import java.util.List;

public class FriendGroup {

	private int id;
	private String name;
	private List<MemberBean> friends;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MemberBean> getFriends() {
		return friends;
	}
	public void setFriends(List<MemberBean> friends) {
		this.friends = friends;
	}
	
	
	
}
