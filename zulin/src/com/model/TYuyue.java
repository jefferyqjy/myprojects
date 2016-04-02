package com.model;

/**
 * TYuyue entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TYuyue implements java.io.Serializable {

	private Integer id;
	private Integer cheId;
	private String xingming;
	private String lianxi;
	
	private String zhuzhi;
	
	private String liuyanremark;
	private String liushij;
	
	private String huifuremark;	
	private String huifushij;
	
	private String isType;
	private String pinjiaremark;
	private String pinjiashij;
	
	private Integer userId;
	private Integer belonguserId;
	
	
	private TChe che;
	private TUser user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCheId() {
		return cheId;
	}
	public void setCheId(Integer cheId) {
		this.cheId = cheId;
	}
	
	public TChe getChe() {
		return che;
	}
	public void setChe(TChe che) {
		this.che = che;
	}
	public String getXingming() {
		return xingming;
	}
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	public String getLianxi() {
		return lianxi;
	}
	public void setLianxi(String lianxi) {
		this.lianxi = lianxi;
	}
	public String getZhuzhi() {
		return zhuzhi;
	}
	public void setZhuzhi(String zhuzhi) {
		this.zhuzhi = zhuzhi;
	}
	public String getLiuyanremark() {
		return liuyanremark;
	}
	public void setLiuyanremark(String liuyanremark) {
		this.liuyanremark = liuyanremark;
	}
	public String getHuifuremark() {
		return huifuremark;
	}
	public void setHuifuremark(String huifuremark) {
		this.huifuremark = huifuremark;
	}
	public String getIsType() {
		return isType;
	}
	public void setIsType(String isType) {
		this.isType = isType;
	}
	public String getPinjiaremark() {
		return pinjiaremark;
	}
	public void setPinjiaremark(String pinjiaremark) {
		this.pinjiaremark = pinjiaremark;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBelonguserId() {
		return belonguserId;
	}
	public void setBelonguserId(Integer belonguserId) {
		this.belonguserId = belonguserId;
	}
	public String getLiushij() {
		return liushij;
	}
	public void setLiushij(String liushij) {
		this.liushij = liushij;
	}
	public String getHuifushij() {
		return huifushij;
	}
	public void setHuifushij(String huifushij) {
		this.huifushij = huifushij;
	}
	public String getPinjiashij() {
		return pinjiashij;
	}
	public void setPinjiashij(String pinjiashij) {
		this.pinjiashij = pinjiashij;
	}
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}

}