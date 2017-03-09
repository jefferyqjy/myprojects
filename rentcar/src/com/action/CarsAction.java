package com.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.business.BrandBusiness;
import com.business.CarsBusiness;
import com.business.CateBusiness;
import com.entity.BrandEntity;
import com.entity.CarsEntity;
import com.entity.CateEntity;
import com.opensymphony.xwork2.ActionSupport;

public class CarsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private CarsEntity cars;
	private List<CarsEntity> list;
	private CarsBusiness carsBusiness;
	private BrandBusiness brandBusiness;
	private CateBusiness cateBusiness;
	private int pageNumber;
	private int maxPage;
	private String html;
	private String number;
	private String id;
	private String name;
	private String cond;
	private Map<String, Object> map = new HashMap<String, Object>();

	public String createCars() {
		List<CateEntity> cateList = this.cateBusiness.show();
		List<BrandEntity> brandList = this.brandBusiness.show();
		this.map.put("cateList", cateList);
		this.map.put("brandList", brandList);
		return SUCCESS;
	}

	public String addCars() {
		this.cars.setCarsid(UUID.randomUUID().toString());
		this.carsBusiness.save(this.cars);
		return SUCCESS;
	}

	public String deleteCars() {
		try {
			this.carsBusiness.delete(cars);
		} catch (Exception e) {
			this.map.put("msg", "存在关联项 不能删除");
		}
		return SUCCESS;
	}

	public String updateCars() {
		this.carsBusiness.update(cars);
		return SUCCESS;
	}

	public String getAllCars() {
		this.list = new ArrayList<CarsEntity>();
		List<CarsEntity> tempList = new ArrayList<CarsEntity>();
		tempList = this.carsBusiness.show();
		this.pageNumber = tempList.size();
		this.maxPage = this.pageNumber;
		if (this.maxPage % 10 == 0) {
			this.maxPage = this.maxPage / 10;
		} else {
			this.maxPage = this.maxPage / 10 + 1;
		}
		if (this.number == null) {
			this.number = "0";
		}
		int start = Integer.parseInt(this.number) * 10;
		int over = (Integer.parseInt(this.number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			CarsEntity u = (CarsEntity) tempList.get(i);
			this.list.add(u);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(this.number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"cars/getAllCars.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(this.number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"cars/getAllCars.action?number="
					+ (Integer.parseInt(this.number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"cars/getAllCars.action?number="
					+ (Integer.parseInt(this.number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (this.maxPage <= (Integer.parseInt(this.number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"cars/getAllCars.action?number="
					+ (this.maxPage - 1) + "\">尾页</a>");
		}
		this.html = buffer.toString();
		return SUCCESS;
	}

	public String getCarsById() {
		List<CateEntity> cateList = this.cateBusiness.show();
		List<BrandEntity> brandList = this.brandBusiness.show();
		this.map.put("cateList", cateList);
		this.map.put("brandList", brandList);
		this.cars = this.carsBusiness.checkId(this.id);
		return SUCCESS;
	}

	public String queryCarsByCond() {
		this.list = new ArrayList<CarsEntity>();
		if ("carno".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeCarno(this.name);
		}
		if ("image".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeImage(this.name);
		}
		if ("cateid".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeCateid(this.name);
		}
		if ("brandid".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeBrandid(this.name);
		}
		if ("price".equals(this.cond)) {
			list = this.carsBusiness.checkByLikePrice(this.name);
		}
		if ("colour".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeColour(this.name);
		}
		if ("degree".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeDegree(this.name);
		}
		if ("displacement".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeDisplacement(this.name);
		}
		if ("special".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeSpecial(this.name);
		}
		if ("recommend".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeRecommend(this.name);
		}
		if ("contents".equals(this.cond)) {
			list = this.carsBusiness.checkByLikeContents(this.name);
		}
		return SUCCESS;
	}

	public CarsEntity getCars() {
		return cars;
	}

	public void setCars(CarsEntity cars) {
		this.cars = cars;
	}

	public List<CarsEntity> getList() {
		return list;
	}

	public void setList(List<CarsEntity> list) {
		this.list = list;
	}

	public CarsBusiness getCarsBusiness() {
		return carsBusiness;
	}

	public void setCarsBusiness(CarsBusiness carsBusiness) {
		this.carsBusiness = carsBusiness;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BrandBusiness getBrandBusiness() {
		return brandBusiness;
	}

	public void setBrandBusiness(BrandBusiness brandBusiness) {
		this.brandBusiness = brandBusiness;
	}

	public CateBusiness getCateBusiness() {
		return cateBusiness;
	}

	public void setCateBusiness(CateBusiness cateBusiness) {
		this.cateBusiness = cateBusiness;
	}

}
