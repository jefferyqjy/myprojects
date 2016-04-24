package com.cz.entity;

/**
 * Sysuser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sysuser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8750869069246712536L;
	
	private Integer id;
	private String uname;
	private String upass;
	private String tname;
	private String tel;
	private String email;

	// Constructors

	/** default constructor */
	public Sysuser() {
	}

	/** full constructor */
	public Sysuser(String uname, String upass, String tname, String tel,
			String email) {
		this.uname = uname;
		this.upass = upass;
		this.tname = tname;
		this.tel = tel;
		this.email = email;
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

}