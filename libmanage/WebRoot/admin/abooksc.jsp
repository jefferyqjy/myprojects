<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.dao.SreaderDAO"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Syspros"%>
<%@page import="com.cz.entity.Sreader"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css"
			href="/libmanage/admin/commfiles/css/style.css" />
		<script type="text/javascript" src="/libmanage/js/popup.js"></script>
	</head>

	<%
		String admin = "";
		String utype = "";
		String qid = request.getParameter("qid") == null ? "" : request.getParameter("qid");

		if (!qid.equals("")) {
			Sreader read = (Sreader) request.getSession().getAttribute("reader");
			SreaderDAO sreaderdao = new SreaderDAO();
			read = sreaderdao.findById(read.getId());
			read.setSc(read.getSc().replaceAll("-" + qid + "-", ""));
			sreaderdao = new SreaderDAO();
			sreaderdao.update(read);
		}

		if (session.getAttribute("admin") != null) {
			Sysuser map = (Sysuser) request.getSession().getAttribute("admin");
			admin = "pupdatesysusers.jsp";
			utype = "管理员";
		}

		if (session.getAttribute("reader") != null) {
			Sreader map = (Sreader) request.getSession().getAttribute("reader");
			SreaderDAO sreaderdao = new SreaderDAO();
			map = sreaderdao.findById(map.getId());
			admin = map.getSc() == null ? "" : map.getSc();
			utype = "读者";
		}
		System.out.println(admin);
		admin = admin.replaceAll("--", ",");
		System.out.println(admin);
		admin = admin.replaceAll("-", "");
		if (admin.equals("")) {
			admin += "-1";
		}
	%>
	<%
		SysprosDAO sysprosdao = new SysprosDAO();
		String sql = "select * from syspros where infoa = '出版社'";
		List<Syspros> cbslist = sysprosdao.findBySql(sql);
		String bookname = request.getParameter("bookname") == null ? "" : request.getParameter("bookname");
		String readername = request.getParameter("readername") == null ? "" : request.getParameter("readername");
		String isbn = request.getParameter("isbn") == null ? "" : request.getParameter("isbn");
		String did = request.getParameter("did") == null ? "" : request.getParameter("did");

		if (!did.equals("")) {
			BooksDAO booksdao = new BooksDAO();
			booksdao.deleteById(Integer.valueOf(did.trim()));
		}

		String url = "abooksc.jsp?1=1";
		sql = "select * from books where 1=1 ";

		if (!bookname.equals("")) {
			url += "&bookname=" + bookname;
			sql += " and bookname like '%" + bookname + "%' ";
		}

		if (!readername.equals("")) {
			url += "&readername=" + readername;
			SreaderDAO sreaderdao = new SreaderDAO();
			Sreader read = sreaderdao.findById(Integer.valueOf(readername.trim()));

			admin = read.getSc() == null ? "" : read.getSc();
			System.out.println(admin);
			admin = admin.replaceAll("--", ",");
			System.out.println(admin);
			admin = admin.replaceAll("-", "");
			if (admin.equals("")) {
				admin += "-1";
			}
			sql += " and id in (" + admin + ")";
		}

		if (!isbn.equals("")) {
			url += "&isbn = " + isbn;
			sql += " and isbn like '%" + isbn + "%' ";
		}
		HashMap m = new HashMap();
		m.put("readername", readername);
		if (!isbn.equals("")) {
			url += "&isbn=" + isbn;
			sql += " and isbn like '%" + isbn + "%' ";
		}
		sql += " order by id desc";
		System.out.println(sql);
		//PageManager pageManager = PageManager.getPage(url, 5, request);
		//pageManager.doList(sql);
		//PageManager bean = (PageManager) request.getAttribute("page");
		BooksDAO booksdao = new BooksDAO();
		List<Books> nlist = booksdao.findBySql(sql);
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<form name="f1" method="post" action="/libmanage/admin/abooksc.jsp">
			<table id="mainbody" border="0" width="100%" cellspacing="1"
				class="tableform">
				<tr>
					<td width=10% height="31" align="center" style="font-size: 12px">
						书名&nbsp;:
					</td>
					<td width=14% style="font-size: 12px" align="left">
						<input name="bookname" value="<%=bookname%>" type="text">
					</td>
					<td width=10% height="31" align="center" style="font-size: 12px">
						读者 :
					</td>
					<td width=14% style="font-size: 12px" align="left">

						<select name="readername" id="readername">
							<option value="">
								不限
							</option>
							<%
								SreaderDAO sreaderdao = new SreaderDAO();
								List<Sreader> rlist = sreaderdao.findAll();
								for (Sreader obj : rlist) {
							%>
									<option value="<%=obj.getId()%>"><%=obj.getUname()%></option>
							<%
								}
							%>
						</select>

					</td>
					<td width=10% align="center" style="font-size: 12px">
						ISBN :
					</td>
					<td width="10%" style= "font-size: 12px" align="left">
						<input value="<%=isbn%>" name="isbn" type="text">
					</td>
					<td width=20% style="font-size: 12px" align="right">
						<input type="submit" class="btn3_mouseup" value="查询">
						&nbsp;

					</td>
				</tr>
			</table>


			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="mytab" id="table1">
				<tr align="center">
					<td colspan="8"
						background="/libmanage/admin/commfiles/images/bg.gif"
						bgcolor="#FFFFFF" class="STYLE3">
						信息列表
					</td>
				</tr>
				<tr align="center">
					<td class="itemtitle">
						封面
					</td>
					<td class="itemtitle">
						ISBN
					</td>
					<td class="itemtitle">
						书名
					</td>
					<td class="itemtitle">
						价格
					</td>
					<td class="itemtitle">
						图书类别
					</td>
					<td class="itemtitle">
						出版社
					</td>
					<td class="itemtitle">
						库存
					</td>

					<td class="itemtitle">
						已收藏
					</td>

				</tr>

				<%
					for (Books map : nlist) {
				%>
				<tr align="center">
					<td align="center">
						<img src="/libmanage/upfile/<%=map.getFilename()%>" height="80" />
					</td>
					<td align="center">
						<%=map.getIsbn().equals("") ? "&nbsp;" : map.getIsbn()%>
					</td>
					<td align="center">
						<%=map.getBookname().equals("") ? "&nbsp;" : map
						.getBookname()%>
					</td>
					<td align="center">
						<%=map.getPrice().equals("") ? "&nbsp;" : map
								.getPrice()%>
					</td>
					<td align="center">
						<%=map.getTslb().equals("") ? "&nbsp;" : map.getTslb()%>
					</td>
					<td align="center">
						<%=map.getCbs().equals("") ? "&nbsp;" : map.getCbs()%>
					</td>
					<td align="center">
						<%=map.getKucun().equals("") ? "&nbsp;" : map
								.getKucun()%>
					</td>

					<td align="center">
						<%
							sreaderdao = new SreaderDAO();
							sql = "select * from sreader where 1=1 and sc like '%-" +  map.getId() + "-%'";
							List<Sreader> scl = sreaderdao.findBySql(sql);
							int i = 0;
							for (Sreader s : scl) {
								i++;
						%>
								<%=s.getUname()%>&nbsp;
								<%
								if (i % 3 == 0){
									out.print("<br />");
								}
								%>
						<%
							}
						%>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
	</body>
	<%
	if (request.getAttribute("suc") != null) {
	%>
	<script type="text/javascript">
	alert("添加成功")
	</script>
	<%
		}
	%>
</html>
