package bean;

public class Evaluation {
	private int eID;
	private int cID;
	private String evaluatime;
	private String evaluation;
	private int cusID;
	
	public int getCusID() {
		return cusID;
	}
	public void setCusID(int cusID) {
		this.cusID = cusID;
	}
	public int geteID() {
		return eID;
	}
	public void seteID(int eID) {
		this.eID = eID;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public String getEvaluatime() {
		return evaluatime;
	}
	public void setEvaluatime(String evaluatime) {
		this.evaluatime = evaluatime;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	

}
