package bean;

import java.math.BigDecimal;

public class ConsumeRecord {
	
	private int cId;
	
	private int mId;
	
	private BigDecimal beforeAmount;
	
	private BigDecimal afterAmount;
	
	private BigDecimal consumeAmount;
	
	private String consumeTime;
	
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

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public BigDecimal getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(BigDecimal consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public String getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}
	
}
