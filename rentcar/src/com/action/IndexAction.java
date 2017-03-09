package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.business.ArticleBusiness;
import com.business.BrandBusiness;
import com.business.CarsBusiness;
import com.business.CateBusiness;
import com.business.OrdersBusiness;
import com.business.TopicBusiness;
import com.business.UsersBusiness;
import com.entity.ArticleEntity;
import com.entity.BrandEntity;
import com.entity.CarsEntity;
import com.entity.CateEntity;
import com.entity.OrdersEntity;
import com.entity.TopicEntity;
import com.entity.UsersEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.util.VeDate;

public class IndexAction extends ActionSupport {
	/**
	 * 网站前台功能 的逻辑操作
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String msg;
	private String name;
	private String cond;
	private String title;
	private String repassword;
	private String newpassword;
	private String province;
	private int pageCur;
	private int totalCount = 0;
	private int totalPage = 0;

	private UsersEntity users;
	private TopicEntity topic;
	private OrdersEntity orders;

	private ArticleBusiness articleBusiness;
	private CateBusiness cateBusiness;
	private OrdersBusiness ordersBusiness;
	private TopicBusiness topicBusiness;
	private UsersBusiness usersBusiness;
	private BrandBusiness brandBusiness;
	private CarsBusiness carsBusiness;

	private Map<String, Object> map = new HashMap<String, Object>();

	// 公用方法
	private void front() {
		this.title = "五洲汽车租赁管理系统";
		List<CateEntity> cateList = this.cateBusiness.show();
		this.map.put("cateList", cateList);
		List<CarsEntity> hotList = this.carsBusiness.hot();
		this.map.put("hotList", hotList);
	}

	// 新闻公告
	public String article() {
		this.front();
		List<ArticleEntity> articleList = this.articleBusiness.show();
		this.map.put("articleList", articleList);
		return SUCCESS;
	}

	public String read() {
		this.front();
		ArticleEntity article = this.articleBusiness.checkId(id);
		this.map.put("article", article);
		return SUCCESS;
	}

	/**
	 * 用户部分开始
	 * 
	 * @return
	 */

	public String preLogin() {
		this.front();
		return SUCCESS;
	}

	public String login() {
		this.front();
		List<UsersEntity> usersList = this.usersBusiness
				.checkUsername(this.users.getUsername());
		if (usersList.size() == 0) {
			this.msg = "用户名不存在";
			return "loginerror";
		} else {
			UsersEntity user = usersList.get(0);
			if (user.getPassword().equals(this.users.getPassword())) {
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				session.put("userid", user.getUsersid());
				session.put("userinfo", user);
				session.put("realname", user.getRealname());
				session.put("users", user);
				this.msg = "登录成功";
			} else {
				this.msg = "密码错误";
				return "loginerror";
			}
		}
		return "success";
	}

	public String preReg() {
		this.front();
		return SUCCESS;
	}

	public String personal() {
		this.front();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		this.usersBusiness.update(users);
		session.put("users", users);
		return SUCCESS;
	}

	public String users() {
		this.front();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		return SUCCESS;
	}

	public String reg() {
		this.front();
		this.usersBusiness.save(users);
		return SUCCESS;
	}

	public String prePwd() {
		this.front();
		return SUCCESS;
	}

	public String editPwd() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		String userid = (String) session.get("userid");
		UsersEntity u = this.usersBusiness.checkId(userid);
		u.setPassword(repassword);
		this.usersBusiness.update(u);
		session.put("userinfo", u);
		return SUCCESS;
	}

	public String exit() {
		this.front();
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.clear();
		return SUCCESS;
	}

	/**
	 * 用户部分结束 商品部分开始
	 */

	public String detail() {
		this.front();
		CarsEntity cars = this.carsBusiness.checkId(id);
		this.map.put("cars", cars);
		List<TopicEntity> topicList = this.topicBusiness.checkCarsid(id);
		this.map.put("topicList", topicList);
		return SUCCESS;
	}

	public String query() {
		this.front();
		List<CarsEntity> carsList = this.carsBusiness.checkByLikeCarno(name);
		this.map.put("carsList", carsList);
		return SUCCESS;
	}

	public String recommend() {
		this.front();
		List<CarsEntity> carsList = this.carsBusiness.checkRecommend("是");
		this.map.put("carsList", carsList);
		return SUCCESS;
	}

	public String special() {
		this.front();
		List<CarsEntity> carsList = this.carsBusiness.checkSpecial("是");
		this.map.put("carsList", carsList);
		return SUCCESS;
	}

	public String cate() {
		this.front();
		List<CarsEntity> carsList = this.carsBusiness.checkCateid(this.name);
		this.map.put("carsList", carsList);
		return SUCCESS;
	}

	public String brand() {
		this.front();
		List<CarsEntity> carsList = this.carsBusiness.checkBrandid(this.name);
		this.map.put("carsList", carsList);
		return SUCCESS;
	}
	
	public String brandShow() {
		this.front();
		List<BrandEntity> brandList = this.brandBusiness.show();
		this.map.put("brandList", brandList);
		return SUCCESS;
	}

	public String all() {
		this.front();
		List<CarsEntity> carsList = this.carsBusiness.show();
		this.map.put("carsList", carsList);
		return SUCCESS;
	}

	public String addtopic() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		String userid = (String) session.get("userid");
		this.topic.setAddtime(VeDate.getStringDateShort());
		this.topic.setUsersid(userid);
		this.topicBusiness.save(topic);
		this.front();
		return SUCCESS;
	}

	public String index() {
		this.front();
		List<CarsEntity> recommendList = this.carsBusiness.recommend();
		List<CarsEntity> specialList = this.carsBusiness.special();
		this.map.put("recommendList", recommendList);
		this.map.put("specialList", specialList);
		return SUCCESS;
	}

	/**
	 * 商品部分结束 订单部分开始
	 */

	// 订单

	public String checkOut() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		String userid = (String) session.get("userid");
		this.orders.setAddtime(VeDate.getStringDateShort());
		this.orders.setUsersid(userid);
		this.orders.setStatus("已预订");
		long days = Math.abs(VeDate.getDays(this.orders.getThestart(), this.orders.getTheend()));
		if(days ==0){
			days = 1;
		}
		CarsEntity car = this.carsBusiness.checkId(this.orders.getCarsid());
		String money = ""+(Double.parseDouble(car.getPrice())*days);
		this.orders.setMoney(money);
		this.ordersBusiness.save(this.orders);
		this.name = null;
		this.cond = null;
		return SUCCESS;
	}

	public String preOrder() {
		this.front();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		this.cond = VeDate.getStringDatex();
		return SUCCESS;
	}

	public String showOrders() {
		this.front();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		String userid = (String) session.get("userid");
		List<OrdersEntity> orderList = this.ordersBusiness.checkUsersid(userid);
		this.map.put("orderList", orderList);
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public int getPageCur() {
		return pageCur;
	}

	public void setPageCur(int pageCur) {
		this.pageCur = pageCur;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public ArticleBusiness getArticleBusiness() {
		return articleBusiness;
	}

	public void setArticleBusiness(ArticleBusiness articleBusiness) {
		this.articleBusiness = articleBusiness;
	}

	public CateBusiness getCateBusiness() {
		return cateBusiness;
	}

	public void setCateBusiness(CateBusiness cateBusiness) {
		this.cateBusiness = cateBusiness;
	}

	public OrdersBusiness getOrdersBusiness() {
		return ordersBusiness;
	}

	public void setOrdersBusiness(OrdersBusiness ordersBusiness) {
		this.ordersBusiness = ordersBusiness;
	}

	public TopicBusiness getTopicBusiness() {
		return topicBusiness;
	}

	public void setTopicBusiness(TopicBusiness topicBusiness) {
		this.topicBusiness = topicBusiness;
	}

	public UsersBusiness getUsersBusiness() {
		return usersBusiness;
	}

	public void setUsersBusiness(UsersBusiness usersBusiness) {
		this.usersBusiness = usersBusiness;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public UsersEntity getUsers() {
		return users;
	}

	public void setUsers(UsersEntity users) {
		this.users = users;
	}

	public TopicEntity getTopic() {
		return topic;
	}

	public void setTopic(TopicEntity topic) {
		this.topic = topic;
	}

	public OrdersEntity getOrders() {
		return orders;
	}

	public void setOrders(OrdersEntity orders) {
		this.orders = orders;
	}

	public BrandBusiness getBrandBusiness() {
		return brandBusiness;
	}

	public void setBrandBusiness(BrandBusiness brandBusiness) {
		this.brandBusiness = brandBusiness;
	}

	public CarsBusiness getCarsBusiness() {
		return carsBusiness;
	}

	public void setCarsBusiness(CarsBusiness carsBusiness) {
		this.carsBusiness = carsBusiness;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 订单部分结束
	 */

	/**
	 * 还车 开始
	 */
	public String applyReturn() {
		this.front();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("userid") == null) {
			return "loginerror";
		}
		List<OrdersEntity> list = this.ordersBusiness.checkByLikeOrdercode(id);
		if(list != null && list.size() > 0) {
			OrdersEntity order = list.get(0);
			order.setStatus("申请还车");
			this.ordersBusiness.update(order);
		}
		
		String userid = (String) session.get("userid");
		List<OrdersEntity> orderList = this.ordersBusiness.checkUsersid(userid);
		this.map.put("orderList", orderList);
		return SUCCESS;
	}
	/**
	 * 还车结束
	 */
}