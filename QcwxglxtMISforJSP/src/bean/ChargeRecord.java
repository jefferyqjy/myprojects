package bean;

import java.math.BigDecimal;

public class ChargeRecord {
	
	private int cId;
	
	private int mId;
	
	private BigDecimal beforeAmount;
	
	private BigDecimal afterAmount;
	
	private BigDecimal chargeAmount;
	
	private String chargeTime;
	
	private Integer empId;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public BigDecimal getBeforeAmount() {
		return beforeAmount;
	}

	public void setBeforeAmount(BigDecimal beforeAmount) {
		this.beforeAmount = beforeAmount;
	}

	public BigDecimal getAfterAmount() {
		return afterAmount;
	}

	public void setAfterAmount(BigDecimal afterAmount) {
		this.afterAmount = afterAmount;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	
}
