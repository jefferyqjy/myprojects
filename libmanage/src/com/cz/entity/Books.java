package com.cz.entity;

/**
 * Books entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Books implements java.io.Serializable {

	// Fields

	private Integer id;
	private String isbn;
	private String bookname;
	private String price;
	private String tslb;
	private String cbs;
	private String jianj;
	private String author;
	private String cbrq;
	private String kucun;
	private String filename;

	// Constructors

	/** default constructor */
	public Books() {
	}

	/** full constructor */
	public Books(String isbn, String bookname, String price, String tslb,
			String cbs, String jianj, String author, String cbrq, String kucun,
			String filename) {
		this.isbn = isbn;
		this.bookname = bookname;
		this.price = price;
		this.tslb = tslb;
		this.cbs = cbs;
		this.jianj = jianj;
		this.author = author;
		this.cbrq = cbrq;
		this.kucun = kucun;
		this.filename = filename;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTslb() {
		return this.tslb;
	}

	public void setTslb(String tslb) {
		this.tslb = tslb;
	}

	public String getCbs() {
		return this.cbs;
	}

	public void setCbs(String cbs) {
		this.cbs = cbs;
	}

	public String getJianj() {
		return this.jianj;
	}

	public void setJianj(String jianj) {
		this.jianj = jianj;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCbrq() {
		return this.cbrq;
	}

	public void setCbrq(String cbrq) {
		this.cbrq = cbrq;
	}

	public String getKucun() {
		return this.kucun;
	}

	public void setKucun(String kucun) {
		this.kucun = kucun;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}