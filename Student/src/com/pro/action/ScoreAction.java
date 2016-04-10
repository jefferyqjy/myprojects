package com.pro.action;
import com.sys.common.util.Const;
import com.sys.common.util.DateUtil;
import com.sys.common.util.UUIDGenerator;
import com.opensymphony.xwork2.ActionContext;
import com.pro.entity.Clazz;
import com.pro.entity.Course;
import com.pro.entity.Score;
import com.pro.entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.TreeMap;

import com.sys.common.util.CommonUtil;
import com.sys.plugin.doc.PDFWriteUtil;
import com.sys.plugin.jschartrpt.ReportUtil;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;
import com.sys.web.fenye.util.QueryConditionSQL;
import com.pro.manager.ClazzManager;
import com.pro.manager.CourseManager;
import com.pro.manager.ScoreManager;
import com.pro.manager.StudentManager;
@Controller
public class ScoreAction {
	@Resource
	private ScoreManager scoreManager;
	@Resource
	private StudentManager studentManager;
	@Resource
	private ClazzManager clazzManager;
	@Resource
	private CourseManager courseManager;
	private int id;
	private String name;
	private double score;
	private String stuno;
	private String clazzname;
	private int year;
	private String type;
	private String ismdy;
	private String indate;
	private String lastdate;
	private String hasapply;
	private String school;
	private int number;
	private String status;
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getHasapply() {
		return hasapply;
	}
	public void setHasapply(String hasapply) {
		this.hasapply = hasapply;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getClazzname() {
		return clazzname;
	}
	public void setClazzname(String clazzname) {
		this.clazzname = clazzname;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsmdy() {
		return ismdy;
	}
	public void setIsmdy(String ismdy) {
		this.ismdy = ismdy;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		String hql = "from Score where year="+this.year+" and type='"+this.type+"' and stuno='"+this.stuno+"' and name='"+this.name+"'";
		List<Score> ls = this.scoreManager.queryByHql(hql);
		if(!CommonUtil.isListEmpty(ls)) {
			return CommonUtil.genActionError("该学号在该年度该学期的该科目已经添加过成绩了");
		}
		Student stu = this.studentManager.querySingleRecordViaKey("stuid", this.stuno);
		this.clazzname = stu.getClazzname();
		Clazz cls = this.clazzManager.querySingleRecordViaKey("name", this.clazzname);
		Score obj = new Score();
		obj.setClazzname(this.clazzname);
		obj.setHasapply("N");
		obj.setIndate(DateUtil.convDate2String(new Date()));
		obj.setIsmdy("N");
		obj.setLastdate(DateUtil.convDate2String(new Date()));
		obj.setName(this.name);
		obj.setScore(this.score);
		obj.setStuno(this.stuno);
		obj.setType(this.type);
		obj.setYear(this.year);
		Course course = this.courseManager.querySingleRecordViaKey("name", this.name);
		int pass = course.getPass();
		if(this.score < pass) {
			obj.setNumber(0);
			obj.setStatus("不及格");
		} else {
			obj.setNumber(course.getNumber());
			obj.setStatus("及格");
		}
		obj.setMandatory(course.getMandatory());
		obj.setSchool(cls.getSchool());
		try {
			this.scoreManager.insert(obj);
		} catch(Exception e) {
			ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, e.getMessage());
			return ActionSupport.ERROR;
		}
		return ActionSupport.SUCCESS;
	}

	public String del() {
		this.scoreManager.deleteViaId(this.id);
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		for(int i=0;i<len;i++) {
			this.scoreManager.deleteViaId(Integer.parseInt(idList[i]));
		}
		return  Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String update() {
		Score pro = this.scoreManager.queryById(this.id);
		pro.setScore(this.score);
		pro.setIsmdy("N");
		pro.setLastdate(DateUtil.convDate2String(new Date()));
		Course course = this.courseManager.querySingleRecordViaKey("name", pro.getName());
		int pass = course.getPass();
		if(this.score < pass) {
			pro.setNumber(0);
			pro.setStatus("不及格");
		} else {
			pro.setNumber(course.getNumber());
			pro.setStatus("及格");
		}
		this.scoreManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}
	
	public String update2() {
		Score pro = this.scoreManager.queryById(this.id);
		pro.setHasapply("Y");
		this.scoreManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}
	
	public String update4() {
		Score pro = this.scoreManager.queryById(this.id);
		pro.setHasapply("N");
		pro.setIsmdy("N");
		this.scoreManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}
	
	public String update3() {
		Score pro = this.scoreManager.queryById(this.id);
		pro.setHasapply("N");
		pro.setIsmdy("Y");
		this.scoreManager.update(pro);
		return Const.ACTION_RETURN_SUCC_CLOSE;
	}

	public String modify() {
		Score pro = this.scoreManager.queryById(this.id);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, pro);
		return Const.ACTION_RETURN_QUERY;
	}

	public String query() {
		Score pro = new Score();
		pro.setStuno(this.stuno);
		pro.setClazzname(this.clazzname);
		pro.setYear(this.year);
		pro.setType(this.type);
		pro.setName(this.name);
		pro.setSchool(this.school);
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
		Page<Score> page = this.scoreManager.getRecords(condition);
		List<Score> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query2() {
		if(CommonUtil.isEmpty(this.clazzname)) {
			return CommonUtil.genActionError("班级不能为空");
		}
		if(CommonUtil.isEmpty(this.name)) {
			return CommonUtil.genActionError("科目不能为空");
		}
		String hql = "select count(*) from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' and score >= 90";
		List<Object[]> ls = this.scoreManager.queryByHql(hql);
		TreeMap map = new TreeMap();
		
		map.put("大于90", ((Long[])ls.toArray(new Long[0]))[0]);
		hql = "select count(*) from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' and score between 80 and 89";
		ls = this.scoreManager.queryByHql(hql);
		map.put("80-90", ((Long[])ls.toArray(new Long[0]))[0]);
		hql = "select count(*) from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' and score between 70 and 79";
		ls = this.scoreManager.queryByHql(hql);
		map.put("70-80", ((Long[])ls.toArray(new Long[0]))[0]);
		hql = "select count(*) from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' and score between 60 and 69";
		ls = this.scoreManager.queryByHql(hql);
		map.put("60-70", ((Long[])ls.toArray(new Long[0]))[0]);
		hql = "select count(*) from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' and score < 60";
		ls = this.scoreManager.queryByHql(hql);
		map.put("小于60", ((Long[])ls.toArray(new Long[0]))[0]);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, ReportUtil.generateBarLineReport(map, "A_div", ReportUtil.TYPE_PIE));
		ActionContext.getContext().put("result2", ReportUtil.generateBarLineReport(map, "B_div", ReportUtil.TYPE_BAR));
		ActionContext.getContext().put("result3", ReportUtil.generateBarLineReport(map, "C_div", ReportUtil.TYPE_LINE));
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query3() {
		Score pro = new Score();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		pro.setStuno(userid);
		pro.setYear(this.year);
		pro.setType(this.type);
		pro.setName(this.name);
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
		Page<Score> page = this.scoreManager.getRecords(condition);
		List<Score> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query4() {
		Score pro = new Score();
		HttpServletRequest request = ServletActionContext.getRequest();
		String orderType = request.getParameter("orderType");
		if(CommonUtil.isEmpty(this.clazzname)) {
			return CommonUtil.genActionError("班级不能为空");
		}
		if(CommonUtil.isEmpty(this.name)) {
			return CommonUtil.genActionError("科目不能为空");
		}
		String hql = "from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' order by score " + orderType;
		List<Score> score = this.scoreManager.queryByHql(hql);
		request.setAttribute(Const.ACTION_PUT_RESULT, score);
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query5() {
		if(CommonUtil.isEmpty(this.clazzname)) {
			return CommonUtil.genActionError("班级不能为空");
		}
		if(CommonUtil.isEmpty(this.name)) {
			return CommonUtil.genActionError("科目不能为空");
		}
		String hql = "from Score where year="+this.year+" and type='"+this.type+"' and clazzname='"+this.clazzname+"' and name='"+this.name+"' and score < "+this.score;
		List<Score> score = this.scoreManager.queryByHql(hql);
		String filePath = CommonUtil.getAttachPath(ServletActionContext.getRequest());
		String fileName = UUIDGenerator.genFileName()+".pdf";
		PDFWriteUtil<Score> util = PDFWriteUtil.getInstance(filePath, fileName);
		String[] titles = new String[]{"学号","课程","年份","学期","班级","分数"};
		util.setTableTitle(titles);
		util.setTableContent(score, new String[]{"getStuno","getName","getYear","getType","getClazzname","getScore"});
		util.genPdf();
		ActionContext.getContext().put("fileName", fileName);
		return Const.ACTION_RETURN_QUERY;
	}
	
	public String query6() {
		String hql = "from Score where hasapply = 'Y' and ismdy = 'N'";
		List<Score> lsscore = this.scoreManager.queryByHql(hql);
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, lsscore);
		return Const.ACTION_RETURN_QUERY;
	}
	
	//查询出不及格科目
	public String query7() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		Score pro = new Score();
		pro.setStuno(userid);
		pro.setYear(this.year);
		pro.setType(this.type);
		pro.setName(this.name);
		pro.setStatus("不及格");
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
		Page<Score> page = this.scoreManager.getRecords(condition);
		List<Score> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	//查询出不及格科目
	public String query77() {
		Score pro = new Score();
		pro.setStuno(this.stuno);
		pro.setYear(this.year);
		pro.setType(this.type);
		pro.setName(this.name);
		pro.setStatus("不及格");
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
		Page<Score> page = this.scoreManager.getRecords(condition);
		List<Score> resultList = page.getList();
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, resultList);
		ActionContext.getContext().put(Const.ACTION_PUT_PAGE_INFO,page.getNavigation());
		ActionContext.getContext().put("curPage", page.getCurrentPage());
		return Const.ACTION_RETURN_QUERY;
	}
	
	//获得学分总数
	public String query8(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		String hql = "select SUM(number) from Score where stuno='"+userid+"'";
		List<Long> list = this.scoreManager.queryByHql(hql);
		if(!CommonUtil.isListEmpty(list)) {
			ActionContext.getContext().put(Const.ACTION_PUT_RESULT, list.get(0)); 
		}
		return Const.ACTION_RETURN_QUERY;
	}
	
	//获得学分总数
	public String query88(){
		String hql = "select SUM(number) from Score where stuno='"+this.stuno+"'";
		List<Long> list = this.scoreManager.queryByHql(hql);
		if(!CommonUtil.isListEmpty(list)) {
			ActionContext.getContext().put(Const.ACTION_PUT_RESULT, list.get(0)); 
		}
		return Const.ACTION_RETURN_QUERY;
	}
	
	//预警信息
	public String query9(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userid = (String)session.getAttribute(Const.ACTION_PUT_SESSION_USRE_NAME);
		String hql = "select SUM(number) from Score where stuno='"+userid+"'";
		List<Long> list = this.scoreManager.queryByHql(hql);
		long number = 0;
		if(!CommonUtil.isListEmpty(list)) {
			number = list.get(0);
		}
		hql = "select COUNT(*) from Score where stuno='"+userid+"' and status='不及格' and mandatory='是'";
		long unpass = 0;
		list = this.scoreManager.queryByHql(hql);
		StringBuilder sb = new StringBuilder();
		if(!CommonUtil.isListEmpty(list)) {
			unpass = list.get(0);
		}
		if(number <10 || unpass >= 8) {
			sb.append("<font color='red'>");
			sb.append("<h2>红色预警</h2><br/>");
			sb.append("获得学分：").append(number).append("<br/>");
			sb.append("不及格数目: ").append(unpass).append("<br/>");
			sb.append("</font>");
		} else
		if(number <12 || unpass >= 5) {
			sb.append("<font color='orange'>");
			sb.append("<h2>橙色预警</h2><br/>");
			sb.append("获得学分：").append(number).append("<br/>");
			sb.append("不及格数目: ").append(unpass).append("<br/>");
			sb.append("</font>");
		} else
		if(number <15 || unpass >= 3) {
			sb.append("<font color='yellow'>");
			sb.append("<h2>黄色预警</h2><br/>");
			sb.append("获得学分：").append(number).append("<br/>");
			sb.append("不及格数目: ").append(unpass).append("<br/>");
			sb.append("</font>");
		} else {
			sb.append("暂无预警信息");
		}
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, sb.toString()); 
		return Const.ACTION_RETURN_QUERY;
	}
	
	//预警信息
	public String query99(){
		String hql = "select SUM(number) from Score where stuno='"+this.stuno+"'";
		List<Long> list = this.scoreManager.queryByHql(hql);
		long number = 0;
		if(!CommonUtil.isListEmpty(list)) {
			number = list.get(0);
		}
		hql = "select COUNT(*) from Score where stuno='"+this.stuno+"' and status='不及格' and mandatory='是'";
		long unpass = 0;
		list = this.scoreManager.queryByHql(hql);
		StringBuilder sb = new StringBuilder();
		if(!CommonUtil.isListEmpty(list)) {
			unpass = list.get(0);
		}
		if(number <10 || unpass >= 8) {
			sb.append("<font color='red'>");
			sb.append("<h2>红色预警</h2><br/>");
			sb.append("获得学分：").append(number).append("<br/>");
			sb.append("不及格数目: ").append(unpass).append("<br/>");
			sb.append("</font>");
		} else
		if(number <12 || unpass >= 5) {
			sb.append("<font color='orange'>");
			sb.append("<h2>橙色预警</h2><br/>");
			sb.append("获得学分：").append(number).append("<br/>");
			sb.append("不及格数目: ").append(unpass).append("<br/>");
			sb.append("</font>");
		} else
		if(number <15 || unpass >= 3) {
			sb.append("<font color='yellow'>");
			sb.append("<h2>黄色预警</h2><br/>");
			sb.append("获得学分：").append(number).append("<br/>");
			sb.append("不及格数目: ").append(unpass).append("<br/>");
			sb.append("</font>");
		} else {
			sb.append("暂无预警信息");
		}
		ActionContext.getContext().put(Const.ACTION_PUT_RESULT, sb.toString()); 
		return Const.ACTION_RETURN_QUERY;
	}

}