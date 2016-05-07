<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cz.common.CommonDAO"%>
<%@page import="com.cz.dao.SysprosDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Syspros"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" />
		<script type="text/javascript" src="/libmanage/js/popup.js"></script>
	</head>
	<%
		String key = request.getParameter("key") == null ? "" : request.getParameter("key");
		String did = request.getParameter("did") == null ? "" : request.getParameter("did");

		if (!did.equals("")) {
			SysprosDAO sysprosdao = new SysprosDAO();
			sysprosdao.deleteById(Integer.valueOf(did));
		}

		String url = "tslbgl.jsp?1=1";
		String sql = "select * from syspros where infoa='超期金额' ";

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
		Syspros syspros = new Syspros();
		if(nlist !=null && nlist.size() > 0) {
			syspros = nlist.get(0);	
		}
	%>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<form name="f1" method="post" action="/libmanage/admin/tslbgl.jsp">
			<%
			if(syspros == null || syspros.getId() == null) {
			%>
			<table id="mainbody" border="0" width="100%" cellspacing="1" class="tableform">
				<tr>
					<td height="31" align="left" style="font-size: 12px">
						<input type="button" onclick="add();" class="btn3_mouseup" value="添加">
					</td>
				</tr>
			</table>
			<%
			}
			%>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
				<tr align="center">
					<td colspan="7" background="/libmanage/admin/commfiles/images/bg.gif" bgcolor="#FFFFFF" class="STYLE3">
						信息列表
					</td>
				</tr>
				<%
				if(syspros != null && syspros.getId() != null) {
				%>
				<tr align="center">
					<td width="37%">
						超期金额
						<br>
					</td>
					<td width="37%">
						<%=syspros.getProname() %>
						<br>
					</td>
					<td align="center">
						<a href="javascript:update('<%=syspros.getId()%>')">修改</a>
						&nbsp;|&nbsp;
						<a href="/libmanage/admin/cqjegl.jsp?did=<%=syspros.getId()%>">删除</a>
					</td>
				</tr>
				<%
				}
				%>
			</table>
		</form>
	</body>
	<script type="text/javascript">
	function update(no) {
		pop('/libmanage/admin/updatepros.jsp?id=' + no, '修改超期金额', 300, 84);
	}

	function add() {
		pop('/libmanage/admin/addpros.jsp?type=5','设置超期金额',300,84);
	}
</script>
</html>
