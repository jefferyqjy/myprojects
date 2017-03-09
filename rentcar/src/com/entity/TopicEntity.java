package com.entity;

public class TopicEntity {
	private CarsEntity cars;
	private UsersEntity users;

	public CarsEntity getCars() {
		return cars;
	}

	public void setCars(CarsEntity cars) {
		this.cars = cars;
	}

	public UsersEntity getUsers() {
		return users;
	}

	public void setUsers(UsersEntity users) {
		this.users = users;
	}

	private String topicid;

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	private String usersid;

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	private String carsid;

	public String getCarsid() {
		return this.carsid;
	}

	public void setCarsid(String carsid) {
		this.carsid = carsid;
	}

	private String num;

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	private String contents;

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	private String addtime;

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}