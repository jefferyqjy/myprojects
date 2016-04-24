package com.cz.entity;

/**
 * Sreader entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sreader implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8712750796597052116L;
	
	private Integer id;
	private String uname;
	private String upass;
	private String tname;
	private String xueli;
	private String ziye;
	private String kjnum;
	private String tel;
	private String email;
	private String sc;

	// Constructors

	/** default constructor */
	public Sreader() {
	}

	/** full constructor */
	public Sreader(String uname, String upass, String tname, String xueli,
			String ziye, String kjnum, String tel, String email, String sc) {
		this.uname = uname;
		this.upass = upass;
		this.tname = tname;
		this.xueli = xueli;
		this.ziye = ziye;
		this.kjnum = kjnum;
		this.tel = tel;
		this.email = email;
		this.sc = sc;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return this.upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getXueli() {
		return this.xueli;
	}

	public void setXueli(String xueli) {
		this.xueli = xueli;
	}

	public String getZiye() {
		return this.ziye;
	}

	public void setZiye(String ziye) {
		this.ziye = ziye;
	}

	public String getKjnum() {
		return this.kjnum;
	}

	public void setKjnum(String kjnum) {
		this.kjnum = kjnum;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSc() {
		return this.sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

}