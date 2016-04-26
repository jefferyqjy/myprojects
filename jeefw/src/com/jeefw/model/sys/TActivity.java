package com.jeefw.model.sys;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeefw.model.sys.param.ActivotyParameter;
import com.jeefw.model.sys.param.SysUserParameter;


/**
 * 用户的实体类
 * administrator
 */
@Entity
@Table(name = "t_activity")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class TActivity extends ActivotyParameter {

	// 各个字段的含义请查阅文档的数据库结构部分
	private static final long serialVersionUID = 822330369002149147L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", length = 500, nullable = false)
	private String title;
	
	
	@Column(name = "promoter")
	private String promoter;

	@Column(name = "reviewed")
	private int reviewed;
	

	
	public TActivity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	public int getReviewed() {
		return reviewed;
	}

	public void setReviewed(int reviewed) {
		this.reviewed = reviewed;
	}

 

}