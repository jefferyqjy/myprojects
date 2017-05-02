package com.pro.entity;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class ApplyInfo extends BaseEntity {

	@EntityAnnotation(needShow=false,beanName="职位申请")
	private int id;
	@EntityAnnotation(desc="岗位ID",needShow=false, isQueryField=false)
	private String parttimeid;
	
	@EntityAnnotation(desc="岗位标题",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String parttimetitle;
	
	@EntityAnnotation(desc="学生ID",needShow=false,isQueryField=false)
	private String stuid;
	
	@EntityAnnotation(desc="学生用户名",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String stuname;
	
	@EntityAnnotation(desc="状态",  needUpdate=true, isQueryField = true, rule ="SELE_M;提交申请;审核通过")
	private String status;
	
	
	@EntityAnnotation(desc="姓名",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String relname;
	
	@EntityAnnotation(desc="联系方式",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String phone;
	
	@EntityAnnotation(desc="申请说明",  needUpdate=true, isQueryField = false, rule ="CHAR_N")
	private String content;
	
	@EntityAnnotation(desc="申请日期",rule="DATE_M")
	private String date;
	
	@EntityAnnotation(desc="备注",  needUpdate=true, isQueryField = false, rule ="CHAR_N")
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParttimeid() {
		return parttimeid;
	}

	public void setParttimeid(String parttimeid) {
		this.parttimeid = parttimeid;
	}

	public String getParttimetitle() {
		return parttimetitle;
	}

	public void setParttimetitle(String parttimetitle) {
		this.parttimetitle = parttimetitle;
	}

	public String getStuid() {
		return stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRelname() {
		return relname;
	}

	public void setRelname(String relname) {
		this.relname = relname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
