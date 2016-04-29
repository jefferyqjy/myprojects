package com.pro.action;
import com.base.common.util.Const;
import com.base.common.util.EntityAnnotation;
import com.pro.entity.Student;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.base.sys.entity.Admin;
import com.base.sys.manager.AdminManager;
import com.base.web.tag.BTAGI18N;
import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import javax.annotation.Resource;
import com.base.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.pro.manager.StudentManager;
@Controller
public class StudentAction {
	@Resource
	private StudentManager studentManager;
	
	@Resource
	private AdminManager adminManager;
	
	private int id;
	
	@EntityAnnotation(desc="学号", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String number;
	
	@EntityAnnotation(desc="姓名", isQueryField=true, length=20, needUpdate=false, rule="CHAR_M_20")
	private String name;
	
	@EntityAnnotation(desc="年龄", isQueryField=false, length=20, needUpdate=false, rule="CHAR_M_20")
	private String age;
	
	@EntityAnnotation(desc="性别", isQueryField=false, needUpdate=false,  rule="SELE_M_男;女")
	private String sex;
	
	@EntityAnnotation(desc="学院", isQueryField=false, length=20, needUpdate=true, rule="CHAR_M_20")
	private String collage;
	
	@EntityAnnotation(desc="班级", isQueryField=false, length=20, needUpdate=true, rule="CHAR_M_20")
	private String className;
	
	@EntityAnnotation(desc="编号", isQueryField=false)
	private String examId;
	
	@EntityAnnotation(desc="缴费", isQueryField=false, needUpdate=false,  rule="SELE_M_未缴;已缴")
	private String money;
	
	private String shenhe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCollage() {
		return collage;
	}

	public void setCollage(String collage) {
		this.collage = collage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}



	private DefaultQueryCondition condition;
	private Page page;
	public String add() {
		Student entity = new Student();
		Student temp = this.studentManager.querySingleRecordViaKeys("number",number);
		if(temp != null ){
			ActionContext.getContext().put(Const.Notification.ERROR, "学生学号:"+number+" 已存在！");
			number="";
			name="";
			age="";
			sex="";
			collage="";
			className="";
			return Const.Pages.MAPPING_URL;
		}
		try {
			entity.setName(this.name);
			entity.setNumber(this.number);
			entity.setAge(this.age);
			entity.setSex(this.sex);
			entity.setCollage(this.collage);
			entity.setClassName(this.className);
			entity.setExamId("");
			entity.setMoney(this.money);
			this.studentManager.add(entity);
			//add to login
			Admin admin = new Admin();
			admin.setUsername(this.number);
			admin.setPassword("123456");
			admin.setUsertype("2");
			this.adminManager.addUser(admin);
			String returnMsg = BTAGI18N.getI18NValue("add.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.MAPPING_URL;
		} catch(Exception e) {
			ActionContext.getContext().put(Const.Notification.ERROR, e.getMessage());
			return Const.Pages.MAPPING_URL;
		}
	}

	public String del() {
		Student student = new Student();
		student = this.studentManager.queryById(this.id);
		this.studentManager.deleteViaId(this.id);
		this.adminManager.deleteUser(CommonUtil.genUTF8String(student.getNumber()));
		ActionContext.getContext().put(Const.Notification.SUCCESS, BTAGI18N.getI18NValue("delete.success", "common"));
		return Const.Pages.QUERY_DO;
	}

	public String dels() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids = request.getParameter("ids");
		String[] idList = ids.split(",");
		int len = idList.length;
		Student student = new Student();
		for(int i=0;i<len;i++) {
			student = this.studentManager.queryById(Integer.parseInt(idList[i]));
			this.studentManager.deleteViaId(Integer.parseInt(idList[i]));
			this.adminManager.deleteUser(CommonUtil.genUTF8String(student.getNumber()));
		}
		return Const.Pages.QUERY_DO;
	}
	
	public String update() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String types = request.getParameter("types");
		String number = request.getParameter("number");
		String age = request.getParameter("age");
		String collage = request.getParameter("collage");
		String className = request.getParameter("className");
		String sex = request.getParameter("sex");
		
		Student entity = new Student();
		if ("jiaofei".equals(types)) {
			entity = this.studentManager.querySingleRecordViaKey("number",number);
			entity.setFlag("Y");
			entity.setMoney("已缴");
		} else {
			entity = this.studentManager.queryById(this.id);
			//String selectValue = request.getParameter("selectValue");
			if ("F".equalsIgnoreCase(this.shenhe)) {
				entity.setExamId("");
			} else {
				entity.setExamId(this.examId);
			}
		}
		entity.setAge(age);
		entity.setCollage(collage);
		entity.setSex(sex);
		entity.setClassName(className);
		this.studentManager.update(entity);
		return Const.Pages.QUERY_DO;
	}

	public String edit() {
		Student entity = this.studentManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}
	
	public String editLanguage() {
		Student entity = this.studentManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}
	
	public String updateLanguage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		String examId = request.getParameter("examId");
		String language = request.getParameter("language");
		Student entity = new Student();
		entity = studentManager.queryById(Integer.valueOf(id.trim()));
		entity.setExamId(examId);
		entity.setLanguage(language);
		studentManager.update(entity);
		return "queryLanguage";
	}

	public String detail() {
		Student entity = this.studentManager.queryById(this.id);
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, entity);
		return Const.Pages.MAPPING_URL;
	}

	public String query() {
		Student entity = new Student();
		entity.setName(this.name);
		entity.setNumber(this.number);
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
		Page<Student> page = this.studentManager.getRecords(condition);
		List<Student> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return Const.Pages.MAPPING_URL;
	}
	
	public String queryLanguage() {
		Student entity = new Student();
		entity.setName(this.name);
		entity.setNumber(this.number);
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
		Page<Student> page = this.studentManager.getRecords(condition);
		List<Student> resultList = page.getList();
		ActionContext.getContext().put(Const.Action.PAGE_REUSLT, resultList);
		ActionContext.getContext().put(Const.Action.PAGINATION_INFO,page.getNavigation());
		ActionContext.getContext().put(Page.CURRENT_PAGE, page.getCurrentPage());
		return "queryLanguage";
	}
	
	public String batchInsert () {
		try {
			List<Student> listExcel = this.getAllByExcel("D://Student//student.xls");
	        for (Student stuEntity : listExcel) {
	        	if (isExist(stuEntity.getNumber())) {
	        		Student entity = new Student();
	        		entity = this.studentManager.querySingleRecordViaKey("number",stuEntity.getNumber());
	        		entity.setNumber(stuEntity.getNumber());
	        		entity.setName(stuEntity.getName());
	        		entity.setAge(stuEntity.getAge());
	        		entity.setSex(stuEntity.getSex());
	        		entity.setCollage(stuEntity.getCollage());
	        		entity.setClassName(stuEntity.getClassName());
	        		//entity.setMoney(money)
	        		this.studentManager.update(entity);
	        	} else {
	        		this.studentManager.add(stuEntity);
	        		//add to login
	    			Admin admin = new Admin();
	    			admin.setUsername(stuEntity.getNumber());
	    			admin.setPassword("123456");
	    			admin.setUsertype("2");
	    			this.adminManager.addUser(admin);
	        	}
	        }
	        String returnMsg = BTAGI18N.getI18NValue("batchInsert.success", "common");
			ActionContext.getContext().put(Const.Notification.SUCCESS, returnMsg);
			return Const.Pages.BATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put(Const.Notification.ERROR, "批量导入出错！");
        	return Const.Pages.BATCH_SUCCESS;
		}
	}
	
	public static List<Student> getAllByExcel(String file) throws BiffException, IOException{
        List<Student> list=new ArrayList<Student>();
        Workbook rwb=Workbook.getWorkbook(new File(file));
        Sheet rs=rwb.getSheet("Sheet1");
        int clos=rs.getColumns();
        int rows=rs.getRows();
        //System.out.println(clos+" rows:"+rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
            	Student student = new Student();
            	String number = rs.getCell(j++, i).getContents();
            	String name = rs.getCell(j++, i).getContents();
            	String age = rs.getCell(j++, i).getContents();
            	String sex = rs.getCell(j++, i).getContents();
            	String collage = rs.getCell(j++, i).getContents();
            	String className = rs.getCell(j++, i).getContents();
            	String money = rs.getCell(j++, i).getContents();
                //System.out.println("number:"+number+" name:"+name+" age:"+age+" sex:"+sex+" collage:"+collage+" className:"+className+" money:"+money);
                student.setNumber(number);
                student.setName(name);
                student.setAge(age);
                student.setSex(sex);
                student.setCollage(collage);
                student.setClassName(className);
                student.setMoney(money);
                student.setExamId("");
                list.add(student);
            }
        }    
        return list;
    }
	
	public boolean isExist (String number) {
		Student temp = this.studentManager.querySingleRecordViaKeys("number",number);
		if (temp != null) {
			return true;
		}
		return false;
	}


	public String query2() {
		return this.query();
	}	
	
	public String select() {
		return this.query();
	}}