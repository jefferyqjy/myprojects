package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.Product;
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
import java.util.List;
import com.pro.manager.ProductManager;
import com.pro.manager.SupplierManager;
@Controller
public class ProductAction {
	@Resource
	private ProductManager productManager;
	
	@Resource
	private SupplierManager supplierManager;
 private int id;
	
	
	private String nameid;
	
	
	
	private String name;
	
	
	
	private String suppliernaem;
	
	
	private String suppliergenid;
	
	
	private String supplierid;
	
	

	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameid() {
		return nameid;
	}

	public void setNameid(String nameid) {
		this.nameid = nameid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			return "gotoadd";
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return "gotoadd";
		}
	}
	
	public String add() {
		Product entity = new Product();
		try {
			entity.setNameid(this.nameid);
			entity.setRemark(this.remark);
			//entity.setSuppliernaem(this.suppliernaem);
			//entity.setSuppliergenid(this.suppliergenid);
			//entity.setSupplierid(this.supplierid);
			if(!StringUtils.isEmpty(suppliernaem)){
				
				Supplier sup = supplierManager.queryById(Integer.parseInt(suppliernaem));
				entity.setSuppliergenid(sup.getId()+"");
				entity.setSuppliernaem(sup.getName());
				entity.setSupplierid(sup.getNameid());
			}
			
			
			entity.setName(this.name);
			this.productManager.add(entity);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			
			List<Supplier> list = supplierManager.queryAll();
			ActionContext.getContext().put(Const.Action.PAGE_REUSLT, list);
			
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		this.productManager.deleteViaId(this.id);
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.productManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return Const.Pages.QUERY_DO;
	}

	public String update() {
		Product entity = this.productManager.queryById(this.id);
		entity.setRemark(this.remark);
		/*entity.setSuppliernaem(this.suppliernaem);
		entity.setSuppliergenid(this.suppliergenid);
		entity.setSupplierid(this.supplierid);*/
		if(!StringUtils.isEmpty(suppliernaem)){
			Supplier sup = supplierManager.queryById(Integer.parseInt(suppliernaem));
			entity.setSuppliergenid(sup.getId()+"");
			entity.setSuppliernaem(sup.getName());
			entity.setSupplierid(sup.getNameid());
		}else{
			entity.setSuppliernaem("");
			entity.setSuppliergenid("");
			entity.setSupplierid("");
		}
		this.productManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		
		List<Supplier> list = supplierManager.queryAll();
		ActionContext.getContext().put("suplist", list);
		
		Product entity = this.productManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String detail() {
		Product entity = this.productManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Product entity = new Product();
		entity.setNameid(this.nameid);
		entity.setName(this.name);
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
		Page<Product> page = this.productManager.getRecords(condition);
		List<Product> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}

	public String query2() {
		return this.query();
 }	public String select() {
		return this.query();
 }}