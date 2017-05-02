package com.z.plugin.announce;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class Announce extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8336300971371959739L;
	@EntityAnnotation(needShow=false,beanName="公告")
	private int id;
	@EntityAnnotation(desc="公告标题",rule="CHAR_M_120",isDetailLink=true,needUpdate=false,isQueryField=true)
	private String title;
	@EntityAnnotation(desc="公告内容",rule="CHAR_M_1024")
	private String content;
	@EntityAnnotation(desc="发布日期",rule="DATE_M")
	private String date;
	@EntityAnnotation(desc="公告级别",rule="RADI_M_高级;中级;低级")
	private String level;
	@EntityAnnotation(desc="测试复选框",rule="CHEC_M_是;否")
	private String sync;
	public String getSync() {
		return sync;
	}
	public void setSync(String sync) {
		this.sync = sync;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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

}
