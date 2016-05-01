<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.entity.Syspros"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" />
	</head>
	<%
		SysprosDAO sysprosdao = new SysprosDAO();
		String sql = "select * from syspros where infoa = '图书类别'";
		List<Syspros> lblist = sysprosdao.findBySql(sql);
		
		sysprosdao = new SysprosDAO();
		sql = "select * from syspros where infoa = '出版社'";
		List<Syspros> cbslist = sysprosdao.findBySql(sql);
	%>
	<body>
		<form name="f1" method="post" action="${pageContext.request.contextPath }/books?operate=addbooks">
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
					<td width="23%" align="center">
						ISBN
					</td>
					<td width="48%" align="left">
						<span class="style1"> <input name="isbn" id="isbn"
								type="text" size="30" /> &nbsp;</span>
					</td>
					<td width="29%" rowspan="4" align="center"><%=Info.getImgUpInfo(90)%></td>
				</tr>
				<tr align="center">
					<td align="center">
						书名
					</td>
					<td align="left">
						<span class="style1"> <input name="bookname" id="bookname"
								type="text" size="30" /> </span>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						价格
					</td>
					<td align="left">
						<span class="style1"> <input name="price" id="price"
								type="text" size="20" /> </span>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						图书类别
					</td>
					<td align="left">
						<select name="tslb" id="tslb">
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
						出版社
					</td>
					<td colspan="2" align="left">
						<label>
							<select name="cbs" id="cbs">
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
						简介
					</td>
					<td colspan="2" align="left">
						<span class="style1"> <textarea name="jianj" cols="40"
								rows="2" id="jianj"></textarea> </span>
					</td>
				</tr>

				<tr align="center">
					<td align="center">
						作者
					</td>
					<td colspan="2" align="left">
						<span class="style1"> <input name="author" id="author"
								type="text" size="20" /> </span>
					</td>
				</tr>

				<tr align="center">
					<td align="center">
						出版日期
					</td>
					<td colspan="2" align="left">
						<span class="style1"> <input name="cbrq" id="cbrq"
								type="text" size="40" /> </span>
					</td>
				</tr>

				<tr align="center">
					<td align="center">
						库存
					</td>
					<td colspan="2" align="left">
						<span class="style1"> <input name="kucun" id="kucun"
								type="text" size="20" /> </span>
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
	<%=Info.sucinfo(request, true)%>
</html>


