<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.entity.Syspros"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css"
			href="/libmanage/admin/commfiles/css/style.css" />
	</head>
	<%
		String id = request.getParameter("id");
		SysprosDAO sysprosdao = new SysprosDAO();
		Syspros data = sysprosdao.findById(Integer.valueOf(id));
	%>
	<body>
		<form name="f1" method="post"
			action="${pageContext.request.contextPath}/syspros?operate=updatepros&id=<%=request.getParameter("id")%>">
			<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="mytab" id="table1">
				<tr align="center" style="display: none"></tr>
				<tr align="center">
					<td height="25" align="center">
						<span class=""> 
							<strong>请输入名称</strong> : <br /> 
							<input name="proname" id="proname" value="<%=data.getProname()%>" type="text" size="40" /> 
						</span>
					</td>
				</tr>
				<%
				if("职业".equals(data.getInfoa())) {
				%>
					<tr align="center">
						<td height="25" align="center">
							<span class=""> 
								<strong>请输入可借数量</strong> : <br /> 
								<input name="infob" id="infob" value="<%=data.getInfob() == null ? "" : data.getInfob() %>" type="text" size="40" /> 
							</span>
						</td>
					</tr>
					<tr align="center">
						<td height="25" align="center">
							<span class=""> 
								<strong>请输入可借天数</strong> : <br /> 
								<input name="infoc" id="infoc" value="<%=data.getInfoc() == null ? "" : data.getInfoc() %>" type="text" size="40" /> 
							</span>
						</td>
					</tr>
				<%
				}
				%>
				<tr align="center">
					<td height="30" align="center">
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
<%=Info.sucinfo(request, false)%>