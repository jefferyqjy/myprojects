package bean;

public class Yuyue {
	private int yID;
	private int cusID;
	private String cusName;
	private String cusphone;
	private String carnum;
	private String carmoder;
	private String repairitem;
	private String yuyuetime;
	//状态控制，不参与实际业务
	private int modifyflag;
	
	public int getModifyflag() {
		return modifyflag;
	}
	public void setModifyflag(int modifyflag) {
		this.modifyflag = modifyflag;
	}
	public int getyID() {
		return yID;
	}
	public void setyID(int yID) {
		this.yID = yID;
	}
	public int getCusID() {
		return cusID;
	}
	public void setCusID(int cusID) {
		this.cusID = cusID;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusphone() {
		return cusphone;
	}
	public void setCusphone(String cusphone) {
		this.cusphone = cusphone;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getCarmoder() {
		return carmoder;
	}
	public void setCarmoder(String carmoder) {
		this.carmoder = carmoder;
	}
	public String getRepairitem() {
		return repairitem;
	}
	public void setRepairitem(String repairitem) {
		this.repairitem = repairitem;
	}
	public String getYuyuetime() {
		return yuyuetime;
	}
	public void setYuyuetime(String yuyuetime) {
		this.yuyuetime = yuyuetime;
	}
	

	
}
