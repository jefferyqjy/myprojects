package com.pro.action;
import com.sys.admin.entity.SysAdmin;
import com.sys.admin.entity.SysAdminManager;
import com.sys.common.util.Const;
import com.sys.common.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.pro.entity.Clazz;
import com.pro.entity.Student;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Date;
import com.sys.common.util.CommonUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;
import com.pro.manager.ClazzManager;
import com.pro.manager.StudentManager;
@Controller
public class StudentAction {
	@Resource
	private StudentManager studentManager;
	@Resource
	private SysAdminManager sysAdminManager;
	@Resource
	private ClazzManager clazzManager;
 private int id;
	private String idno;
	private String pwd;
	private String name;
	private String gender;
	private String age;
	private String address;
	private String party;
	private String phone;
	private String hasbonous;
	private String hasdebit;
	private String job;
	private String indate;
	private String enddate;
	private String faname1;
	private String faname2;
	private String faname3;
	private String farelation1;
	private String farelation2;
	private String farelation3;
	private String fajob1;
	private String fajob2;
	private String fajob3;
	private String clazzname;
	private String ispayment;
	private String stuid;
	private String isapplymdy;
	public String getIsapplymdy() {
		return isapplymdy;
	}
	public void setIsapplymdy(String isapplymdy) {
		this.isapplymdy = isapplymdy;
	}
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getIspayment() {
		return ispayment;
	}
	public void setIspayment(String ispayment) {
		this.ispayment = ispayment;
	}
	public String getClazzname() {
		return clazzname;
	}
	public void setClazzname(String clazzname) {
		this.clazzname = clazzname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHasbonous() {
		return hasbonous;
	}
	public void setHasbonous(String hasbonous) {
		this.hasbonous = hasbonous;
	}
	public String getHasdebit() {
		return hasdebit;
	}
	public void setHasdebit(String hasdebit) {
		this.hasdebit = hasdebit;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getFaname1() {
		return faname1;
	}
	public void setFaname1(String faname1) {
		this.faname1 = faname1;
	}
	public String getFaname2() {
		return faname2;
	}
	public void setFaname2(String faname2) {
		this.faname2 = faname2;
	}
	public String getFaname3() {
		return faname3;
	}
	public void setFaname3(String faname3) {
		this.faname3 = faname3;
	}
	public String getFarelation1() {
		return farelation1;
	}
	public void setFarelation1(String farelation1) {
		this.farelation1 = farelation1;
	}
	public String getFarelation2() {
		return farelation2;
	}
	public void setFarelation2(String farelation2) {
		this.farelation2 = farelation2;
	}
	public String getFarelation3() {
		return farelation3;
	}
	public void setFarelation3(String farelation3) {
		this.farelation3 = farelation3;
	}
	public String getFajob1() {
		return fajob1;
	}
	public void setFajob1(String fajob1) {
		this.fajob1 = fajob1;
	}
	public String getFajob2() {
		return fajob2;
	}
	public void setFajob2(String fajob2) {
		this.fajob2 = fajob2;
	}
	public String getFajob3() {
		return fajob3;
	}
	public void setFajob3(String fajob3) {
		this.fajob3 = fajob3;
	}


	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		if(this.studentManager.isExist("stuid", this.stuid)) {
			return CommonUtil.genActionError("该学号已经存在，不能重复添加");
		}
		if(this.studentManager.isExist("idno", this.idno)) {
			return CommonUtil.genActionError("该身份证已经存在，不能重复添加");
		}
		Student obj = new Student();
		obj.setAddress(this.address);
		obj.setAge(this.age);
		obj.setClazzname(this.clazzname);
		obj.setEnddate(this.enddate);
		obj.setFajob1(this.fajob1);
		obj.setFajob2(this.fajob2);
		obj.setFajob3(this.fajob3);
		obj.setFaname1(this.faname1);
		obj.setFaname2(this.faname2);
		obj.setFaname3(this.faname3);
		obj.setFarelation1(this.farelation1);
		obj.setFarelation2(this.farelation2);
		obj.setFarelation3(this.farelation3);
		obj.setGender(this.gender);
		obj.setHasbonous(this.hasbonous);
		obj.setHasdebit(this.hasdebit);
		obj.setIdno(this.idno);
		obj.setIndate(this.indate);
		obj.setIspayment(this.ispayment);
		obj.setJob(this.job);
		obj.setName(this.name);
		obj.setParty(this.party);
		obj.setPhone(this.phone);
		obj.setStuid(this.stuid);
		obj.setIsapplymdy("N");
		try {
			SysAdmin adm = new SysAdmin();
			adm.setUsername(this.stuid);
			adm.setPassword(this.stuid);
			adm.setUserType(4);
			adm.setCreateTime(DateUtil.getCurrentDateTime());
			this.sysAdminManager.addUser(adm);
			this.studentManager.insert(obj);
		} catch(Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
			return ActionSupport.ERROR;
		}
		return ActionSupport.SUCCESS;
	}

	public String del() {
		this.studentManager.deleteViaId(this.id);
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.studentManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String update() {
		Student obj = this.studentManager.queryById(this.id);
		obj.setAddress(this.address);
		obj.setAge(this.age);
		obj.setClazzname(this.clazzname);
		obj.setEnddate(this.enddate);
		obj.setFajob1(this.fajob1);
		obj.setFajob2(this.fajob2);
		obj.setFajob3(this.fajob3);
		obj.setFaname1(this.faname1);
		obj.setFaname2(this.faname2);
		obj.setFaname3(this.faname3);
		obj.setFarelation1(this.farelation1);
		obj.setFarelation2(this.farelation2);
		obj.setFarelation3(this.farelation3);
		obj.setGender(this.gender);
		obj.setHasbonous(this.hasbonous);
		obj.setHasdebit(this.hasdebit);
		obj.setIndate(this.indate);
		obj.setIspayment(this.ispayment);
		obj.setJob(this.job);
		obj.setName(this.name);
		obj.setParty(this.party);
		obj.setPhone(this.phone);
		obj.setIsapplymdy("N");
		this.studentManager.update(obj);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String modify() {
		Student pro = this.studentManager.queryById(this.id);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, pro);
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String detail() {
		Student pro = this.studentManager.queryById(this.id);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, pro);
		return Const.ACTION_RETURN_QUERY;
	}

	public String query() {
		Student pro = new Student();
		pro.setStuid(this.stuid);
		pro.setName(this.name);
		pro.setIdno(this.idno);
		condition = new DefaultQueryCondition(pro);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Student> page = this.studentManager.getRecords(condition);
		List<Student> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query2() {
		return this.query();
	}
	
	public String query3() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		Clazz clazz = this.clazzManager.querySingleRecordViaKey("username", userid);
		Student pro = new Student();
		pro.setStuid(this.stuid);
		pro.setName(this.name);
		pro.setIdno(this.idno);
		pro.setClazzname(clazz.getName());
		condition = new DefaultQueryCondition(pro);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Student> page = this.studentManager.getRecords(condition);
		List<Student> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query4() {
		Student pro = new Student();
		pro.setStuid(this.stuid);
		pro.setName(this.name);
		pro.setIdno(this.idno);
		condition = new DefaultQueryCondition(pro);
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");
		String pageSize = request.getParameter("pageSize");
		if (CommonUtil.isNotEmpty(curPage)) {
			condition.setPageIndex(Integer.parseInt(curPage));
		}
		if (CommonUtil.isNotEmpty(pageSize)) {
			condition.setPageSize(Integer.parseInt(pageSize));
		}
		Page<Student> page = this.studentManager.getRecords(condition);
		List<Student> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}

}