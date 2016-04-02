package com.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.TAdminDAO;
import com.dao.TContractDAO;
import com.dao.TFundDAO;
import com.dao.TUserDAO;
import com.dao.TZulinDAO;
import com.model.TAdmin;
import com.model.TContract;
import com.model.TFund;
import com.model.TUser;
import com.model.TZulin;
import com.opensymphony.xwork2.ActionSupport;

public class fundAction extends ActionSupport
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6488863268747458433L;

	private Integer id;
	
	private Integer contractId;
	
	private Integer status;
	
	private Integer type;
	
	private Integer payType;
	
	private BigDecimal money;
	
	private String receiptNumber;
	
	private TContractDAO contractDAO;
	
	private TAdminDAO adminDAO;
	
	private TUserDAO userDAO;
	
	private TZulinDAO zulinDAO;
	
	private TFundDAO fundDAO;
	
	/**
	 * forward to page of the list of funds  
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String fundMana() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		// if user is the landlord, then find records according to the landlord
		if ("1".equals(admin.getUserType())) {
			sqlString = " and adminId=" + admin.getUserId();
		}
		String sql = " from TContract where 1=1 " + sqlString;
		List<TContract> conList = contractDAO.getHibernateTemplate().find(sql);
		List<TFund> list = new ArrayList<TFund>();
		if(conList != null && conList.size() > 0) {
			for(TContract con : conList) {
				Integer contractId = con.getContractId();
				List<TFund> fundList = fundDAO.findByContractId(contractId);
				list.addAll(fundList);
			}
		}
		Map<String, Object> request1 = (Map) ServletActionContext.getContext().get("request");
		request1.put("fundList", list);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * forward to create/update page
	 * @return
	 */
	public String fundAddShow() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer contractId = (Integer) request.getAttribute("contractId");
		
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		
		List<TContract> conList = contractDAO.findByAdminId(admin.getUserId());
		
		Map<String, Object> request1 = (Map) ServletActionContext.getContext().get("request");
		request1.put("conList", conList);
		request1.put("admin", admin);
		request1.put("contractId", contractId);
		
		Integer id = (Integer) request.getAttribute("id");
		// udpate
		if(id != null) {
			TFund fund = fundDAO.findById(id);
			if(fund != null) {
				request1.put("fund", fund);
				request1.put("contractId", fund.getContractId());
			}
		}
		
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * save/update fund
	 * @return
	 */
	public String fundAddSave() {
		
		TFund fund = new TFund();
		
		if(id != null) {
			fund = fundDAO.findById(id);
		}
		
		fund.setContractId(contractId);
		fund.setMoney(money);
		fund.setPayType(payType);
		fund.setReceiptNumber(receiptNumber);
		fund.setStatus(id == null ? 0 : status);
		fund.setType(type);
		
		Map request1=(Map)ServletActionContext.getContext().get("request");
		
		if(id != null) {
			fundDAO.getHibernateTemplate().update(fund);
			request1.put("msg", "资金信息修改成功");
		} else {
			fundDAO.save(fund);
			request1.put("msg", "资金信息添加成功");
		}
		
		fundMana(); // return to list page
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * delete fund
	 * @return
	 */
	public String fundDel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = (Integer) request.getAttribute("id");
		TFund fund = fundDAO.findById(id);
		fundDAO.delete(fund);
		
		fundMana(); //return to list page
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * user can just view the fund
	 * @return
	 */
	public String fundView() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");
		List<TContract> conList = contractDAO.findByUserId(user.getUserId());
		List<TFund> list = new ArrayList<TFund>(); 
		if(conList != null && conList.size() > 0) {
			for(TContract con : conList) {
				List<TFund> fundList = fundDAO.findByContractId(con.getContractId());
				list.addAll(fundList);
			}
		}
		
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("fundList", list);
		return ActionSupport.SUCCESS; 
	}
	
	/**
	 * user search fund records
	 */
	public String fundSearch() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer contractId = (Integer) request.getAttribute("contractId");
		String receiptNumber = (String) request.getAttribute("receiptNumber");
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");
		
		Map request1=(Map)ServletActionContext.getContext().get("request");
		
		List<TContract> conList = contractDAO.findByUserId(user.getUserId());
		List<TFund> list = new ArrayList<TFund>(); 
		TFund instance = new TFund();
		if(receiptNumber != null && !receiptNumber.isEmpty()) {
			instance.setReceiptNumber(receiptNumber);
			request1.put("searchReceiptNumber", receiptNumber);
		}
		if(contractId == null) {
			if(conList != null && conList.size() > 0) {
				for(TContract con : conList) {
					instance.setContractId(con.getContractId());
					List<TFund> fundList = fundDAO.findByExample(instance);
					list.addAll(fundList);
				}
			}
		} else {
			request1.put("searchContractId", contractId);
			instance.setContractId(contractId);
			List<TFund> fundList = fundDAO.findByExample(instance);
			list.addAll(fundList);
		}
		
		request1.put("fundList", list);
		return ActionSupport.SUCCESS; 
	}
	
	/**
	 * user can see the detail of fund
	 */
	public String fundDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = (Integer) request.getAttribute("id");
		TFund fund = fundDAO.findById(id);
		TContract contract = contractDAO.findById(fund.getContractId());
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("contract", contract);
		request1.put("fund", fund);
		request1.put("contractId", contractId);
		return ActionSupport.SUCCESS; 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public TContractDAO getContractDAO() {
		return contractDAO;
	}

	public void setContractDAO(TContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}

	public TAdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(TAdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public TUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(TUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public TZulinDAO getZulinDAO() {
		return zulinDAO;
	}

	public void setZulinDAO(TZulinDAO zulinDAO) {
		this.zulinDAO = zulinDAO;
	}

	public TFundDAO getFundDAO() {
		return fundDAO;
	}

	public void setFundDAO(TFundDAO fundDAO) {
		this.fundDAO = fundDAO;
	}
	
}
