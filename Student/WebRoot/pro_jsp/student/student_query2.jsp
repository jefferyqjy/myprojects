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
				var url = "student/student_query2.do?curPage="+pageNum;
				self.location.href = encodeURI(url);
			}
			function setParent(id) {
				opener.document.getElementById("stuid").value = id;
				window.opener = null;
				window.close();
		}
		</script>
		</head>

		<body id="page">
			<div id="contentWrap">
			<div id="widget table-widget">
			<div class="pageTitle">当前位置：管理学生</div>
			<div class="pageColumn">
			<form id="searchForm" name="searchForm" action="student/student_query2.do" method="post">
				学号：<input type="text" name="stuid">
				姓名：<input type="text" name="name">
				身份证：<input type="text" name="idno">
				<input name="Submit" type="submit" class="bt" value="查询" />
			</form>
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
						学号
					</th>
					<th>
						姓名
					</th>
					<th>
						身份证
					</th>
					<th>
						入学时间
					</th>
					<th>
						所在班级
					</th>
					<th>
						是否缴费
					</th>
					</thead>
				<tbody>
				<c:forEach items="${result}" var="record">
				<tr>
					<td>
						<a style="cursor: pointer;" onclick="setParent('${record.stuid}')" />选择</a>
					</td>
					<td>
						${record.stuid }
					</td>
					<td>
						${record.name }
					</td>
					<td>
						${record.idno }
					</td>
					<td>
						${record.indate }
					</td>
					<td>
						${record.clazzname }
					</td>
					<td>
						${record.ispayment }
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
