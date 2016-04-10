<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'admin_add.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function toPage(pageNum){
			var uName = document.getElementById("username").value;
			var url = "admin/admin_query2.do?curPage="+pageNum;
			self.location.href = encodeURI(url);
		}
		function toModify(url) {
			openWin(url,'mdy',500,500,0);
		}
	</script>
	</head>

	<body id="page">
		<div id="contentWrap">
  		<div id="widget table-widget">
  		<div class="pageTitle">当前位置：查询用户</div>
  		<div class="pageColumn">	
		<form id="searchForm" name="searchForm" action="admin/admin_query2.do" method="post">
			用户名：
			<input name="username" id="username" type="text" value="${username }" />
			<input name="Submit" type="submit" class="bt" value="查询" />
		</form>
		<c:choose>
		<c:when test="${empty result}">
		</c:when>
		<c:otherwise>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<th>
					用户名
				</th>
				<th>
					地址
				</th>
				<th>
					性别
				</th>
				<th>
					邮箱
				</th>
				<th>
					生日
				</th>
				<th>
					电话
				</th>
				<th>
					注册时间
				</th>
				<th>
					执行操作
				</th>
			</thead>
			<c:forEach items="${result}" var="user">
			<tr>
				<td>
					${user.username }
				</td>
				<td>
					${user.address }
				</td>
				<td>
					${user.gender }
				</td>
				<td>
					${user.email }
				</td>
				<td>
					${user.power }
				</td>
				<td>
					${user.phone }
				</td>
				<td>
					${user.createTime }
				</td>
				<td>
					<!-- 
					<img title="修改" src="<%=basePath%>/sys_css/icon/edit2.png" width="16" height="16" onclick="toModify('<%=basePath%>/admin/admin_modify.do?username=${user.username}')"/> 　
					<img title="删除" src="<%=basePath%>/sys_css/icon/del.png" width="16" height="16" onclick="actionDel('<%=basePath%>/admin/admin_del.do?username=${user.username}')"/>
					-->
				</td>
			</tr>
			</c:forEach>
		</table>
		</c:otherwise>
		</c:choose>
		<div class="pageButton">
				${pageInfo }
		</div>
		</div></div></div>
	</body>
</html>
