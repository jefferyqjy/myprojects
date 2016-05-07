<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
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
		String sql = "select * from syspros where infoa = '学历'";
		SysprosDAO sysprosdao = new SysprosDAO();
		List<Syspros> lblist = sysprosdao.findBySql(sql);
		
		sql = "select * from syspros where infoa = '职业'";
		sysprosdao = new SysprosDAO();
		List<Syspros> cbslist = sysprosdao.findBySql(sql);
	%>
	<body>
		<form name="f1" method="post" action="${pageContext.request.contextPath }/sreader?operate=addsreader">
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
					<td width="26%" align="center">
						用户名
					</td>
					<td width="74%" align="left">
						<span class="style1"> <input name="uname" id="uname"
								type="text" size="30" /> &nbsp;</span>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						密码
					</td>
					<td align="left">
						<span class="style1"> <input name="upass" id="upass"
								type="password" size="30" /> </span>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						姓名
					</td>
					<td align="left">
						<span class="style1"> <input name="tname" id="tname"
								type="text" size="20" /> </span>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						学历
					</td>
					<td align="left">
						<select name="xueli" id="xueli">
							<%
								for (Syspros obj : lblist) {
							%>
							<option value="<%=obj.getProname()%>"><%=obj.getProname()%></option>
							<%
								}
							%>
						</select>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						职业
					</td>
					<td colspan="2" align="left">
						<label>
							<select name="ziye" id="ziye">
								<%
									for (Syspros obj : cbslist) {
								%>
								<option value="<%=obj.getProname()%>"><%=obj.getProname()%></option>
								<%
									}
								%>
							</select>
						</label>
					</td>
				</tr>

				<tr align="center">
					<td align="center">
						可借数量
					</td>
					<td colspan="2" align="left">
						<span class="style1"> 
							<input name="kjnum" id="kjnum" type="text" size="20" /> 
						</span>
					</td>
				</tr>

				<tr align="center">
					<td align="center">
						电话
					</td>
					<td colspan="2" align="left">
						<span class="style1"> <input name="tel" id="tel"
								type="text" size="20" /> </span>
					</td>
				</tr>

				<tr align="center">
					<td align="center">
						email
					</td>
					<td colspan="2" align="left">
						<span class="style1"> <input name="email" id="email"
								type="text" size="40" /> </span>
					</td>
				</tr>


				<tr align="center">
					<td colspan="3" align="center">
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
	<%
	if (request.getAttribute("duplicate") != null) {
	%>
		<script type="text/javascript">
			alert("此用户名已存在，请重新输入读者信息")
		</script>
	<%
		}
	%>
	<%=Info.sucinfo(request, true)%>
</html>