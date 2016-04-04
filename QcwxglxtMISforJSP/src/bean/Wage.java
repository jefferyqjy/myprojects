package bean;

public class Wage {
	private int wID;
	private int empID;
	private String empName;
	private String month;
	private float basepay;
	private float commission;
	private float salary;
	public int getwID() {
		return wID;
	}
	public void setwID(int wID) {
		this.wID = wID;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public float getBasepay() {
		return basepay;
	}
	public void setBasepay(float basepay) {
		this.basepay = basepay;
	}
	public float getCommission() {
		return commission;
	}
	public void setCommission(float commission) {
		this.commission = commission;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}


}
