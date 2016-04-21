package com.cz.entity;

/**
 * Bookyy entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Bookyy implements java.io.Serializable {

	// Fields

	private Integer id;
	private String yytime;
	private String htime;
	private String readername;
	private String bookname;
	private String bei;
	private String status;

	// Constructors

	/** default constructor */
	public Bookyy() {
	}

	/** full constructor */
	public Bookyy(String yytime, String htime, String readername,
			String bookname, String bei, String status) {
		this.yytime = yytime;
		this.htime = htime;
		this.readername = readername;
		this.bookname = bookname;
		this.bei = bei;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYytime() {
		return this.yytime;
	}

	public void setYytime(String yytime) {
		this.yytime = yytime;
	}

	public String getHtime() {
		return this.htime;
	}

	public void setHtime(String htime) {
		this.htime = htime;
	}

	public String getReadername() {
		return this.readername;
	}

	public void setReadername(String readername) {
		this.readername = readername;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBei() {
		return this.bei;
	}

	public void setBei(String bei) {
		this.bei = bei;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}