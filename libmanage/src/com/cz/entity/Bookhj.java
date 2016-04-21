package com.cz.entity;

/**
 * Bookhj entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Bookhj implements java.io.Serializable {

	// Fields

	private Integer id;
	private String jtime;
	private String htime;
	private String bookname;
	private String readername;
	private String yjin;
	private String bei;
	private String hbtime;
	private String hbkou;
	private String hbbei;
	private String sjtime;
	private String sjstatus;

	// Constructors

	/** default constructor */
	public Bookhj() {
	}

	/** full constructor */
	public Bookhj(String jtime, String htime, String bookname,
			String readername, String yjin, String bei, String hbtime,
			String hbkou, String hbbei, String sjtime, String sjstatus) {
		this.jtime = jtime;
		this.htime = htime;
		this.bookname = bookname;
		this.readername = readername;
		this.yjin = yjin;
		this.bei = bei;
		this.hbtime = hbtime;
		this.hbkou = hbkou;
		this.hbbei = hbbei;
		this.sjtime = sjtime;
		this.sjstatus = sjstatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJtime() {
		return this.jtime;
	}

	public void setJtime(String jtime) {
		this.jtime = jtime;
	}

	public String getHtime() {
		return this.htime;
	}

	public void setHtime(String htime) {
		this.htime = htime;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getReadername() {
		return this.readername;
	}

	public void setReadername(String readername) {
		this.readername = readername;
	}

	public String getYjin() {
		return this.yjin;
	}

	public void setYjin(String yjin) {
		this.yjin = yjin;
	}

	public String getBei() {
		return this.bei;
	}

	public void setBei(String bei) {
		this.bei = bei;
	}

	public String getHbtime() {
		return this.hbtime;
	}

	public void setHbtime(String hbtime) {
		this.hbtime = hbtime;
	}

	public String getHbkou() {
		return this.hbkou;
	}

	public void setHbkou(String hbkou) {
		this.hbkou = hbkou;
	}

	public String getHbbei() {
		return this.hbbei;
	}

	public void setHbbei(String hbbei) {
		this.hbbei = hbbei;
	}

	public String getSjtime() {
		return this.sjtime;
	}

	public void setSjtime(String sjtime) {
		this.sjtime = sjtime;
	}

	public String getSjstatus() {
		return this.sjstatus;
	}

	public void setSjstatus(String sjstatus) {
		this.sjstatus = sjstatus;
	}

}