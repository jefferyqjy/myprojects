<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Bookhj"%>
<%@page import="com.cz.common.CommDAO"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.BookhjDAO"%>
<%@page import="com.cz.dao.SreaderDAO"%>
 
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
		SreaderDAO sreaderdao = new SreaderDAO();
		List<Map<String,Object>> nlist = sreaderdao.statBlacklist();
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
			<tr align="center">
				<td colspan="8"
					background="/libmanage/admin/commfiles/images/bg.gif"
					bgcolor="#FFFFFF" class="STYLE3">
					黑名单
				</td>
			</tr>
			<tr align="center">
				<td class="itemtitle">
					读者
				</td>
				<td class="itemtitle">
					读者联系方式
				</td>
				<td class="itemtitle">
					未还图书
				</td>
				<td class="itemtitle">
					应还日期	
				</td>
				<td class="itemtitle">
					逾期金额	
				</td>
			</tr>

			<%
				for (Map<String,Object> m : nlist) {
			%>
			<tr align="center">
				<td align="center">
					<font color="red"><%=m.get("readername")%></font>
				</td>
				<td align="center">
					<font color="red"><%=m.get("tel")%></font>
				</td>
				<td align="center">
					<%=m.get("bookname")%>
				</td>
				<td align="center">
					<%=m.get("htime")%>
				</td>
				<td align="center">
					<%=m.get("fine")%>
				</td>
			</tr>
			<%
				}
			%>


		</table>
	</body>
</html>
