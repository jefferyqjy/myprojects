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
		function toModify(url) {
			openWin(url,'mdy',500,500,0);
		}
	</script>
	</head>

	<body id="page">
		<h2>
			当前位置：查询资料
		</h2>
		<form id="searchForm" name="searchForm" action="file/file_query.do" method="post">
			关键字：
			<input name="name" type="text" value="${name }" />
			<input name="Submit" type="submit" class="bt" value="查询" />
		</form>
		<c:choose>
		<c:when test="${empty result}">
		</c:when>
		<c:otherwise>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th>
					文件名
				</th>
				<th>
					文件类型
				</th>
				<th>
					上传日期
				</th>
				<th>
					执行操作
				</th>
			</tr>
			<c:forEach items="${result}" var="user">
			<tr>
				<td>
				<input type="hidden" name="id" value="${user.id }">
					${user.name}
				</td>
				<td>
					${user.type }
				</td>
				<td>
					${user.date }
				</td>
				<td>
				<c:if test="${uType == '0'}">
					<a style="cursor: hand" onclick="actionDel('<%=basePath%>/file/file_del.do?id=${user.id}')" />删除</a>
					/
				</c:if>
					<a style="cursor: hand" onclick="actionDo('<%=basePath%>/file/file_download.do?id=${user.id}')" />下载</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		</c:otherwise>
		</c:choose>
	</body>
</html>
