package com.z.plugin.bbss;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Title extends BaseEntity {
	
	@EntityAnnotation(beanName="论坛主题", needShow = false)
	private int id;
	
	
	@EntityAnnotation(desc="标题",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String title;
	@EntityAnnotation(desc="内容",  needUpdate=true, isQueryField = true, rule ="CHAR_M")
	private String content;
	
	@EntityAnnotation(desc="发布者id",  needShow = false)
	private String userid;
	
	@EntityAnnotation(desc="发布者",  needUpdate=false, isQueryField = true, rule ="CHAR_M")
	private String username;
	
	@EntityAnnotation(desc="时间", needUpdate=false, rule ="DATE_N")
	private Date datetime;
	
	
	private String datetimestr;
	

	public String getDatetimestr() {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(datetime != null){
			return sf.format(datetime);
		}else{
			return "";
		}
		
	}

	public void setDatetimestr(String datetimestr) {
		this.datetimestr = datetimestr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	
}
