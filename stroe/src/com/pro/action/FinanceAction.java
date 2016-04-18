package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.Customerinfo;
import com.pro.entity.Finance;
import com.pro.entity.Product;
import com.pro.entity.Saleproduct;
import com.pro.entity.Statistics;
import com.pro.entity.Supplier;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pro.manager.CustomerinfoManager;
import com.pro.manager.FinanceManager;
import com.pro.manager.ProductManager;
import com.pro.manager.SaleproductManager;
import com.pro.manager.SupplierManager;
@Controller
public class FinanceAction {
	@Resource
	private FinanceManager financeManager;
	@Resource
	private ProductManager productManager;
	
	@Resource
	private SupplierManager supplierManager;
	
	@Resource
	private CustomerinfoManager customerinfoManager;
	
	@Resource
	private SaleproductManager saleproductManager;
	
 private int id;
 private Date datefin1;
	private Date datefin2;
	
	
	public Date getDatefin1() {
		return datefin1;
	}

	public void setDatefin1(Date datefin1) {
		this.datefin1 = datefin1;
	}

	public Date getDatefin2() {
		return datefin2;
	}

	public void setDatefin2(Date datefin2) {
		this.datefin2 = datefin2;
	}

 
	
	
	private String financetype;
	
	
	private String appgenid;
	
	
	private String appnameid;
	
	
	
	private String supname;
	
	
	private int custgenid;
	
	
	private String custnameid;
	
	
	
	private String custname;
	
	
	private Date datefin;
	
	
	private String totalmoney;
	
	
	private String remark;
	
	

	private DefaultQueryCondition condition;
	private Page page;
	
	public String gotoadd() {
		Product entity = new Product();
		try {
			List<Supplier> list = supplierManager.queryAll();
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);
			
			List<Customerinfo> listcust = customerinfoManager.queryAll();
			ActionContext.getContext().put("resultcust", listcust);
			
			return "gotoadd";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return "gotoadd";
		}
	}
	
	public String add() {
		Finance entity = new Finance();
		/*entity.setAppgenid(appgenid);
		entity.setAppnameid(appnameid);
		entity.setSupname(supname);*/
		if(!StringUtils.isEmpty(custname)){
			Customerinfo cust = customerinfoManager.queryById(Integer.parseInt(custname));
			entity.setCustgenid(cust.getId());
			entity.setCustnameid(cust.getNameid());
			entity.setCustname(cust.getName());
		}
		
		
		/*entity.setCustgenid(custgenid);
		entity.setCustname(custname);
		entity.setCustnameid(custnameid);*/
		if(!StringUtils.isEmpty(supname)){
			Supplier sup = supplierManager.queryById(Integer.parseInt(supname));
			entity.setAppgenid(sup.getId()+"");
			entity.setAppnameid(sup.getNameid());
			entity.setSupname(sup.getName());
		}
		
		entity.setDatefin(datefin);
		entity.setFinancetype(financetype);
		entity.setRemark(remark);
		entity.setTotalmoney(totalmoney);
		try {
			this.financeManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			List<Supplier> list = supplierManager.queryAll();
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);
			
			List<Customerinfo> listcust = customerinfoManager.queryAll();
			ActionContext.getContext().put("resultcust", listcust);
			
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.financeManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.financeManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Finance entity = this.financeManager.queryById(this.id);
		/*entity.setAppgenid(appgenid);
		entity.setAppnameid(appnameid);
		entity.setSupname(supname);*/
		if(!StringUtils.isEmpty(supname)){
			Customerinfo cust = customerinfoManager.queryById(Integer.parseInt(supname));
			entity.setAppgenid(cust.getId()+"");
			entity.setAppnameid(cust.getNameid());
			entity.setSupname(cust.getName());
		}
		
		
		/*entity.setCustgenid(custgenid);
		entity.setCustname(custname);
		entity.setCustnameid(custnameid);*/
		if(!StringUtils.isEmpty(custname)){
			Customerinfo cust = customerinfoManager.queryById(Integer.parseInt(custname));
			entity.setCustgenid(cust.getId());
			entity.setCustnameid(cust.getNameid());
			entity.setCustname(cust.getName());
		}
		
		//entity.setDatefin(datefin);
		entity.setFinancetype(financetype);
		entity.setRemark(remark);
		entity.setTotalmoney(totalmoney);
		
		this.financeManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		List<Supplier> list = supplierManager.queryAll();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);
		
		List<Customerinfo> listcust = customerinfoManager.queryAll();
		ActionContext.getContext().put("resultcust", listcust);
		
		Finance entity = this.financeManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Finance entity = this.financeManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Finance entity = new Finance();
		condition = new DefaultQueryCondition(entity);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter(Page.CURRENT_PAGE);
		String pageSize = request.getParameter(Page.PAGE_SIZE);
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Finance> page = this.financeManager.getRecords(condition);
		List<Finance> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	public String query2() {
		return this.query();
 }	public String select() {
		return this.query();
 }
 
 public String shouru() {
		
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(today);
		List listsale = saleproductManager.queryBySql("select sum(t_totalmoney), t_productname from t_saleproduct where Date(t_datesale) = '"+date+"'  group by t_productname");
		//List listfin = financeManager.queryBySql("select t_totalmoney, t_supname,t_custname,t_financetype from t_finance where Date(t_datefin) = '"+date+"' and (t_financetype ='客户还款' or t_financetype ='其他收入') ");
		List listfin = financeManager.queryBySql("select t_totalmoney, t_custname,t_financetype from t_finance where Date(t_datefin) = '"+date+"' and (t_financetype ='客户还款' or t_financetype ='其他收入') ");
		double totamount =0.00;
		
		List<Statistics> returnlist = new ArrayList<Statistics>();
		for(int i =0;i<listsale.size(); i++){
			Statistics ss = new Statistics();
			Object[] objsale = (Object[]) listsale.get(i);
			totamount += Double.parseDouble((objsale[0]+""));
			String name = (String) objsale[1];
			ss.setAmount(Double.parseDouble((objsale[0]+""))+"");
			ss.setName(name);
			ss.setType("产品销售");
			returnlist.add(ss);
		}
		
		for(int i =0;i<listfin.size(); i++){
			Statistics ss = new Statistics();
			Object[] objfin = (Object[]) listfin.get(i);
			//int amount = (int)Double.parseDouble((objfin[0]+""));
			totamount += Double.parseDouble((objfin[0]+""));
			String name = (String) objfin[1];
			ss.setAmount(Double.parseDouble((objfin[0]+""))+"");
			ss.setName(name);
			ss.setType((String) objfin[2]);
			returnlist.add(ss);
		}
		Statistics ss = new Statistics();
		ss.setAmount(totamount+"");
		ss.setName("总计");
		returnlist.add(ss);
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		return Const.Pages.MAPPING_URL;
	}
 
 
 public String zhichu() {
		
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(today);
		//List listsale = saleproductManager.queryBySql("select sum(t_totalmoney), t_productname from t_saleproduct where Date(t_datesale) = '"+date+"'  group by t_productname");
		//List listfin = financeManager.queryBySql("select t_totalmoney, t_supname,t_custname,t_financetype from t_finance where Date(t_datefin) = '"+date+"' and (t_financetype ='客户还款' or t_financetype ='其他收入') ");
		List listfin = financeManager.queryBySql("select t_totalmoney, t_supname,t_financetype from t_finance where Date(t_datefin) = '"+date+"' and (t_financetype ='付供货商欠款' or t_financetype ='其他支出') ");
		double totamount =0.00;
		
		List<Statistics> returnlist = new ArrayList<Statistics>();
		/*for(int i =0;i<listsale.size(); i++){
			Statistics ss = new Statistics();
			Object[] objsale = (Object[]) listsale.get(i);
			totamount += Double.parseDouble((objsale[0]+""));
			String name = (String) objsale[1];
			ss.setAmount(Double.parseDouble((objsale[0]+""))+"");
			ss.setName(name);
			ss.setType("产品销售");
			returnlist.add(ss);
		}*/
		
		for(int i =0;i<listfin.size(); i++){
			Statistics ss = new Statistics();
			Object[] objfin = (Object[]) listfin.get(i);
			//int amount = (int)Double.parseDouble((objfin[0]+""));
			totamount += Double.parseDouble((objfin[0]+""));
			String name = (String) objfin[1];
			ss.setAmount(Double.parseDouble((objfin[0]+""))+"");
			ss.setName(name);
			ss.setType((String) objfin[2]);
			returnlist.add(ss);
		}
		Statistics ss = new Statistics();
		ss.setAmount(totamount+"");
		ss.setName("总计");
		returnlist.add(ss);
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		return Const.Pages.MAPPING_URL;
	}
 
 
 public String queryshouru() {
	
	 if(datefin1 == null || datefin2 == null){
		 return Const.Pages.MAPPING_URL;
	 }
	 
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = sf.format(datefin1);
		String d2 = sf.format(datefin2);
		List listsale = saleproductManager.queryBySql("select sum(t_totalmoney), t_productname from t_saleproduct where Date(t_datesale) >= '"+d1+"' and  Date(t_datesale) <='"+d2+"'  group by t_productname");
		//List listfin = financeManager.queryBySql("select t_totalmoney, t_supname,t_custname,t_financetype from t_finance where Date(t_datefin) = '"+date+"' and (t_financetype ='客户还款' or t_financetype ='其他收入') ");
		List listfin = financeManager.queryBySql("select t_totalmoney, t_custname,t_financetype from t_finance where Date(t_datefin) >= '"+d1+"' and Date(t_datefin) <='"+d2+"' and (t_financetype ='客户还款' or t_financetype ='其他收入') ");
		double totamount =0.00;
		
		List<Statistics> returnlist = new ArrayList<Statistics>();
		for(int i =0;i<listsale.size(); i++){
			Statistics ss = new Statistics();
			Object[] objsale = (Object[]) listsale.get(i);
			totamount += Double.parseDouble((objsale[0]+""));
			String name = (String) objsale[1];
			ss.setAmount(Double.parseDouble((objsale[0]+""))+"");
			ss.setName(name);
			ss.setType("产品销售");
			returnlist.add(ss);
		}
		
		for(int i =0;i<listfin.size(); i++){
			Statistics ss = new Statistics();
			Object[] objfin = (Object[]) listfin.get(i);
			//int amount = (int)Double.parseDouble((objfin[0]+""));
			totamount += Double.parseDouble((objfin[0]+""));
			String name = (String) objfin[1];
			ss.setAmount(Double.parseDouble((objfin[0]+""))+"");
			ss.setName(name);
			ss.setType((String) objfin[2]);
			returnlist.add(ss);
		}
		Statistics ss = new Statistics();
		ss.setAmount(totamount+"");
		ss.setName("总计");
		returnlist.add(ss);
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		return Const.Pages.MAPPING_URL;
	}
 
 public String queryzhichu() {
		
	 if(datefin1 == null || datefin2 == null){
		 return Const.Pages.MAPPING_URL;
	 }
	 
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = sf.format(datefin1);
		String d2 = sf.format(datefin2);
		//List listsale = saleproductManager.queryBySql("select sum(t_totalmoney), t_productname from t_saleproduct where Date(t_datesale) >= '"+d1+"' and  Date(t_datesale) <='"+d2+"'  group by t_productname");
		//List listfin = financeManager.queryBySql("select t_totalmoney, t_supname,t_custname,t_financetype from t_finance where Date(t_datefin) = '"+date+"' and (t_financetype ='客户还款' or t_financetype ='其他收入') ");
		List listfin = financeManager.queryBySql("select t_totalmoney, t_supname,t_financetype from t_finance where Date(t_datefin) >= '"+d1+"' and Date(t_datefin) <='"+d2+"' and (t_financetype ='付供货商欠款' or t_financetype ='其他支出') ");
		double totamount =0.00;
		
		List<Statistics> returnlist = new ArrayList<Statistics>();
		/*for(int i =0;i<listsale.size(); i++){
			Statistics ss = new Statistics();
			Object[] objsale = (Object[]) listsale.get(i);
			totamount += Double.parseDouble((objsale[0]+""));
			String name = (String) objsale[1];
			ss.setAmount(Double.parseDouble((objsale[0]+""))+"");
			ss.setName(name);
			ss.setType("产品销售");
			returnlist.add(ss);
		}*/
		
		for(int i =0;i<listfin.size(); i++){
			Statistics ss = new Statistics();
			Object[] objfin = (Object[]) listfin.get(i);
			//int amount = (int)Double.parseDouble((objfin[0]+""));
			totamount += Double.parseDouble((objfin[0]+""));
			String name = (String) objfin[1];
			ss.setAmount(Double.parseDouble((objfin[0]+""))+"");
			ss.setName(name);
			ss.setType((String) objfin[2]);
			returnlist.add(ss);
		}
		Statistics ss = new Statistics();
		ss.setAmount(totamount+"");
		ss.setName("总计");
		returnlist.add(ss);
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		return Const.Pages.MAPPING_URL;
	}
 
 /*<option value="付供货商欠款">付供货商欠款</option>
 <option value="欠供货商货款">欠供货商货款</option>
 <option value="客户还款">客户还款</option>
 <option value="客户欠款">客户欠款</option>
 <option value="其他支出">其他支出</option>
 <option value="其他收入">其他收入</option>*/
 
 public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFinancetype() {
		return financetype;
	}

	public void setFinancetype(String financetype) {
		this.financetype = financetype;
	}

	public String getAppgenid() {
		return appgenid;
	}

	public void setAppgenid(String appgenid) {
		this.appgenid = appgenid;
	}

	public String getAppnameid() {
		return appnameid;
	}

	public void setAppnameid(String appnameid) {
		this.appnameid = appnameid;
	}

	public String getSupname() {
		return supname;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}

	public int getCustgenid() {
		return custgenid;
	}

	public void setCustgenid(int custgenid) {
		this.custgenid = custgenid;
	}

	public String getCustnameid() {
		return custnameid;
	}

	public void setCustnameid(String custnameid) {
		this.custnameid = custnameid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}