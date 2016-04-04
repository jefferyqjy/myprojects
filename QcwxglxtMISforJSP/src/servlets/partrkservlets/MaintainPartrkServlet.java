package servlets.partrkservlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Employee;
import bean.Part;
import bean.Partkc;
import bean.Partrk;
import bean.Partrkmingxi;
import bean.Supplier;
import dao.CategoryDAO;
import dao.EmployeeDAO;
import dao.PartDAO;
import dao.PartkcDAO;
import dao.PartrkDAO;
import dao.SupplierDAO;

/**
 * Servlet implementation class MaintainOrderServlet
 */
@WebServlet(name = "MaintainPartrk", urlPatterns = { "/MaintainPartrk" })
public class MaintainPartrkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintainPartrkServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operatorStr = request.getParameter("operatorStr");
		String p_operatorStr = request.getParameter("p_operatorStr"); // p_operatorStr是从partrkServlet来源，再转到partrkmingxi.jsp页面来

		String message = "";
		Partrk partrk = new Partrk();
		List<Partrkmingxi> partrkmingxis = new ArrayList<Partrkmingxi>();
		HttpSession session = request.getSession();

		if (operatorStr.equals("0")) // 获取供应商列表和员工列表及类别列表
		{
			session.removeAttribute("partrkmingxis");// 编辑某个订单之前，先移除session中原有订单
			session.removeAttribute("partrk");// 编辑某个订单之前，先移除session中原有订单明细
			EmployeeDAO edao = new EmployeeDAO();
			SupplierDAO sdao = new SupplierDAO();
			CategoryDAO gdao = new CategoryDAO();
			List<Employee> employees = new ArrayList<Employee>();
			List<Supplier> suppliers = new ArrayList<Supplier>();
			List<Category> categorys = new ArrayList<Category>();
			try {
				employees = edao.findAll();
				suppliers = sdao.findAll();
				categorys = gdao.findAll();
			} catch (Exception e) {
				message = "获取供应商信息列表和员工信息列表及类别信息列表失败！";
			}

			session.setAttribute("employees", employees);
			session.setAttribute("suppliers", suppliers);
			session.setAttribute("categorys", categorys);
			partrk = new Partrk();
			partrk.setSupID(-1);// 采购单开始时以供应商号为负数表示未选中供应商
			partrk.setEmpID(-1);// 采购单开始时以员工号为负数表示未员工供应商

			partrkmingxis = new ArrayList<Partrkmingxi>();
			session.setAttribute("partrk", partrk);
			session.setAttribute("partrkmingxis", partrkmingxis);
			if (p_operatorStr != null) // 在partrk.jsp页面增加采购单时，"MaintainOrder?p_operatorStr=add&&operatorStr=0"这句话中p_operatorStr是放在request的，所以要取出来放入session.
				if (p_operatorStr.equals("add"))
					
					
					session.setAttribute("p_operatorStr", p_operatorStr);

		} else if (operatorStr.equals("1")) // 根据员工编码和供应商编码获取员工姓名及供应商姓名在页面上显示
		{
			EmployeeDAO edao = new EmployeeDAO();
			SupplierDAO sdao = new SupplierDAO();
			String empName = "";
			String supName = "";
			String empID, supID;
			empID = request.getParameter("empID");
			supID = request.getParameter("supID");
			String rkdate=request.getParameter("rkdate");
			try {
				Employee employee = edao.findById(Integer.parseInt(empID));
				Supplier supplier = sdao.findById(Integer.parseInt(supID));
				empName = employee.getEmpName();
				supName = supplier.getSupName();
			} catch (Exception e) {
				message = "获取供应商信息和员工信息失败！";
			}
			partrk = (Partrk) session.getAttribute("partrk");
			partrk.setSupID(Integer.parseInt(supID));
			partrk.setEmpID(Integer.parseInt(empID));
			partrk.setSupName(supName);
			partrk.setEmpName(empName);
			partrk.setRkdate(rkdate);
			session.setAttribute("partrk", partrk);

		} else if (operatorStr.equals("2")) // 根据类别编号获取零件列表
		{
			List<Part> parts = new ArrayList<Part>();
			PartDAO pdao = new PartDAO();
			try {
				parts = pdao.findByKindID(Integer.parseInt(request.getParameter("kindID")));
			} catch (Exception e) {
				message = "获取零件信息列表失败！";
			}
			session.setAttribute("parts", parts);

		} else if (operatorStr.equals("3")) // 若采购单明细中没有该零件，则根据零件编号获取零件信息，并把该零件信息及采购数量放入采购单明细，计算金额小计及总计。
		{

			partrkmingxis = (List<Partrkmingxi>) session.getAttribute("partrkmingxis");
			String partID = request.getParameter("partID");
			boolean findFl = false;

			if (partrkmingxis != null) {
				for (int i = 0; i < partrkmingxis.size(); i++)
					if (partID.equals(partrkmingxis.get(i).getPartID())) {
						findFl = true;
						message = "该零件已经在采购入库单明细中，不能重复加入！";
						break;
					}
			}

			if (findFl == false) {
				PartDAO pdao = new PartDAO();
				Part part = new Part();
				boolean numFl = false;
				Float partrknum = 0.0f;
				Float partrkprice = 0.0f;
				try {
					partrknum = Float.parseFloat(request.getParameter("partrknum"));
					partrkprice = Float.parseFloat(request.getParameter("partrkprice"));
					numFl = true;
				} catch (NumberFormatException e1) {
					message = "请输入正确的单价或者数量！";
				}
				if (numFl) {
					try {
						part = pdao.findById(request.getParameter("partID"));
						
						Partrkmingxi partrkmingxi1 = new Partrkmingxi();
						// oitem.setpartrkID(partrk.getpartrkID());
						// //新采购单没有partrkID，保存时才给出，所以这里不需要给出partrkID，而对于修改采购单来说，新添加的零件也会没有partrkID。
						partrkmingxi1.setPartID(part.getPartID());
						partrkmingxi1.setPartname(part.getPartname());
						partrkmingxi1.setPartstandard(part.getPartstandard());
						partrkmingxi1.setPartpackaging(part.getPartpackaging());
						partrkmingxi1.setKindName(part.getKindName());
						partrkmingxi1.setPartrkprice(partrkprice);
						partrkmingxi1.setPartrknum(partrknum);
						partrkmingxi1.setPartrktotalmoney(partrknum* partrkprice);

						partrkmingxis.add(partrkmingxi1);
						partrk = (Partrk) session.getAttribute("partrk");
						partrk.setPartrktotal(partrk.getPartrktotal()+partrkmingxi1.getPartrktotalmoney());

						session.setAttribute("partrkmingxis", partrkmingxis);
						session.setAttribute("partrk", partrk);

					} catch (Exception e) {
						e.printStackTrace();

						message = "获取零件信息失败！";
					}
				}

			}

		} else if (operatorStr.equals("4")) // 根据零件编号从采购单列表中删除该零件
		{
			partrkmingxis = (List<Partrkmingxi>) session.getAttribute("partrkmingxis");
			String partID = request.getParameter("partID");
			partrk = (Partrk) session.getAttribute("partrk");
			for (int i = 0; i < partrkmingxis.size(); i++) {
				if (partID.equals(partrkmingxis.get(i).getPartID())) {
					partrk.setPartrktotal(partrk.getPartrktotal()- partrkmingxis.get(i).getPartrktotalmoney());

					partrkmingxis.remove(i);
				}
			}
			// message="成功删除零件!";
			session.setAttribute("partrk", partrk);
			session.setAttribute("partrkmingxis", partrkmingxis);

		} else if (operatorStr.equals("5")) // 添加采购单
		{
			partrk = (Partrk) session.getAttribute("partrk");
			if (partrk.getSupID() == -1 || partrk.getEmpID() == -1)
				message = "请选择供应商或员工！";
			else if (partrk.getPartrktotal() == 0)
				message = "请填写采购入库单明细!";
			else {
				partrkmingxis = (List<Partrkmingxi>) session.getAttribute("partrkmingxis");
				PartrkDAO pdao = new PartrkDAO();
				PartkcDAO partkcdao = new PartkcDAO();
				try {
					pdao.insert(partrk);//添加到入库表
					pdao = new PartrkDAO();
					partrkmingxis = (List<Partrkmingxi>) session.getAttribute("partrkmingxis");
					pdao.insertPartrkmingxi(partrkmingxis);//添加到明细表
					
					
					//接下来添加到库存表
							
					//String partrkID = ((Partrk)request.getSession().getAttribute("partrk")).partrkID;//我把添加的入库编号找了出来
					StringBuffer sbPartID=new StringBuffer();
					for (int i = 0; i < partrkmingxis.size(); i++) {
						sbPartID.append("'"+partrkmingxis.get(i).getPartID()+"',");
					}
					sbPartID.delete(sbPartID.length()-1,sbPartID.length());
					partkcdao = new PartkcDAO();
					List<Partkc> resultList = partkcdao.findBypartByIds(sbPartID.toString());//查询存在的庫存
					String[] nowPartIds=partkcdao.getNowPartIds().split(",");//存在的庫存
					String[] partidArr=sbPartID.toString().replaceAll("'", "").split(",");
					Object[] partIds=compare(nowPartIds, partidArr).toArray();//不存在的
					//两个循环判断相等的PartID 为要修改的Partkc pk，
					for(Partkc pk:resultList){//循环存在的庫存更新数量 partrkmingxis = (List<Partrkmingxi>)
						for(Partrkmingxi pmx:partrkmingxis){
							if(pk.getPartID().equals(pmx.getPartID())){//环判断相等的PartID 为要修改的Partkc pk
								pk.setPartkcnum(pk.getPartkcnum()+pmx.getPartrknum());
								partkcdao.update(pk);
							}
						}
					}
					//和上面一个意思，只是判断的PartID为库存没有的，主要是为了得到相同的PartID数据Partrkmingxi pmx
					for(Partrkmingxi pmx:partrkmingxis){//循环不存在的插入 
						for(Object pid:partIds){
							if(pid.equals(pmx.getPartID())){//主要是为了得到相同的PartID数据Partrkmingxi pmx
								//得到相同的PartID数据给new Partkc();
								Partkc pk=new Partkc();
								pk.setPartID(pmx.getPartID());
								pk.setPartname(pmx.getPartname());
								pk.setPartstandard(pmx.getPartstandard());
								pk.setPartpackaging(pmx.getPartpackaging());
								pk.setPartkcnum(pmx.getPartrknum());	
								partkcdao.insert(pk);
								
							}
						}
					}
					partkcdao.close();
					message = "成功提交采购入库单！";

				} catch (Exception e) {
					message = "提交采购单入库失败！";
				}
			}
		} else if (operatorStr.equals("6")) // 修改采购单
		{

			partrk = (Partrk) session.getAttribute("partrk");
			partrkmingxis = (List<Partrkmingxi>) session.getAttribute("partrkmingxis");
			PartkcDAO partkcdao = new PartkcDAO();
			if (partrk.getPartrktotal() == 0)
				message = "请填写采购单入库明细!";
			else {
				try {
					
					PartrkDAO pdao = new PartrkDAO();
					pdao.update(partrk);
					partrkmingxis.get(0).setPartrkID(partrk.getPartrkID()); // 把采购单号加入第一个零件，若修改零件时删除了原有零件，所加入的新零件都不会有采购单号。保存时需要取第一个零件的采购单号。
					pdao = new PartrkDAO();
					pdao.updatePartrkmingxi(partrkmingxis);
					
					
					StringBuffer sbPartID=new StringBuffer();
					for (int i = 0; i < partrkmingxis.size(); i++) {
						sbPartID.append("'"+partrkmingxis.get(i).getPartID()+"',");
					}
					sbPartID.delete(sbPartID.length()-1,sbPartID.length());
					partkcdao = new PartkcDAO();
					List<Partkc> resultList = partkcdao.findBypartByIds(sbPartID.toString());//查询存在的庫存
					String[] nowPartIds=partkcdao.getNowPartIds().split(",");//存在的庫存
					String[] partidArr=sbPartID.toString().replaceAll("'", "").split(",");
					Object[] partIds=compare(nowPartIds, partidArr).toArray();//不存在的
					//两个循环判断相等的PartID 为要修改的Partkc pk，
					for(Partkc pk:resultList){//循环存在的庫存更新数量 partrkmingxis = (List<Partrkmingxi>)
						for(Partrkmingxi pmx:partrkmingxis){
							if(pk.getPartID().equals(pmx.getPartID())){//环判断相等的PartID 为要修改的Partkc pk
								pk.setPartkcnum(pk.getPartkcnum()+pmx.getPartrknum());
								partkcdao.update(pk);
							}
						}
					}
					//和上面一个意思，只是判断的PartID为库存没有的，主要是为了得到相同的PartID数据Partrkmingxi pmx
					for(Partrkmingxi pmx:partrkmingxis){//循环不存在的插入 
						for(Object pid:partIds){
							if(pid.equals(pmx.getPartID())){//主要是为了得到相同的PartID数据Partrkmingxi pmx
								//得到相同的PartID数据给new Partkc();
								Partkc pk=new Partkc();
								pk.setPartID(pmx.getPartID());
								pk.setPartname(pmx.getPartname());
								pk.setPartstandard(pmx.getPartstandard());
								pk.setPartpackaging(pmx.getPartpackaging());
								pk.setPartkcnum(pmx.getPartrknum());	
								partkcdao.insert(pk);
								
							}
						}
					}
					
					
					
					message = "成功修改采购入库单及明细！";
				} catch (Exception e) {
					e.printStackTrace();
					message = "修改采购入库单及明细失败！";
				}
			}
		} else if (operatorStr.equals("7")) // 删除采购单
		{
			partrk = (Partrk) session.getAttribute("partrk");
			partrkmingxis = (List<Partrkmingxi>) session.getAttribute("partrkmingxis");
			PartrkDAO pdao = new PartrkDAO();
			PartkcDAO partkcdao = new PartkcDAO();
			try {
				pdao.deleteById(partrk.getPartrkID());

				for (int i = 0; i < partrkmingxis.size(); i++) {
					Partrkmingxi partrkmingxi = partrkmingxis.get(i);
					pdao = new PartrkDAO();
					pdao.deletePartrkmingxi(partrkmingxi);
					
				}
				StringBuffer sbPartID=new StringBuffer();
				for (int i1 = 0; i1 < partrkmingxis.size(); i1++) {
					sbPartID.append("'"+partrkmingxis.get(i1).getPartID()+"',");
				}
				sbPartID.delete(sbPartID.length()-1,sbPartID.length());
				partkcdao = new PartkcDAO();
				List<Partkc> resultList = partkcdao.findBypartByIds(sbPartID.toString());//查询存在的库存
				//两个循环判断相等的PartID 为要修改的Partkc pk，
				for(Partkc pk:resultList){//循环存在的庫存更新数量 partrkmingxis = (List<Partrkmingxi>)
					for(Partrkmingxi pmx:partrkmingxis){
						if(pk.getPartID().equals(pmx.getPartID())){//环判断相等的PartID 为要修改的Partkc pk
							pk.setPartkcnum(pk.getPartkcnum()-pmx.getPartrknum());
							partkcdao.update(pk);
						}
					}
				}
				message = "成功删除采购入库单及明细！";
			} catch (Exception e) {
				message = "删除采购入库单及明细失败！";
			}
		}
		// message
		request.setAttribute("message", message);
		getServletConfig().getServletContext().getRequestDispatcher("/partrkmingxi.jsp").forward(request, response);

	}
	public static <T> List<T> compare(T[] t1, T[] t2) {
		List<T> list1 = Arrays.asList(t1);
		List<T> list2 = new ArrayList<T>();
		for (T t : t2) {
			if (!list1.contains(t)) {
				list2.add(t);
			}
		}
		return list2;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
