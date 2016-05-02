<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.BookhjDAO"%>
<%@page import="com.cz.dao.SreaderDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
<%@page import="com.cz.entity.Syspros"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Sreader"%>
<%@page import="com.cz.entity.Bookhj"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css"
			href="/libmanage/admin/commfiles/css/style.css" />
		<script type="text/javascript"
			src="/libmanage/js/My97DatePicker/WdatePicker.js"></script>
	</head>
	<%
		BookhjDAO bookhjdao = new BookhjDAO();
		String id = request.getParameter("id");
		Bookhj data = bookhjdao.findById(Integer.valueOf(id));
		HashMap m = new HashMap();
		m.put("bei", data.getBei());
		m.put("bookname", data.getBookname());
		m.put("htime", data.getHtime());
		m.put("jtime", data.getJtime());
		m.put("readername", data.getReadername());
		m.put("yjin", data.getYjin());
		
		BooksDAO booksdao = new BooksDAO();
		List<Books> lblist = booksdao.findAll();
		
		SreaderDAO sreaderdao = new SreaderDAO();
		List<Sreader> cbslist = sreaderdao.findAll();
	%>
	<body>
		<form name="f1" method="post" action="${pageContext.request.contextPath }\bookhj?operate=rshbook&id=<%=request.getParameter("id")%>">
			<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="mytab" id="table1">
				<tr align="center" style="display: none">
					<td colspan="3"
						background="/libmanage/admin/commfiles/images/bg.gif"
						bgcolor="#FFFFFF" class="STYLE3">
						<br>
						<br>
						<br>
						<br>
					</td>
				</tr>

				<tr align="center">
					<td height="25" align="center">
						续借至
					</td>
					<td height="25" colspan="2" align="left">
						<span class=""> <input name="htime" type="text" id="htime"
								onclick="WdatePicker();" value="" size="12"> </span>
					</td>
				</tr>



				<tr align="center">
					<td height="29" colspan="3" align="center">
						<label>
							<input type="submit" name="button" id="button" value="提交信息">
						</label>
						&nbsp;&nbsp;
						<input type="reset" name="button2" id="button2" value="重新填写">
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script type="text/javascript" src="/libmanage/admin/commfiles/js/ajax.js"></script>
</html>
<%=Info.sucinfo(request, true)%>
<%=Info.tform(m)%>