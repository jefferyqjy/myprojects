<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.dao.SreaderDAO"%>
<%@page import="com.cz.dao.BookhjDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.entity.Bookhj"%>
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
		SysprosDAO sysprosdao = new SysprosDAO();
		String sql = "select * from syspros where infoa = '出版社'";
		List<Syspros> cbslist = sysprosdao.findBySql(sql);
		String uname = request.getParameter("uname") == null ? "" : request.getParameter("uname");
		String tname = request.getParameter("tname") == null ? "" : request.getParameter("tname");
		String did = request.getParameter("did") == null ? "" : request.getParameter("did");

		if (!did.equals("")) {
			SreaderDAO sreaderdao = new SreaderDAO();
			sreaderdao.deleteById(Integer.valueOf(did));
		}

		String url = "sreaderck.jsp?1=1";
		sql = "select * from sreader where 1=1 ";

		if (!uname.equals("")) {
			url += "&uname=" + uname;
			sql += " and uname like '%" + uname + "%' ";
		}

		if (!tname.equals("")) {
			url += "&tname=" + tname;
			sql += " and tname like '%" + tname + "%' ";
		}

		HashMap m = new HashMap();
		m.put("uname", uname);
		m.put("tname", tname);

		sql += " order by id desc";
		System.out.println(sql);
		//PageManager pageManager = PageManager.getPage(url, 5, request);
		//pageManager.doList(sql);
		//PageManager bean = (PageManager) request.getAttribute("page");
		SreaderDAO sreaderdao = new SreaderDAO();
		List<Sreader> nlist = sreaderdao.findBySql(sql);
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<form name="f1" method="post" action="/libmanage/admin/sreaderck.jsp">
			<table id="mainbody" border="0" width="100%" cellspacing="1"
				class="tableform">
				<tr>
					<td width=10% height="31" align="center" style="font-size: 12px">
						用户名&nbsp;:
					</td>
					<td width=14% style="font-size: 12px" align="left">
						<input name="uname" value="<%=uname%>" type="text">
					</td>


					<td width=10% align="center" style="font-size: 12px">
						姓名 :
					</td>
					<td width="10%" style="font-size: 12px" align="left">
						<input value="<%=tname%>" name="tname" type="text">
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
					<td colspan="10"
						background="/libmanage/admin/commfiles/images/bg.gif"
						bgcolor="#FFFFFF" class="STYLE3">
						信息列表
					</td>
				</tr>
				<tr align="center">
					<td class="itemtitle">
						用户名
					</td>
					<td class="itemtitle">
						密码
					</td>
					<td class="itemtitle">
						姓名
					</td>
					<td class="itemtitle">
						学历
					</td>
					<td class="itemtitle">
						职业
					</td>
					<td class="itemtitle">
						可借数量
					</td>
					<td class="itemtitle">
						已借数量
					</td>
					<td class="itemtitle">
						电话
					</td>
					<td class="itemtitle">
						email
					</td>

				</tr>

				<%
					for (Sreader map : nlist) {
						BookhjDAO bookhjdao = new BookhjDAO();
						sql = "select * from bookhj where readername = '" + map.getUname() + "' and (hbtime is null or hbtime = '')" ;
						List<Bookhj> bookhjlist = bookhjdao.findBySql(sql);
						int yj = 0;
						if(bookhjlist != null && bookhjlist.size() > 0) {
							yj = bookhjlist.size();
						} 
				%>
				<tr align="center">
					<td align="center">
						<%=map.getUname().equals("") ? "&nbsp;" : map.getUname()%>
					</td>
					<td align="center">
						<%=map.getUpass().equals("") ? "&nbsp;" : map.getUpass()%>
					</td>
					<td align="center">
						<%=map.getTname().equals("") ? "&nbsp;" : map.getTname()%>
					</td>
					<td align="center">
						<%=map.getXueli().equals("") ? "&nbsp;" : map.getXueli()%>
					</td>
					<td align="center">
						<%=map.getZiye().equals("") ? "&nbsp;" : map.getZiye()%>
					</td>
					<td align="center">
						<%=map.getKjnum().equals("") ? "&nbsp;" : map.getKjnum()%>
					</td>
					<td align="center">
						<%=yj%>
					</td>
					<td align="center">
						<%=map.getTel().equals("") ? "&nbsp;" : map.getTel()%>
					</td>
					<td align="center">
						<%=map.getEmail().equals("") ? "&nbsp;" : map.getEmail()%>
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
<%=Info.tform(m)%>