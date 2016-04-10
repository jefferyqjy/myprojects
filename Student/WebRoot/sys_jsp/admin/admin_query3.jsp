<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sys_jsp/base.jsp"%>
<html>
		<head>
			<base href="<%=basePath%>">
			<title>Query</title>
			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">

		<script type="text/javascript">
			function toPage(pageNum){
				var url = "admin/admin_query3.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
			function setParent(id) {
				opener.document.getElementById("username").value = id;
				window.opener = null;
				window.close();
		}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：选择教师</div>
			<div class="pageColumn">
			<c:choose>
			<c:when test="${empty result}">
			</c:when>
			<c:otherwise>
			<table>
				<thead>
					<th>
						操作
					</th>
					<th>
						教师
					</th>
					</thead>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td>
						<a style="cursor: pointer;" onclick="setParent('${record.username}')" />选择</a>
					</td>
					<td>
						${record.username }
					</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</c:otherwise>
			</c:choose>
			<div class="pageButton">
				${pageInfo }
			</div>
			</div></div></div>
		</body>
	</html>
