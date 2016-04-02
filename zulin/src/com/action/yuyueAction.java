package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.TCheDAO;
import com.dao.TUserDAO;
import com.dao.TYuyueDAO;
import com.model.TAdmin;
import com.model.TChe;
import com.model.TUser;
import com.model.TYuyue;
import com.opensymphony.xwork2.ActionSupport;

public class yuyueAction extends ActionSupport {
	private Integer id;
	private Integer cheId;
	private String xingming;
	private String lianxi;
	private String zhuzhi;
	private String pinjiaremark;
	private String liuyanremark;
	private String huifuremark;

	private TYuyueDAO yuyueDAO;
	private TCheDAO cheDAO;
	private String isType;
	private TUserDAO userDAO;

	public String yuyueAdd() {
		HttpServletRequest request = ServletActionContext.getRequest();

		TYuyue yuyue = new TYuyue();

		yuyue.setCheId(cheId);
		yuyue.setXingming(xingming);
		yuyue.setLianxi(lianxi);
		yuyue.setZhuzhi(zhuzhi);

		yuyueDAO.save(yuyue);

		request.setAttribute("msg", "预约成功,我们会及时和你取得联系");
		return "msg";
	}

	public String pinjiaAdd() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");

		TChe tChe = cheDAO.findById(cheId);
		TYuyue yuyue = new TYuyue();

		yuyue.setCheId(cheId);
		yuyue.setUserId(user.getUserId());
		yuyue.setXingming(user.getUserRealname());
		yuyue.setPinjiaremark(pinjiaremark);
		yuyue.setPinjiashij(new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date()));
		yuyue.setIsType("pj");
		yuyue.setBelonguserId(tChe.getUserid());

		yuyueDAO.save(yuyue);

		request.setAttribute("msg", "评价成功，感谢您的评价！");
		return "msg";
	}

	public String liuyanAdd() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");

		TChe tChe = cheDAO.findById(cheId);
		TYuyue yuyue = new TYuyue();

		yuyue.setCheId(cheId);
		yuyue.setUserId(user.getUserId());
		yuyue.setXingming(user.getUserRealname());
		yuyue.setPinjiaremark(pinjiaremark);
		yuyue.setLiuyanremark(liuyanremark);
		yuyue.setLiushij(new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date()));
		yuyue.setIsType("ly");
		yuyue.setBelonguserId(tChe.getUserid());

		yuyueDAO.save(yuyue);

		request.setAttribute("msg", "留言成功！");
		return "msg";
	}

	public String liuyanHuifu1() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");

		TYuyue yuyue = yuyueDAO.findById(id);
		yuyue.setHuifuremark(huifuremark);
		yuyue.setHuifushij(new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(new Date()));
		yuyue.setBelonguserId(admin.getUserId());

		yuyueDAO.attachDirty(yuyue);

		request.setAttribute("msg", "留言回复成功！");
		return "msg";
	}

	public String yuyueMana() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		if ("1".equals(admin.getUserType())) {
			sqlString = " and belonguserId=" + admin.getUserId();
		}
		String sql = "from TYuyue where 1=1 and isType='" + isType + "' "
				+ sqlString;
		List yuyueList = yuyueDAO.getHibernateTemplate().find(sql);
		for (int i = 0; i < yuyueList.size(); i++) {
			TYuyue yuyue = (TYuyue) yuyueList.get(i);
			yuyue.setChe(cheDAO.findById(yuyue.getCheId()));
			yuyue.setUser(userDAO.findById(yuyue.getUserId()));
		}

		Map request1 = (Map) ServletActionContext.getContext().get("request");
		request1.put("yuyueList", yuyueList);
		request1.put("isType", isType);
		return ActionSupport.SUCCESS;
	}

	public String viewliuy() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");

		String sql = "from TYuyue where 1=1 and isType='ly' and cheId="+cheId+" and userId="+user.getUserId();
		List yuyueList = yuyueDAO.getHibernateTemplate().find(sql);
		for (int i = 0; i < yuyueList.size(); i++) {
			TYuyue yuyue = (TYuyue) yuyueList.get(i);
			yuyue.setChe(cheDAO.findById(yuyue.getCheId()));
			yuyue.setUser(userDAO.findById(yuyue.getUserId()));
		}

		Map request1 = (Map) ServletActionContext.getContext().get("request");
		request1.put("yuyueList", yuyueList);
		return ActionSupport.SUCCESS;
	}

	public String yuyueDel() {
		TYuyue yuyue = yuyueDAO.findById(id);
		yuyueDAO.delete(yuyue);

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("msg", "删除成功");
		return "msg";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TCheDAO getCheDAO() {
		return cheDAO;
	}

	public void setCheDAO(TCheDAO cheDAO) {
		this.cheDAO = cheDAO;
	}

	public Integer getCheId() {
		return cheId;
	}

	public void setCheId(Integer cheId) {
		this.cheId = cheId;
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

	public TYuyueDAO getYuyueDAO() {
		return yuyueDAO;
	}

	public void setYuyueDAO(TYuyueDAO yuyueDAO) {
		this.yuyueDAO = yuyueDAO;
	}

	public String getPinjiaremark() {
		return pinjiaremark;
	}

	public void setPinjiaremark(String pinjiaremark) {
		this.pinjiaremark = pinjiaremark;
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

	public TUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(TUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
