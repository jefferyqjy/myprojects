package servlets.partssservlets;

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

import bean.*;
import dao.*;

/**
 * Servlet implementation class MaintainOrderServlet
 */
@WebServlet(name = "MaintainPartss", urlPatterns = { "/MaintainPartss" })
public class MaintainPartssServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaintainPartssServlet() {
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
		String p_operatorStr = request.getParameter("p_operatorStr"); // p_operatorStr是从partssServlet来源，再转到partssmingxi.jsp页面来

		String message = "";
		Partss partss = new Partss();
		List<Partssmingxi> partssmingxis = new ArrayList<Partssmingxi>();

		HttpSession session = request.getSession();

		if (operatorStr.equals("0")) // 获取客户列表和员工列表及零件类别列表
		{
			session.removeAttribute("partssmingxis");// 编辑某个订单之前，先移除session中原有订单
			session.removeAttribute("partss");// 编辑某个订单之前，先移除session中原有订单明细
			EmployeeDAO edao = new EmployeeDAO();
			CustomerDAO cdao = new CustomerDAO();
			CategoryDAO gdao = new CategoryDAO();
			List<Employee> employees = new ArrayList<Employee>();
			List<Customer> customers = new ArrayList<Customer>();
			List<Category> categorys = new ArrayList<Category>();
			try {
				employees = edao.findAll();
				customers = cdao.findAll();
				categorys = gdao.findAll();
			} catch (Exception e) {
				message = "获取客户信息列表和员工信息列表及零件类别信息列表失败！";
			}

			session.setAttribute("employees", employees);
			session.setAttribute("customers", customers);
			session.setAttribute("categorys", categorys);
			partss = new Partss();
			partss.setCusID(-1);// 销售单开始时以客户号为负数表示未选中客户
			partss.setEmpID(-1);// 销售单开始时以员工号为负数表示未员工客户

			partssmingxis = new ArrayList<Partssmingxi>();
			session.setAttribute("partss", partss);
			session.setAttribute("partssmingxis", partssmingxis);
			if (p_operatorStr != null) // 在partss.jsp页面增加销售单时，"MaintainOrder?p_operatorStr=add&&operatorStr=0"这句话中p_operatorStr是放在request的，所以要取出来放入session.
				if (p_operatorStr.equals("add"))
					
					
					session.setAttribute("p_operatorStr", p_operatorStr);

		} else if (operatorStr.equals("1")) // 根据员工编码和客户编码获取员工姓名及客户姓名在页面上显示
		{
			EmployeeDAO edao = new EmployeeDAO();
			CustomerDAO cdao = new CustomerDAO();
			String empName = "";
			String cusName = "";
			String empID, cusID;
			empID = request.getParameter("empID");
			cusID = request.getParameter("cusID");
			String ssdate=request.getParameter("ssdate");
			try {
				Employee employee = edao.findById(Integer.parseInt(empID));
				Customer customer = cdao.findById(Integer.parseInt(cusID));
				empName = employee.getEmpName();
				cusName = customer.getCusName();
			} catch (Exception e) {
				message = "获取供应商信息和员工信息失败！";
			}
			partss = (Partss) session.getAttribute("partss");
			partss.setCusID(Integer.parseInt(cusID));
			partss.setEmpID(Integer.parseInt(empID));
			partss.setCusName(cusName);
			partss.setEmpName(empName);
			partss.setSsdate(ssdate);
			session.setAttribute("partss", partss);

		} else if (operatorStr.equals("2")) // 根据零件类别编号获取零件列表
		{
			List<Part> parts = new ArrayList<Part>();
			PartDAO pdao = new PartDAO();
			try {
				parts = pdao.findByKindID(Integer.parseInt(request.getParameter("kindID")));
			} catch (Exception e) {
				message = "获取零件信息列表失败！";
			}
			session.setAttribute("parts", parts);

		} else if (operatorStr.equals("3")) // 若销售单明细中没有该零件，则根据零件编号获取零件信息，并把该零件信息及购买数量放入销售单明细，计算金额小计及总计。
		{

			partssmingxis = (List<Partssmingxi>) session.getAttribute("partssmingxis");
			String partID = request.getParameter("partID");
			boolean findFl = false;

			if (partssmingxis != null) {
				for (int i = 0; i < partssmingxis.size(); i++)
					if (partID.equals(partssmingxis.get(i).getPartID())) {
						findFl = true;
						message = "该零件已经在销售出库单明细中，不能重复加入！";
						break;
					}
			}

			if (findFl == false) {
				PartkcDAO partkcdao = new PartkcDAO();
				PartDAO pdao = new PartDAO();
				Part part = new Part();
				boolean numFl = false;
				Float partssnum = 0.0f;
				Float partssprice = 0.0f;
				try {
					partssnum = Float.parseFloat(request.getParameter("partssnum"));
					partssprice = Float.parseFloat(request.getParameter("partssprice"));
					numFl = true;
				} catch (NumberFormatException e1) {
					message = "请输入正确的单价或者数量！";
				}
				if (numFl) {
					try {
						int currStoreNum = partkcdao.findCurrStoreNumById(request.getParameter("partID"));
						pdao = new PartDAO();
						part = pdao.findById(request.getParameter("partID"));
						
						if(currStoreNum<partssnum){
							message = "当前库存不足，最大可用库存量为【"+currStoreNum+"】";
						}else{
							Partssmingxi partssmingxi1 = new Partssmingxi();
							// oitem.setpartssID(partss.getpartssID());
							// //新销售单没有partssID，保存时才给出，所以这里不需要给出partssID，而对于修改销售单来说，新添加的零件也会没有partssID。
							partssmingxi1.setPartID(part.getPartID());
							partssmingxi1.setPartname(part.getPartname());
							partssmingxi1.setPartstandard(part.getPartstandard());
							partssmingxi1.setPartpackaging(part.getPartpackaging());
							partssmingxi1.setKindName(part.getKindName());
							partssmingxi1.setPartssprice(partssprice);
							partssmingxi1.setPartssnum(partssnum);
							partssmingxi1.setPartsstotalmoney(partssnum* partssprice);

							partssmingxis.add(partssmingxi1);
							partss = (Partss) session.getAttribute("partss");
							partss.setPartsstotal(partss.getPartsstotal()+partssmingxi1.getPartsstotalmoney());

							session.setAttribute("partssmingxis", partssmingxis);
							session.setAttribute("partss", partss);
						}
						

					} catch (Exception e) {
						e.printStackTrace();

						message = "获取零件信息失败！";
					}
				}

			}

		} else if (operatorStr.equals("4")) // 根据零件编号从销售单列表中删除该零件
		{
			partssmingxis = (List<Partssmingxi>) session.getAttribute("partssmingxis");
			String partID = request.getParameter("partID");
			partss = (Partss) session.getAttribute("partss");
			for (int i = 0; i < partssmingxis.size(); i++) {
				if (partID.equals(partssmingxis.get(i).getPartID())) {
					partss.setPartsstotal(partss.getPartsstotal()- partssmingxis.get(i).getPartsstotalmoney());

					partssmingxis.remove(i);
				}
			}
			// message="成功删除零件!";
			session.setAttribute("partss", partss);
			session.setAttribute("partssmingxis", partssmingxis);

		} else if (operatorStr.equals("5")) // 添加销售单
		{
			partss = (Partss) session.getAttribute("partss");
			

			if (partss.getCusID() == -1 || partss.getEmpID() == -1)
				message = "请选择客户或员工！";
			else if (partss.getPartsstotal() == 0)
				message = "请填写销售出库单明细!";
			else {
				partssmingxis = (List<Partssmingxi>) session.getAttribute("partssmingxis");
				PartssDAO pdao = new PartssDAO();
				PartkcDAO partkcdao = new PartkcDAO();
				try {
					pdao.insert(partss);
					pdao = new PartssDAO();
					partssmingxis = (List<Partssmingxi>) session.getAttribute("partssmingxis");
					pdao.insertPartssmingxi(partssmingxis);
					
					StringBuffer sbPartID=new StringBuffer();
					for (int i = 0; i < partssmingxis.size(); i++) {
						sbPartID.append("'"+partssmingxis.get(i).getPartID()+"',");
					}
					sbPartID.delete(sbPartID.length()-1,sbPartID.length());
					partkcdao = new PartkcDAO();
					List<Partkc> resultList = partkcdao.findBypartByIds(sbPartID.toString());//查询存在的库存
					for(Partkc pk:resultList){//循环存在的库存更新數量 partssmingxis = (List<Partssmingxi>)
						for(Partssmingxi psmx:partssmingxis){
							if(pk.getPartID().equals(psmx.getPartID())){//环判断相等的PartID 为要修改的Partkc pk
								pk.setPartkcnum(pk.getPartkcnum()-psmx.getPartssnum());
								partkcdao.update(pk);
							}
						}
					}
					
					message = "成功提交销售出库单！";

				} catch (Exception e) {
					message = "提交销售单出库失败！";
				}
			}
		} else if (operatorStr.equals("6")) // 修改销售单
		{
			partss = (Partss) session.getAttribute("partss");
			partssmingxis = (List<Partssmingxi>) session.getAttribute("partssmingxis");
	
			if (partss.getPartsstotal() == 0)
				message = "请填写销售单出库明细!";
			else {
				try {
					PartssDAO pdao = new PartssDAO();
					pdao.update(partss);
					partssmingxis.get(0).setPartssID(partss.getPartssID()); // 把销售单号加入第一个零件，若修改零件时删除了原有零件，所加入的新零件都不会有销售单号。保存时需要取第一个零件的销售单号。
					pdao = new PartssDAO();
					pdao.updatePartssmingxi(partssmingxis);
					
					StringBuffer sbPartID=new StringBuffer();
					for (int i = 0; i < partssmingxis.size(); i++) {
						sbPartID.append("'"+partssmingxis.get(i).getPartID()+"',");
					}
					sbPartID.delete(sbPartID.length()-1,sbPartID.length());
					PartkcDAO partkcdao = new PartkcDAO();
					List<Partkc> resultList = partkcdao.findBypartByIds(sbPartID.toString());//查询存在的库存
					for(Partkc pk:resultList){//循环存在的库存更新數量 partssmingxis = (List<Partssmingxi>)
						for(Partssmingxi psmx:partssmingxis){
							if(pk.getPartID().equals(psmx.getPartID())){//环判断相等的PartID 为要修改的Partkc pk
								pk.setPartkcnum(pk.getPartkcnum()-psmx.getPartssnum());
								partkcdao.update(pk);
							}
						}
					}
					
					
					
					message = "成功修改销售出库单及明细！";
				} catch (Exception e) {
					message = "修改销售出库单及明细失败！";
				}
			}
		} else if (operatorStr.equals("7")) // 删除销售单
		{
			partss = (Partss) session.getAttribute("partss");
			partssmingxis = (List<Partssmingxi>) session.getAttribute("partssmingxis");
			PartssDAO pdao = new PartssDAO();
			try {
				pdao.deleteById(partss.getPartssID());

				for (int i = 0; i < partssmingxis.size(); i++) {
					Partssmingxi partssmingxi = partssmingxis.get(i);
					pdao = new PartssDAO();
					pdao.deletePartssmingxi(partssmingxi);
				}
				StringBuffer sbPartID=new StringBuffer();
				for (int i = 0; i < partssmingxis.size(); i++) {
					sbPartID.append("'"+partssmingxis.get(i).getPartID()+"',");
				}
				sbPartID.delete(sbPartID.length()-1,sbPartID.length());
				PartkcDAO partkcdao = new PartkcDAO();
				List<Partkc> resultList = partkcdao.findBypartByIds(sbPartID.toString());//查询存在的库存
				for(Partkc pk:resultList){//循环存在的库存更新數量 partssmingxis = (List<Partssmingxi>)
					for(Partssmingxi psmx:partssmingxis){
						if(pk.getPartID().equals(psmx.getPartID())){//环判断相等的PartID 为要修改的Partkc pk
							pk.setPartkcnum(pk.getPartkcnum()+psmx.getPartssnum());
							partkcdao.update(pk);
						}
					}
				}
				
				message = "成功删除销售出库单及明细！";
			} catch (Exception e) {
				message = "删除销售出库单及明细失败！";
			}
		}
		// message
		request.setAttribute("message", message);
		getServletConfig().getServletContext().getRequestDispatcher("/partssmingxi.jsp").forward(request, response);

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
