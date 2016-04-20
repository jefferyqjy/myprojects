package com.pro.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.base.common.util.CommonUtil;
import com.base.common.util.Const;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import com.pro.entity.Product;
import com.pro.entity.Saleproduct;
import com.pro.manager.OrderproductManager;
import com.pro.manager.ProductManager;
import com.pro.manager.SaleproductManager;
import com.pro.manager.SupplierManager;
@Controller
public class SaleproductAction {
	@Resource
	private SaleproductManager saleproductManager;
	
	@Resource
	private OrderproductManager orderproductManager;
	
	@Resource
	private ProductManager productManager;
	
	@Resource
	private SupplierManager supplierManager;
	
 private int id;
	
	
	private String productid;
	
	
	private String productgenid;
	
	
	private String productname;
	
	
	private String orderstatus;
	
	
	private String amount;
	
	
	private String totalmoney;
	
	
	private Date datesale;
	
	
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductgenid() {
		return productgenid;
	}

	public void setProductgenid(String productgenid) {
		this.productgenid = productgenid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public Date getDatesale() {
		return datesale;
	}

	public void setDatesale(Date datesale) {
		this.datesale = datesale;
	}
	
	

	private DefaultQueryCondition condition;
	private Page page;
	
	public String gotoadd() {
		Product entity = new Product();
		try {
			/*List<Supplier> list = supplierManager.queryAll();
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);*/
			
			List<Product> listp = productManager.queryAll();
			ActionContext.getContext().put("resultpro", listp);
			
			return "gotoadd";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return "gotoadd";
		}
	}
	
	public String add() {
		Saleproduct entity = new Saleproduct();
		try {
			entity.setRemark(this.remark);
			entity.setOrderstatus(this.orderstatus);
			/*entity.setProductid(this.productid);
			entity.setProductgenid(this.productgenid);
			entity.setProductname(this.productname);*/
			
			if(!StringUtils.isEmpty(productname)){
				Product pro = productManager.queryById(Integer.parseInt(productname));
				entity.setProductid(pro.getNameid());
				entity.setProductgenid(pro.getId()+"");
				entity.setProductname(pro.getName());
			}
			
			entity.setAmount(this.amount);
			entity.setTotalmoney(this.totalmoney);
			entity.setDatesale(this.datesale);
			this.saleproductManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			List<Product> listp = productManager.queryAll();
			ActionContext.getContext().put("resultpro", listp);
			
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.saleproductManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.saleproductManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Saleproduct entity = this.saleproductManager.queryById(this.id);
		entity.setRemark(this.remark);
		/*entity.setProductid(this.productid);
		entity.setProductgenid(this.productgenid);*/
		entity.setAmount(this.amount);
		entity.setOrderstatus(orderstatus);
		entity.setTotalmoney(this.totalmoney);
		entity.setDatesale(this.datesale);
		this.saleproductManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		
		List<Product> listp = productManager.queryAll();
		ActionContext.getContext().put("resultpro", listp);
		
		Saleproduct entity = this.saleproductManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Saleproduct entity = this.saleproductManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Saleproduct entity = new Saleproduct();
		entity.setOrderstatus(this.orderstatus);
		entity.setProductname(this.productname);
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
		Page<Saleproduct> page = this.saleproductManager.getRecords(condition);
		List<Saleproduct> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	@SuppressWarnings("unchecked")
	public String kucun() {
		/*Saleproduct entity = new Saleproduct();
		entity.setOrderstatus(this.orderstatus);
		entity.setProductname(this.productname);
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
		Page<Saleproduct> page = this.saleproductManager.getRecords(condition);
		List<Saleproduct> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());*/
		HttpServletRequest request = ServletActionContext.getRequest();
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String supplierName = request.getParameter("supplierName");
		List<Saleproduct> returnlist = new ArrayList<Saleproduct>();
		StringBuffer salequery = new StringBuffer("select sum(t1.t_amount), t1.t_productname, p.t_nameid, p.t_suppliernaem from t_saleproduct t1, t_product p");
		salequery.append(" where t1.t_productid = p.t_nameid");
		StringBuffer orderquery = new StringBuffer("select sum(o.t_amount), o.t_productname, p.t_nameid, p.t_suppliernaem from t_orderproduct o, t_product p");
		orderquery.append(" where o.t_productid = p.t_nameid and t_orderstatus ='订单完成'");
		if(StringUtils.isNotEmpty(productId)) {
			salequery.append(" and p.t_nameid like '%").append(productId).append("'");
			orderquery.append(" and p.t_nameid like '%").append(productId).append("'");
		}
		if(StringUtils.isNotEmpty(productName)) {
			salequery.append(" and p.t_name like '%").append(productName).append("%'");
			orderquery.append(" and p.t_name like '%").append(productName).append("%'");
		}
		if(StringUtils.isNotEmpty(supplierName)) {
			salequery.append(" and p.t_suppliernaem like '%").append(supplierName).append("%'");
			orderquery.append(" and p.t_suppliernaem like '%").append(supplierName).append("%'");
		}
		salequery.append(" group by t1.t_productname");
		orderquery.append(" group by o.t_productname");
		List listsale = saleproductManager.queryBySql(salequery.toString());
		List listorder = orderproductManager.queryBySql(orderquery.toString());
		
		for(int i =0;i<listorder.size(); i++){
			Saleproduct entity = new Saleproduct();
			Object[] objorder = (Object[]) listorder.get(i);
			int amount = (int)Double.parseDouble((objorder[0]+""));
			String name = (String) objorder[1];
			boolean hasSale = false;
			for(int j =0;j<listsale.size(); j++){
				Object[] objsale = (Object[]) listsale.get(j);
				int amounttemp = (int)Double.parseDouble(objsale[0]+"");
				String nametemp= (String) objsale[1];
				if(name.equals(nametemp)){
					int aa = amount - amounttemp;
					entity.setProductname(name);
					entity.setAmount(aa+"");
					hasSale = true;
					break;
				}
				
			}
			if(!hasSale){
				entity.setProductname(name);
				entity.setAmount(amount+"");
			}
			String productnameid = (String) objorder[2];
			String suppliername = (String) objorder[3];
			entity.setProductnameid(productnameid);
			entity.setSuppliername(suppliername);
			returnlist.add(entity);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		/*ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());*/
		return Const.Pages.MAPPING_URL;
	}
	
	
	
	public String query2() {
		return this.query();
 }	public String select() {
		return this.query();
 }
 	
 public String xiaoshoubyshangpin() {
		
		
		List<Saleproduct> resultList = new ArrayList<Saleproduct>();
		List listsale = saleproductManager.queryBySql("select sum(t_amount), t_productname from t_saleproduct group by t_productname");
		
		for(int j =0;j<listsale.size(); j++){
			Saleproduct entity = new Saleproduct();
			Object[] objsale = (Object[]) listsale.get(j);
			int amounttemp = (int)Double.parseDouble(objsale[0]+"");
			String nametemp= (String) objsale[1];
			entity.setProductname(nametemp);
			entity.setAmount(amounttemp+"");
			resultList.add(entity);
			
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}

 public String xiaoshoubykehu() {
		
		
		List<Saleproduct> resultList = new ArrayList<Saleproduct>();
		List listsale = saleproductManager.queryBySql("select sum(t_amount), t_productname from t_saleproduct group by t_productname");
		
		for(int j =0;j<listsale.size(); j++){
			Saleproduct entity = new Saleproduct();
			Object[] objsale = (Object[]) listsale.get(j);
			int amounttemp = (int)Double.parseDouble(objsale[0]+"");
			String nametemp= (String) objsale[1];
			entity.setProductname(nametemp);
			entity.setAmount(amounttemp+"");
			
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}
 
 	public String quehuo() {
		
		List<Saleproduct> returnlist = new ArrayList<Saleproduct>();
		List listsale = saleproductManager.queryBySql("select sum(t_amount), t_productname from t_saleproduct group by t_productname");
		List listorder = orderproductManager.queryBySql("select sum(t_amount), t_productname from t_orderproduct where t_orderstatus ='订单完成' group by t_productname ");
		
		for(int i =0;i<listorder.size(); i++){
			Saleproduct entity = new Saleproduct();
			Object[] objorder = (Object[]) listorder.get(i);
			int amount = (int)Double.parseDouble((objorder[0]+""));
			String name = (String) objorder[1];
			for(int j =0;j<listsale.size(); j++){
				Object[] objsale = (Object[]) listsale.get(j);
				int amounttemp = (int)Double.parseDouble(objsale[0]+"");
				String nametemp= (String) objsale[1];
				if(name.equals(nametemp)){
					int aa = amount - amounttemp;
					entity.setProductname(name);
					entity.setAmount(aa+"");
					if(aa <=20){
						entity.setRemark("商品少于20件，请备货！");
					} 
					break;
				}
				
			}
			returnlist.add(entity);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		
		return Const.Pages.MAPPING_URL;
	}
 	
 	public String chaochu() {
		
		List<Saleproduct> returnlist = new ArrayList<Saleproduct>();
		List listsale = saleproductManager.queryBySql("select sum(t_amount), t_productname from t_saleproduct group by t_productname");
		List listorder = orderproductManager.queryBySql("select sum(t_amount), t_productname from t_orderproduct where t_orderstatus ='订单完成' group by t_productname ");
		
		for(int i =0;i<listorder.size(); i++){
			Saleproduct entity = new Saleproduct();
			Object[] objorder = (Object[]) listorder.get(i);
			int amount = (int)Double.parseDouble((objorder[0]+""));
			String name = (String) objorder[1];
			boolean hasSale = false;
			for(int j =0;j<listsale.size(); j++){
				Object[] objsale = (Object[]) listsale.get(j);
				int amounttemp = (int)Double.parseDouble(objsale[0]+"");
				String nametemp= (String) objsale[1];
				if(name.equals(nametemp)){
					int aa = amount - amounttemp;
					entity.setProductname(name);
					entity.setAmount(aa+"");
					hasSale = true;
					break;
				}
			}
			if(!hasSale){
				entity.setProductname(name);
				entity.setAmount(amount+"");
			}
			Integer count = Integer.valueOf(entity.getAmount().trim());
			if(count >= 5000) {
				entity.setRemark("商品已超储，请注意！");
			}
			returnlist.add(entity);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, returnlist);
		
		return Const.Pages.MAPPING_URL;
	}
}