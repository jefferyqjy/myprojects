package bean;

import java.math.BigDecimal;

/**
 * 内配
 */
public class Partinner {
	
	private Integer iId;
	
	private Integer empId;
	
	private String empName;
	
	private String pId;
	
	private String pName;
	
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	private Integer pNum;
	
	private BigDecimal pCost;
	
	private BigDecimal pPrice;
	
	private String company;
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	private String date;
	
	public Integer getiId() {
		return iId;
	}

	public void setiId(Integer iId) {
		this.iId = iId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public Integer getpNum() {
		return pNum;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public void setpNum(Integer pNum) {
		this.pNum = pNum;
	}

	public BigDecimal getpCost() {
		return pCost;
	}

	public void setpCost(BigDecimal pCost) {
		this.pCost = pCost;
	}

	public BigDecimal getpPrice() {
		return pPrice;
	}

	public void setpPrice(BigDecimal pPrice) {
		this.pPrice = pPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
