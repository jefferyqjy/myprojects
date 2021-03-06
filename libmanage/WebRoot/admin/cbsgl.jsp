<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Syspros"%>
<%@page import="com.cz.entity.Books"%>
 
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
		String key = request.getParameter("key") == null ? "" : request.getParameter("key");
		String did = request.getParameter("did") == null ? "" : request.getParameter("did");

		if (!did.equals("")) {
			SysprosDAO sysprosdao = new SysprosDAO();
			Syspros syspros = sysprosdao.findById(Integer.valueOf(did));
			if(syspros != null && "出版社".equals(syspros.getInfoa())) {
				BooksDAO booksdao = new BooksDAO();
				String sql = "select * from books where cbs = '" + syspros.getProname() + "'";
				List<Books> bookslist = booksdao.findBySql(sql);
				if(bookslist != null && bookslist.size() > 0) {
					request.setAttribute("error", "");
				} else {
					sysprosdao = new SysprosDAO();
					sysprosdao.deleteById(Integer.valueOf(did));
				}
			} else {
				sysprosdao = new SysprosDAO();
				sysprosdao.deleteById(Integer.valueOf(did));				
			}
		}

		String url = "cbsgl.jsp?1=1";
		String sql = "select * from syspros where infoa='出版社' ";

		if (!key.equals("")) {
			sql += " and proname like '%" + key + "%' ";
		}

		sql += " order by id desc";
		System.out.println(sql);
		//PageManager pageManager = PageManager.getPage(url, 100, request);
		//pageManager.doList(sql);
		//PageManager bean = (PageManager) request.getAttribute("page");
		SysprosDAO sysprosdao = new SysprosDAO();
		List<Syspros> nlist = sysprosdao.findBySql(sql);
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<form name="f1" method="post" action="/libmanage/admin/cbsgl.jsp">
			<table id="mainbody" border="0" width="100%" cellspacing="1"
				class="tableform">
				<tr>
					<td height="31" align="left" style="font-size: 12px">
						&nbsp;&nbsp;相关信息&nbsp; :&nbsp;
						<input name="key" type="text" value="<%=key%>" size="35">
						&nbsp;
						<input type="submit" class="btn3_mouseup" value="查询">
						&nbsp;
						<input type="button" onclick="add();" class="btn3_mouseup"
							value="添加">
					</td>
				</tr>
			</table>


			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="mytab" id="table1">
				<tr align="center">
					<td colspan="7"
						background="/libmanage/admin/commfiles/images/bg.gif"
						bgcolor="#FFFFFF" class="STYLE3">
						信息列表
					</td>
				</tr>
				<tr align="center">
					<td class="itemtitle" width="37%">
						出版社名称
						<br>
					</td>
					<td class="itemtitle" width="13%">
						操作
					</td>
					<td class="itemtitle" width="37%">
						出版社名称
					</td>
					<td class="itemtitle" width="13%">
						操作
					</td>

				</tr>
				<tr align="center">
					<%
						int i = 0;
						for (Syspros data : nlist) {
							i++;
					%>
							<td align="center">
								<%=data.getProname().equals("") ? "&nbsp;" : data.getProname()%>
							</td>
							<td align="center">
								<a href="javascript:update('<%=data.getId()%>')">修改</a>
								&nbsp;|&nbsp;
								<a href="/libmanage/admin/cbsgl.jsp?did=<%=data.getId()%>">删除</a>
							</td>
					<%
							if (i % 2 == 0)
								out.print("</tr><tr align='center'>");
					%>
					<%
						}
					%>

				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript">
	function update(no)  {
		pop('/libmanage/admin/updatepros.jsp?id='+no,'修改出版社',300,84);
	}

	function add() {
		pop('/libmanage/admin/addpros.jsp?type=1','添加出版社',300,84);
	}
	</script>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<script type="text/javascript">
	alert("该出版社下已有图书信息，请先删除该出版社下的图书！");
	</script>
	<%
		}
	%>
</html>
