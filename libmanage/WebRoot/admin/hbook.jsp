<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.dao.BookhjDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
<%@page import="com.cz.dao.SreaderDAO"%>
<%@page import="com.cz.entity.Syspros"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Sreader"%>
<%@page import="com.cz.entity.Bookhj"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" />
		<script type="text/javascript" src="/libmanage/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="/libmanage/admin/commfiles/js/ajax.js"></script>
		<script type="text/javascript">
		<%
		boolean success = request.getAttribute("suc") != null;
		System.out.println(success);
		if (success) {
		%>
			alert("还书成功！");
		<%
		}
		%>			
		</script>
	</head>
	<%
		String id = request.getParameter("id");
		BookhjDAO dao = new BookhjDAO();
		Bookhj data = dao.findById(Integer.valueOf(id.trim()));
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("bei", data.getBei());
		m.put("bookname", data.getBookname());
		m.put("htime", data.getHtime());
		m.put("jtime", data.getJtime());
		m.put("readername", data.getReadername());
		m.put("yjin", data.getYjin());
		
		BooksDAO bdao = new BooksDAO();
		List<Books> lblist = bdao.findAll();
		SreaderDAO sdao = new SreaderDAO();
		List<Sreader> cbslist = sdao.findAll();
	%>
	<body>
		<form name="f1" method="post" action="${pageContext.request.contextPath }/bookhj?operate=hbook&id=<%=request.getParameter("id")%>">
			<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="mytab" id="table1">
				<tr align="center" style="display: none">
					<td colspan="3"
						background="/libmanage/admin/commfiles/images/bg.gif"
						bgcolor="#FFFFFF" class="STYLE3">
					</td>
				</tr>
				<tr align="center">
					<td width="25%" height="25" align="center">
						借出时间
					</td>
					<td width="75%" height="25" align="left">
						<span class=""> <%=data.getJtime()%></span>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						还书时间
					</td>
					<td height="25" align="left">
						<span class=""> <%=data.getHtime()%> </span>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						书名
					</td>
					<td height="25" align="left"><%=data.getBookname()%>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						读者
					</td>
					<td height="25" align="left">
						<%=data.getReadername()%>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						押金
					</td>
					<td height="25" colspan="2" align="left">
						<label>
							<span class=""> <%=data.getYjin()%> </span>
						</label>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						相关说明
					</td>
					<td height="25" colspan="2" align="left">
						<span class=""> <%=data.getBei()%> </span>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						实际还书时间
					</td>
					<td height="25" colspan="2" align="left">
						<span class=""> <input name="hbtime" type="text"
								id="hbtime" onclick="WdatePicker();" value="" size="12">
						</span>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						押金扣除
					</td>
					<td height="25" colspan="2" align="left">
						<span class=""> <input name="hbkou" type="text" id="hbkou"
								value="0" size="12"> </span>
					</td>
				</tr>
				<tr align="center">
					<td height="25" align="center">
						还书说明
					</td>
					<td height="25" colspan="2" align="left">
						<span class=""> <input name="hbbei" type="text" id="hbbei"
								value="" size="50"> </span>
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
</html>