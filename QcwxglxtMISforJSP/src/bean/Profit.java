package bean;

import java.math.BigDecimal;

public class Profit {
	
	private Integer pId;

	private Integer cId;
	
	private BigDecimal profit;
	
	private BigDecimal cost;
	
	private String createTime;
	
	private Checkouts checkouts;
	
	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Checkouts getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(Checkouts checkouts) {
		this.checkouts = checkouts;
	}

}
