<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java"
	import="java.util.Date,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	background-image: url(img/bj.jpg);
	 background-repeat:no-repeat;
}
</style>
</head>
<%
	SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
	String dtime = df.format(new Date());
%>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="110" style="background:#9DFCAF"><table width="100%"
					border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="195" height="110" background="img/logo1.jpg">&nbsp;</td>
						<td width="731"
							style="color:#003399; font-family:仿宋_GB2312;font-style:italic; font-size: 40px"
							align="center">汽车维修管理系统</td>
						<td width="69" valign="top">
						<div align="right"><a href="index.jsp" onclick="return confirm('确定要退出系统吗？')">
						<img src="img/quit.gif" width="69" height="17" /></a>
						</div></td>
						
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="30" background="img/main_31.gif"><table width="100%"
					border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<%
										if(session!=null){
											if ("admin".equals(session.getAttribute("type"))) {
									%>
									<td height="20" valign="bottom">当前登录用户编号：${admin.admID}&nbsp;用户名：${admin.admName}
										&nbsp;用户角色：管理员</td>
									<%
										}
									%>
									<%
										if ("employee".equals(session.getAttribute("type"))) {
									%>
									<td height="20" valign="bottom">当前登录用户编号：${employee.empID}&nbsp;用户名：${employee.empName}
										&nbsp;用户角色：员工</td>
									<%
										}
									%>
									<%
										if ("customer".equals(session.getAttribute("type"))) {
									%>
									<td height="20" valign="bottom">当前登录用户编号：${customer.cusID}&nbsp;用户名：${customer.cusName}
										&nbsp;用户角色：客户</td>
									<%
										}
									%>
									<td width="21"><img src="img/main_15.gif" width="19"
										height="14" /></td>
									<td width="35"><div align="center">
											<a href="javascript:history.go(-1);">后退</a>
										</div></td>
									<td width="21"><img src="img/main_17.gif" width="19"
										height="14" /></td>
									<td width="35"><div align="center">
											<a href="javascript:history.go(+1);">前进</a>
										</div></td>
									<td width="21"><img src="img/main_19.gif" width="19"
										height="14" /></td>
									<td width="35"><div align="center">
											<a href="javascript:history.go(0);">刷新</a>
										</div></td>
									<td width="21"><img src="img/main_21.gif" width="19"
										height="14" /></td>
									<td width="35"><div align="center">
											<a href="#" onclick="return confirm('请联系官方:0771-1234567')">帮助</a>
										</div></td>

									<td align="right" style="color:red; font-family: 仿宋_GB2312">今天是:<%=dtime%></td>
									<%}else{//null  debug %>
									<script>alert("session null")</script>
									<%} %>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
