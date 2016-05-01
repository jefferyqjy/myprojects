<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Bookhj"%>
<%@page import="com.cz.common.CommDAO"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.BookhjDAO"%>
 
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
		BookhjDAO bookhjdao = new BookhjDAO();
		List<Map<String,Object>> nlist = bookhjdao.statBookhjCount();
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
			<tr align="center">
				<td colspan="8"
					background="/libmanage/admin/commfiles/images/bg.gif"
					bgcolor="#FFFFFF" class="STYLE3">
					图书借阅排行
				</td>
			</tr>
			<tr align="center">
				<td class="itemtitle">
					书名
				</td>
				<td class="itemtitle">
					借阅量
				</td>
				<%
					for (Map<String,Object> m : nlist) {
				%>
			</tr>

			<tr align="center">
				<td align="center">
					<!--Books b = (Books) cdao.findById(m.get("bookname").toString(), "Books");-->
					<%=m.get("bookname")%>
				</td>
				<td align="center">
					<%=m.get("num")%>
				</td>

			</tr>
			<%
				}
			%>


		</table>
	</body>
</html>
