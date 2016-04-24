package com.cz.entity;

/**
 * Syspros entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Syspros implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1441798860255494571L;
	
	private Integer id;
	private String proname;
	private String infoa;
	private String infob;
	private String infoc;
	private String infod;
	private String infoe;
	private String infof;
	private String infog;

	// Constructors

	/** default constructor */
	public Syspros() {
	}

	/** full constructor */
	public Syspros(String proname, String infoa, String infob, String infoc,
			String infod, String infoe, String infof, String infog) {
		this.proname = proname;
		this.infoa = infoa;
		this.infob = infob;
		this.infoc = infoc;
		this.infod = infod;
		this.infoe = infoe;
		this.infof = infof;
		this.infog = infog;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getInfoa() {
		return this.infoa;
	}

	public void setInfoa(String infoa) {
		this.infoa = infoa;
	}

	public String getInfob() {
		return this.infob;
	}

	public void setInfob(String infob) {
		this.infob = infob;
	}

	public String getInfoc() {
		return this.infoc;
	}

	public void setInfoc(String infoc) {
		this.infoc = infoc;
	}

	public String getInfod() {
		return this.infod;
	}

	public void setInfod(String infod) {
		this.infod = infod;
	}

	public String getInfoe() {
		return this.infoe;
	}

	public void setInfoe(String infoe) {
		this.infoe = infoe;
	}

	public String getInfof() {
		return this.infof;
	}

	public void setInfof(String infof) {
		this.infof = infof;
	}

	public String getInfog() {
		return this.infog;
	}

	public void setInfog(String infog) {
		this.infog = infog;
	}

}