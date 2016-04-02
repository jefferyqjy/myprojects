package com.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.TAdminDAO;
import com.dao.TContractDAO;
import com.dao.TUserDAO;
import com.dao.TZulinDAO;
import com.model.TAdmin;
import com.model.TContract;
import com.model.TUser;
import com.model.TZulin;
import com.opensymphony.xwork2.ActionSupport;

public class contractAction extends ActionSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2059059988896172110L;
	
	private Integer id;
	
	private Integer zulinId;
	
	private Integer adminId;
	private String adminName;
	
	private Integer userId;
	private String userName;

	private String content;
	
	private Integer status;
	
	private Integer parentContractId;
	
	private Date startDate;
	
	private Date endDate;
	
	private String startDateStr;
	
	private String endDateStr;
	
	private String adminSignPic;
	
	private String userSignPic;
	
	private TContractDAO contractDAO;
	
	private TAdminDAO adminDAO;
	
	private TUserDAO userDAO;
	
	private TZulinDAO zulinDAO;
	
	/**
	 * forward to page of the list of contracts  
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String contractMana() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		String sqlString = "";
		// if user is the landlord, then find contract according to the landlord
		if ("1".equals(admin.getUserType())) {
			sqlString = " and adminId=" + admin.getUserId();
		}
		String sql = " from TContract where 1=1 " + sqlString;
		List<TContract> conList = contractDAO.getHibernateTemplate().find(sql);
		Map<String, Object> request1 = (Map) ServletActionContext.getContext().get("request");
		if(conList != null && !conList.isEmpty()) {
			for(TContract con : conList) {
				// set user name and admin name
				TAdmin admin1 = adminDAO.findById(con.getAdminId());
				if(admin1 != null) {
					con.setAdminName(admin1.getUserName());
				}
				
				TUser user = userDAO.findById(con.getUserId());
				if(user != null) {
					con.setUserName(user.getUserName());
				}
				
				// if 1. cannot find records through parent contract id; 2. current date > endDate -> set expired as true
				Date date = new Date();
				if(date.compareTo(con.getEndDate()) > 0) {
					con.setExpired(1);
				}
			}
		}
		
		request1.put("contractList", conList);
		return ActionSupport.SUCCESS;
		
	}
	
	/**
	 * forward to create/update page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String contractAddShow() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		TAdmin admin = (TAdmin) session.getAttribute("admin");
		
		List<TZulin> zulinList = zulinDAO.findByProperty("belonguserId", admin.getUserId());
		
		Map<String, Object> request1 = (Map) ServletActionContext.getContext().get("request");
		request1.put("zulinList", zulinList);
		request1.put("admin", admin);
		
		Integer contractId = (Integer) request.getAttribute("id");
		// udpate
		if(contractId != null) {
			TContract contract = contractDAO.findById(contractId);
			if(contract != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				request1.put("content", contract.getContent());
				request1.put("startDate", df.format(contract.getStartDate()));
				request1.put("endDate", df.format(contract.getEndDate()));
				request1.put("zulinId", contract.getZulinId());
				request1.put("status", contract.getStatus());
				request1.put("id", contract.getContractId());
			}
		}
		
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * save/update contract
	 * @return
	 */
	public String contractAddSave() {
		
		TContract contract = new TContract();
		
		if(id != null) {
			contract = contractDAO.findById(id);
		}
		
		contract.setAdminId(adminId);
		contract.setAdminName(adminName);
		contract.setContent(content);
		contract.setStatus(status);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			if(startDateStr == null || startDateStr.equals("")) {
				contract.setStartDate(new Date());
			} else {
				contract.setStartDate(df.parse(startDateStr));
			}
			
			if(endDateStr == null || endDateStr.equals("")) {
				contract.setEndDate(new Date());
			} else {
				contract.setEndDate(df.parse(endDateStr));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(id == null) {
			contract.setStatus(0);
		}
		
		TZulin zulin = zulinDAO.findById(zulinId);
		contract.setZulinId(zulinId);
		contract.setUserId(zulin.getUserId());
		contract.setUserName(userName);
		
		Map request1=(Map)ServletActionContext.getContext().get("request");
		
		if(id != null) {
			contractDAO.getHibernateTemplate().update(contract);
			request1.put("msg", "合同信息修改成功");
		} else {
			contractDAO.save(contract);
			request1.put("msg", "合同信息添加成功");
		}
		
		contractMana(); // return to list page
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * delete contract
	 * @return
	 */
	public String contractDel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = (Integer) request.getAttribute("id");
		TContract contract = contractDAO.findById(id);
		contractDAO.delete(contract);
		
		contractMana(); // return to list page
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * user can just view the contract
	 * @return
	 */
	public String contractView() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");
		List<TContract> list = contractDAO.findByUserId(user.getUserId());
		if(list != null && list.size() > 0) {
			for(TContract con : list) {
				TAdmin admin = adminDAO.findById(con.getAdminId());
				con.setAdminName(admin.getUserName());
			}
		}
		
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("contractList", list);
		return ActionSupport.SUCCESS; 
	}
	
	/**
	 * user can see the detail of contract 
	 */
	public String contractDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer id = (Integer) request.getAttribute("id");
		TContract contract = contractDAO.findById(id);
		TAdmin admin = adminDAO.findById(contract.getAdminId());
		contract.setAdminName(admin.getUserName());
		Map request1=(Map)ServletActionContext.getContext().get("request");
		request1.put("contract", contract);
		return ActionSupport.SUCCESS; 
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getParentContractId() {
		return parentContractId;
	}

	public void setParentContractId(Integer parentContractId) {
		this.parentContractId = parentContractId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public String getStartDateInfo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return startDate == null ? "" : df.format(startDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getEndDateInfo() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return endDate == null ? "" : df.format(endDate);
	}

	public String getAdminSignPic() {
		return adminSignPic;
	}

	public void setAdminSignPic(String adminSignPic) {
		this.adminSignPic = adminSignPic;
	}

	public String getUserSignPic() {
		return userSignPic;
	}

	public void setUserSignPic(String userSignPic) {
		this.userSignPic = userSignPic;
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

	public Integer getZulinId() {
		return zulinId;
	}

	public void setZulinId(Integer zulinId) {
		this.zulinId = zulinId;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
}
