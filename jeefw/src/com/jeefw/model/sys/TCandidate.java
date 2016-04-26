package com.jeefw.model.sys;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeefw.model.sys.param.SysUserParameter;


/**
 * 用户的实体类
 * administrator
 */
@Entity
@Table(name = "t_candidate")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class TCandidate extends SysUserParameter {

	// 各个字段的含义请查阅文档的数据库结构部分
	private static final long serialVersionUID = 822330369002149147L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	  
	@Column(name = "aid")
	private int aid;
	
	@Column(name = "atitle")
	private String atitle;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "reviewed")
	private int reviewed;
	
	@Transient
	private boolean isHot;
	
	public int getReviewed() {
		return reviewed;
	}

	public void setReviewed(int reviewed) {
		this.reviewed = reviewed;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAtitle() {
		return atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
 
	@Column(name = "candescribe")
	private String candescribe;

	public String getCandescribe() {
		return candescribe;
	}

	public void setCandescribe(String candescribe) {
		this.candescribe = candescribe;
	}

	public TCandidate() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}
	 
 

}