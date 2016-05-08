package bean;

import java.math.BigDecimal;

public class ProfitMonthly {
	
	
	private BigDecimal checkoutProfit; // 结账利润
	
	private BigDecimal innerProfit; // 内配利润
	
	private BigDecimal returnCost; // 退货损失
	
	private BigDecimal salaryCost; // 工资发放
	
	private BigDecimal totalProfit; // 总利润

	public BigDecimal getCheckoutProfit() {
		return checkoutProfit;
	}

	public void setCheckoutProfit(BigDecimal checkoutProfit) {
		this.checkoutProfit = checkoutProfit;
	}

	public BigDecimal getInnerProfit() {
		return innerProfit;
	}

	public void setInnerProfit(BigDecimal innerProfit) {
		this.innerProfit = innerProfit;
	}

	public BigDecimal getReturnCost() {
		return returnCost;
	}

	public void setReturnCost(BigDecimal returnCost) {
		this.returnCost = returnCost;
	}

	public BigDecimal getSalaryCost() {
		return salaryCost;
	}

	public void setSalaryCost(BigDecimal salaryCost) {
		this.salaryCost = salaryCost;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	

}
