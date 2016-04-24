<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core" prefix="c" %> 
<%@page import="com.cz.dao.BookhjDAO"%>
<%@page import="com.cz.dao.BooksDAO"%>
<%@page import="com.cz.dao.SreaderDAO"%>
<%@page import="com.cz.common.Info"%>
<%@page import="com.cz.entity.Sysuser"%>
<%@page import="com.cz.common.PageManager"%>
<%@page import="com.cz.entity.Books"%>
<%@page import="com.cz.entity.Sreader"%>
<%@page import="com.cz.entity.Bookhj"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/common.css" />
		<link rel="stylesheet" type="text/css" href="/libmanage/admin/commfiles/css/style.css" />
		<script type="text/javascript" src="/libmanage/js/popup.js"></script>
		<script type="text/javascript" src="/libmanage/admin/commfiles/js/calendar/WdatePicker.js"></script>
	</head>
	<body>
		<!-- cellspacing 是单元格之间的距离、cesspadding 是单元格中内容与边框的距离 -->
		<form name="f1" method="post" action="/libmanage/admin/bookhj.jsp">
			<table id="mainbody" border="0" width="100%" cellspacing="1"
				class="tableform">
				<tr>
					<td width=10% height="31" align="center" style="font-size: 12px">
						书名&nbsp;:
					</td>
					<td width=14% style="font-size: 12px" align="left">

						<select name="bookname" id="bookname">
							<option value="">不限</option>
							<%--<c:forEach items="${lblist}" var="lb">
								<option value="${lb.bookname }">${lb.bookname }</option>
							</c:forEach>
						--%></select>

					</td>
					<td width=10% align="center" style="font-size: 12px">借书人&nbsp;:</td>
					<td width=46% style="font-size: 12px" align="left">
						<select name="readername" id="readername">
							<option value="">不限</option>
							<%--<c:forEach items="${cbslist}" var="cbs">
								<option value="${cbs.uname }">${cbs.uname }</option>
							</c:forEach>
						--%></select>

					</td>
					<td width=20% style="font-size: 12px" align="right">
						<input type="submit" class="btn3_mouseup" value="查询">
						&nbsp;
						<input type="button" class="btn3_mouseup" onclick="add();" value="图书借出">
						&nbsp;
					</td>
				</tr>
			</table>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="mytab" id="table1">
				<tr align="center">
					<td colspan="10" background="/libmanage/admin/commfiles/images/bg.gif" bgcolor="#FFFFFF" class="STYLE3">
						信息列表
					</td>
				</tr>
				<tr align="center">
					<td class="itemtitle">借出时间</td>
					<td class="itemtitle">还书时间</td>
					<td class="itemtitle">书名</td>
					<td class="itemtitle">读者</td>
					<td class="itemtitle">押金</td>
					<td class="itemtitle">相关说明</td>
					<td class="itemtitle">续借至</td>
					<td class="itemtitle">续借申请状态</td>
					<td class="itemtitle">续借审批</td>
					<td class="itemtitle">操作</td>
				</tr>
				<c:forEach items="${nlist}" var="n">
					<tr align="center">
						<td align="center">${n.jtime }</td>
						<td align="center">${n.htime }</td>
						<td align="center"></td>
						<td align="center">${n.readername }</td>
						<td align="center">${n.yjin }</td>
						<td align="center">${n.bei }</td>
						<td align="center">${n.sjtime }</td>
						<td align="center">${n.sjstatus }</td>
						<td align="center">
							<%--<c:if test="${not empty n.sjstatus}">
								<a href="bookhj.jsp?tid=${n.id }">通过</a> 
								&nbsp;|&nbsp;
								<a href="bookhj.jsp?jid=${n.id }">拒绝</a>
							</c:if>
							<c:if test="${empty n.sjstatus}">
								&nbsp;&nbsp;
							</c:if>
						--%></td>
						<td align="center">
							<a href="javascript:hbook('${n.id }')">还书</a>
							&nbsp;|&nbsp;
							<a href="javascript:shbook('${n.id }')">续借</a>
							&nbsp;|&nbsp;
							<a href="javascript:update('${n.id }')">修改</a>
							&nbsp;|&nbsp;
							<a href="javascript:delete('${n.id }')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</body>
	<script type="text/javascript"><%--
		var suc = "${suc}";
		if(suc != null && suc != '') {
			alert("添加成功");
		}
		function update(no) {
			pop('/libmanage/admin/updatebookhj.jsp?id='+no,'修改信息',450,195);
		}
		
		function hbook(no) {
			pop('/libmanage/admin/hbook.jsp?id='+no,'还书',450,272);
		}
		
		function shbook(no) {
			pop('/libmanage/admin/shbook.jsp?id='+no,'续借',400,72);
		}
		
		function add() {
			pop('/libmanage/admin/addbookhj.jsp','图书借出',450,195);
		}
	--%></script>
</html>

