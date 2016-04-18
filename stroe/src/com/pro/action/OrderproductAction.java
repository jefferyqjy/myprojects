package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.Orderproduct;
import com.pro.entity.Product;
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

import java.util.ArrayList;
import java.util.List;
import com.pro.manager.OrderproductManager;
import com.pro.manager.ProductManager;
import com.pro.manager.SupplierManager;
@Controller
public class OrderproductAction {
	@Resource
	private OrderproductManager orderproductManager;
	
	@Resource
	private ProductManager productManager;
	
	@Resource
	private SupplierManager supplierManager;
	
	private int id;
	
	
	private String orderid;
	
	
	private String orderstatus;
	
	
	private String dateorder;
	
	
	private String datefinished;
	
	
	
	private String productid;
	
	
	private String productgenid;
	
	
	private String productname;
	
	
	private String suppliernaem;
	
	
	private String suppliergenid;
	
	
	private String supplierid;
	
	
	private String amount;
	
	
	private String datebirth;
	
	
	private String datequality;
	
	
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getDateorder() {
		return dateorder;
	}

	public void setDateorder(String dateorder) {
		this.dateorder = dateorder;
	}

	public String getDatefinished() {
		return datefinished;
	}

	public void setDatefinished(String datefinished) {
		this.datefinished = datefinished;
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

	public String getSuppliernaem() {
		return suppliernaem;
	}

	public void setSuppliernaem(String suppliernaem) {
		this.suppliernaem = suppliernaem;
	}

	public String getSuppliergenid() {
		return suppliergenid;
	}

	public void setSuppliergenid(String suppliergenid) {
		this.suppliergenid = suppliergenid;
	}

	public String getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(String datebirth) {
		this.datebirth = datebirth;
	}

	public String getDatequality() {
		return datequality;
	}

	public void setDatequality(String datequality) {
		this.datequality = datequality;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

	private DefaultQueryCondition condition;
	private Page page;
	
	public String gotoadd() {
		Product entity = new Product();
		try {
			List<Supplier> list = supplierManager.queryAll();
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);
			
			List<Product> listp = productManager.queryAll();
			ActionContext.getContext().put("resultpro", listp);
			
			return "gotoadd";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return "gotoadd";
		}
	}
	
	public String add() {
		Orderproduct entity = new Orderproduct();
		try {
			entity.setRemark(this.remark);
			entity.setDatebirth(this.datebirth);
			entity.setOrderid(this.orderid);
			entity.setOrderstatus(this.orderstatus);
			entity.setDateorder(this.dateorder);
			entity.setDatefinished(this.datefinished);
			/*entity.setProductid(this.productid);
			entity.setProductgenid(this.productgenid);
			entity.setProductname(this.productname);*/
			if(!StringUtils.isEmpty(productname)){
				Product pro = productManager.queryById(Integer.parseInt(productname));
				entity.setProductid(pro.getNameid());
				entity.setProductgenid(pro.getId()+"");
				entity.setProductname(pro.getName());
			}
			
			
			/*entity.setSuppliernaem(this.suppliernaem);
			entity.setSuppliergenid(this.suppliergenid);
			entity.setSupplierid(this.supplierid);*/
			if(!StringUtils.isEmpty(suppliernaem)){
				Supplier sup = supplierManager.queryById(Integer.parseInt(suppliernaem));
				entity.setSuppliergenid(sup.getId()+"");
				entity.setSuppliernaem(sup.getName());
				entity.setSupplierid(sup.getNameid());
			}
			
			entity.setAmount(this.amount);
			entity.setDatequality(this.datequality);
			this.orderproductManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			List<Supplier> list = supplierManager.queryAll();
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);
			List<Product> listp = productManager.queryAll();
			ActionContext.getContext().put("resultpro", listp);
			
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.orderproductManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.orderproductManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Orderproduct entity = this.orderproductManager.queryById(this.id);
		entity.setRemark(this.remark);
		entity.setDatebirth(this.datebirth);
		entity.setDateorder(this.dateorder);
		entity.setDatefinished(this.datefinished);
		entity.setOrderstatus(orderstatus);
		/*entity.setProductid(this.productid);
		entity.setProductgenid(this.productgenid);*/
		/*entity.setSuppliernaem(this.suppliernaem);
		entity.setSuppliergenid(this.suppliergenid);
		entity.setSupplierid(this.supplierid);*/
		
		if(!StringUtils.isEmpty(suppliernaem)){
			Supplier sup = supplierManager.queryById(Integer.parseInt(suppliernaem));
			entity.setSuppliergenid(sup.getId()+"");
			entity.setSuppliernaem(sup.getName());
			entity.setSupplierid(sup.getNameid());
		}
		
		entity.setAmount(this.amount);
		entity.setDatequality(this.datequality);
		this.orderproductManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		
		List<Supplier> list = supplierManager.queryAll();
		ActionContext.getContext().put("suplist", list);
		
		List<Product> listp = productManager.queryAll();
		ActionContext.getContext().put("resultpro", listp);
		
		Orderproduct entity = this.orderproductManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Orderproduct entity = this.orderproductManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Orderproduct entity = new Orderproduct();
		entity.setOrderid(this.orderid);
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
		Page<Orderproduct> page = this.orderproductManager.getRecords(condition);
		List<Orderproduct> resultList = page.getList();
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
 	
 public String tongjibygonghong() {
		
		
		List<Orderproduct> resultList = new ArrayList<Orderproduct>();
		List list = orderproductManager.queryBySql("select sum(t_amount), t_productname,t_suppliernaem from t_orderproduct where t_orderstatus ='订单完成' group by t_suppliernaem, t_productname ");
		
		for(int i =0;i<list.size(); i++){
			Orderproduct ss = new Orderproduct();
			Object[] objsale = (Object[]) list.get(i);
			double dd = Double.parseDouble((objsale[0]+""));
			String name = (String) objsale[1];
			String name2 = (String) objsale[2];
			ss.setAmount(dd+"");
			ss.setProductname(name);
			ss.setSuppliernaem(name2);
			resultList.add(ss);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}

 public String tongjibyshangpin() {
		
		
		List<Orderproduct> resultList = new ArrayList<Orderproduct>();
		List list = orderproductManager.queryBySql("select sum(t_amount), t_productname,t_suppliernaem from t_orderproduct where t_orderstatus ='订单完成' group by t_productname, t_suppliernaem ");
		
		for(int i =0;i<list.size(); i++){
			Orderproduct ss = new Orderproduct();
			Object[] objsale = (Object[]) list.get(i);
			double dd = Double.parseDouble((objsale[0]+""));
			String name = (String) objsale[1];
			String name2 = (String) objsale[2];
			ss.setAmount(dd+"");
			ss.setProductname(name);
			ss.setSuppliernaem(name2);
			resultList.add(ss);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}
 
 	public String guoqi() {
		
		
		List<Orderproduct> resultList = new ArrayList<Orderproduct>();
		List list = orderproductManager.queryBySql("select t_orderid, t_productname,t_suppliernaem, t_datequality, TO_DAYS(t_datequality) - TO_DAYS(NOW()) from t_orderproduct where t_orderstatus ='订单完成' and TO_DAYS(t_datequality) - TO_DAYS(NOW()) <= 30");
		
		for(int i =0;i<list.size(); i++){
			Orderproduct ss = new Orderproduct();
			Object[] objsale = (Object[]) list.get(i);
			ss.setOrderid((String)objsale[0]);
			ss.setProductname((String)objsale[1]);
			ss.setSuppliernaem((String)objsale[2]);
			ss.setDatequality((String)objsale[3]);
			ss.setRemark("此订单的商品还有"+Integer.parseInt(objsale[4]+"")+"天过期");
			resultList.add(ss);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}
 	
public String chaochu() {
		
		
		List<Orderproduct> resultList = new ArrayList<Orderproduct>();
		List list = orderproductManager.queryBySql("select t_orderid, t_productname,t_suppliernaem, t_datequality from t_orderproduct where t_orderstatus ='订单完成' and TO_DAYS(t_datequality) - TO_DAYS(NOW()) <= 0");
		
		
		for(int i =0;i<list.size(); i++){
			Orderproduct ss = new Orderproduct();
			Object[] objsale = (Object[]) list.get(i);
			ss.setOrderid((String)objsale[0]);
			ss.setProductname((String)objsale[1]);
			ss.setSuppliernaem((String)objsale[2]);
			ss.setDatequality((String)objsale[3]);
			ss.setRemark("此商品已过期");
			resultList.add(ss);
		}
		
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		return Const.Pages.MAPPING_URL;
	}
}