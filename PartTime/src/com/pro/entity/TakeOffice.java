package com.pro.entity;

import com.base.common.util.EntityAnnotation;
import com.base.sys.entity.BaseEntity;

public class TakeOffice extends BaseEntity {

	@EntityAnnotation(needShow=false,beanName="就职情况")
	private int id;
	@EntityAnnotation(desc="岗位ID",needShow=false, isQueryField=false)
	private String parttimeid;
	
	@EntityAnnotation(desc="岗位标题",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String parttimetitle;
	
	@EntityAnnotation(desc="学生ID",needShow=false,isQueryField=false)
	private String stuid;
	
	@EntityAnnotation(desc="学生用户名",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String stuname;
	
	@EntityAnnotation(desc="状态",  needUpdate=true, isQueryField = true, rule ="SELE_M;兼职中;兼职结束")
	private String status;
	
	@EntityAnnotation(desc="姓名",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String relname;
	
	@EntityAnnotation(desc="联系方式",rule="CHAR_M_120",needUpdate=false,isQueryField=false)
	private String phone;
	
	@EntityAnnotation(desc="开始日期",rule="DATE_N",needUpdate=true,isQueryField=false)
	private String datefrom;
	
	@EntityAnnotation(desc="结束日期",rule="DATE_N",needUpdate=true,isQueryField=false)
	private String dateto;
	
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

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateto() {
		return dateto;
	}

	public void setDateto(String dateto) {
		this.dateto = dateto;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
